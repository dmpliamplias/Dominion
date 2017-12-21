package lobby;

import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.ChatContainer;
import com.weddingcrashers.servermodels.GameSettings;
import com.weddingcrashers.servermodels.LobbyContainer;
import com.weddingcrashers.servermodels.Methods;
import com.weddingcrashers.servermodels.ViewStatus;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import game.GameController;
import game.GameModel;
import game.GameView;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import ranking.RankingView;
import util.ViewUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static javafx.scene.media.AudioClip.INDEFINITE;
import static util.ViewFactory.createRankingView;

/**
 *  author Michel Schlatter
 *  */

public class LobbyController extends Controller <LobbyModel, LobbyView> {

    private final User _user;
    private HashMap<Integer, User> players;
    Translator translator;

    public LobbyController(LobbyView view, LobbyModel model) {
        super( model, view );
        _user = plServiceLocator.getUser();
        serverConnectionService.setLobbyController( this );
        translator = ServiceLocator.getServiceLocator().getTranslator();

        initialize();
    }

    public void initialize() {
        try {
            serverConnectionService.updateViewStatus( ViewStatus.Lobby ); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.simpleAlert( e.getMessage(), Alert.AlertType.ERROR );
        }

        /**
         *  author Manuel Wirz
         *  Set different views for client and hoster
         *  */

        if (!serverConnectionService.isHoster()){
            view.getBtnStart().setVisible( false );
            view.gethBoxOption1().setVisible( false );
            view.gethBoxOption2().setVisible( false );
            view.setWaitText();
            view.setTexts();
            view.getLvPlayers().setMouseTransparent( true );
            view.getLvPlayers().setFocusTraversable( false );
        }

        /**
         *  Check if sound is on or off
         *  */

        if (plServiceLocator.soundIsOn == false){
            super.view.menuBar.getMenuItemMusicMute().setSelected( true );
        }

        /**
         *  Start sound default
         *  */

        if(plServiceLocator.soundIsOn) {
            startSound();
        }

        /**
         *  Sets sound off
         *  */
        
        super.view.menuBar.getMenuItemMusicMute().setOnAction( event -> {
            plServiceLocator.audioClip.stop();
            plServiceLocator.soundIsOn = false;
        } );

        /**
         *  Sets sound on
         *  */

        super.view.menuBar.getMenuItemMusicUnmute().setOnAction( event -> {

            if(!plServiceLocator.audioClip.isPlaying()){
                startSound();
                plServiceLocator.soundIsOn = true;
            }
        } );

        /**
         *  Sends message in chat with Enter
         *  */

        view.getTextFieldChat().setOnKeyPressed(event -> {
                    if (event.getCode().equals( KeyCode.ENTER)){
                       sendMessage();
        }  });

        /**
         *  ActionHandler for start the game
         *  */

        view.getBtnStart().setOnAction( (event) -> {
            startGame();

        } );

        /**
         *  Sends message in chat due to pressing button
         *  */

        view.getBtnChatSend().setOnAction( event -> {
            sendMessage();
        } );


        /**
         *  Showing help
         *  */

        view.getBtnHelp().setOnAction( event -> {
            help();
        } );

        //author Murat Kelleci am 24.10.2017
        view.btnRanking.setOnAction(event -> {
            goToRankingView();
        });


        loadData();
    }

    /**
     *  author Manuel Wirz
     *  Method for backgorund music
     *  https://stackoverflow.com/questions/31784698/javafx-background-thread-task-should-play-music-in-a-loop-as-background-thread
     *  */

    public void startSound(){

        final Task task = new Task() {

            @Override
            protected Object call() throws Exception {
                int s = INDEFINITE;
                plServiceLocator.audioClip = new AudioClip(getClass().getResource("/Sounds/background.wav").toExternalForm());
                plServiceLocator.audioClip.setVolume(0.07);
                plServiceLocator.audioClip.setCycleCount(s);
                plServiceLocator.audioClip.play();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }


    /**
     *  author Manuel Wirz
     *  Starts Alert Information for some help
     *  */

    private void help(){
        view.alert("lobbyview.help", Alert.AlertType.INFORMATION );
    }


    private void loadData(){
        LobbyContainer lc = new LobbyContainer(Methods.Lobby_Players);
        try {
            serverConnectionService.sendObject(lc);
        } catch (IOException e) {
            view.simpleAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * this method gets called when a new player comes to the lobby
     * the player will bi added to the players list
     * @author Michel Schlatter
     */
    public void handleServerAnswer_newPlayer(LobbyContainer lc) {
        Platform.runLater( () -> {
            String res = "";
            players = lc.getPlayers();
            Iterator iter = lc.getPlayers().entrySet().iterator();

            view.observablePlayerList.clear();
            while (iter.hasNext()) {
                Map.Entry<Integer, User> item = (Map.Entry) iter.next();
                view.observablePlayerList.add( item.getKey() + ": " + item.getValue().getUsername());
            }

            view.lvPlayers.setItems( view.observablePlayerList);
            view.lvPlayers.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
        } );
    }

    /**
     * @author Michel Schlatter
     * gets called after the hoster send a valid gamestartrequest to the server.
     */
    public void handleServerAnswer_startGame(LobbyContainer lc) {
        Platform.runLater(() -> {
            GameModel gameModel = new GameModel();
            GameView gameView = new GameView( this.view.getStage(), gameModel );
            new GameController(gameModel,gameView, lc.getPlayers(), lc.getGameSettings());

            this.view.stop();
            gameView.start();
        });
    }

    /**
     * @author Manuel Wirz
     * Method for some checks if the correct options were selected in the lobby
     * for starting the game
     */
    private void startGame() {

        ObservableList<String> players = view.lvPlayers.getItems();


        if (!view.cbFinishPointCards.isSelected() && view.choiceBox.getSelectionModel().isEmpty()) {
            view.alert("lobbyview.falseStatement", Alert.AlertType.WARNING );

        } else if (view.cbFinishPointCards.isSelected() && !view.choiceBox.getSelectionModel().isEmpty()) {
            view.alert("lobbyview.falseStatement", Alert.AlertType.WARNING );
            view.choiceBox.getSelectionModel().clearSelection();
            view.cbFinishPointCards.setSelected( false );
        } else if (players.size() < 2) {
            view.alert("lobbyview.notEnoughPlayers", Alert.AlertType.WARNING );
        } else {
            sendStartRequest();
        }
    }


    /**
     * @author Michel Schlatter
     * send a gamestartrequest to the hoster
     */
    public void sendStartRequest(){

        ObservableList<String> names = view.lvPlayers.getItems();

        ArrayList<Integer> clientIds = new ArrayList<Integer>();

        for (String name : names) {
            String[] array = name.split( ":" );
            int clientId = Integer.parseInt( array[0] );
            clientIds.add( clientId );
        }

        Integer rounds = (Integer)view.choiceBox.getSelectionModel().getSelectedItem();
        LobbyContainer lc = new LobbyContainer( Methods.StartGame );
        lc.setClientIds_startGame( clientIds );
        GameSettings gs = new GameSettings();
        gs.setPointCards(view.cbFinishPointCards.isSelected());
        gs.setFinishAfterRounds(rounds);
        lc.setGameSettings(gs);


        try {
            serverConnectionService.sendObject( lc );
        } catch (IOException e) {
            view.simpleAlert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }

    /**
     * author Manuel Wirz
     * Sets the chat message, send it to the server and display it on the client display
     */

    public void sendMessage() {

        String chatMessage =  plServiceLocator.getUser().getUsername() + ": " + view.textFieldChat.getText();
        ChatContainer cc = new ChatContainer(Methods.ChatLobby);
        cc.setClientId(plServiceLocator.getServerConnectionService().getClientId());
        cc.setMsg( chatMessage );
        view.setChatMessage(chatMessage, ViewUtils.getColorByClientId(cc.getClientId()));
        view.textFieldChat.clear();

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.simpleAlert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }

    /**
     *  author Manuel Wirz
     *  Method for receiving chat message form the server and display it
     *  @param chatContainer
     *  */

    public void handleServerAnswer_receiveMessage(ChatContainer chatContainer) {
            Platform.runLater( () -> {
                view.setChatMessage(chatContainer.getMsg(), ViewUtils.getColorByClientId(chatContainer.getClientId()));
            } );

    }

    //@author Murat Kelleci am 24.10.2017

    private void goToRankingView() {
        final Stage stage = new Stage();
        final RankingView rankingView = createRankingView(stage);

        view.stop();
        rankingView.start();
    }
}
