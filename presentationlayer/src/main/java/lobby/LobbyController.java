package lobby;

import Game.GameController;
import Game.GameModel;
import Game.GameView;
import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import util.PLServiceLocator;
import util.ViewUtils;
import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.*;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyCode;
import login.LoginController;
import login.LoginModel;
import login.LoginView;
import ranking.RankingController;
import ranking.RankingModel;
import ranking.RankingView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.weddingcrashers.service.Language.ENGLISH;
import static com.weddingcrashers.service.Language.GERMAN;
import static com.weddingcrashers.service.Language.SWISS_GERMAN;
import static javafx.scene.media.AudioClip.INDEFINITE;

/**
 *  author Michel Schlatter
 *  */

public class LobbyController extends Controller <LobbyModel, LobbyView> {

    private final User _user;
    private HashMap<Integer, User> players;
    Translator translator;

    public LobbyController(LobbyView view, LobbyModel model) {
        super( model, view );
        _user = plServiceLocator.getUser(); // I think you need id here for set ranking when game is over...
        serverConnectionService.setLobbyController( this );
        translator = ServiceLocator.getServiceLocator().getTranslator();

        initialize();
    }

    public void initialize() {
        try {
            serverConnectionService.updateViewStatus( ViewStatus.Lobby ); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }

        /**
         *  author Manuel Wirz
         *  */

        // Hoster or normal Client? -> sets the view

        if (!serverConnectionService.isHoster()){

            view.getBtnStart().setVisible( false );
            view.gethBoxOption1().setVisible( false );
            view.gethBoxOption2().setVisible( false );
            view.setWaitText();
            view.setTexts();
            view.getLvPlayers().setMouseTransparent( true );
            view.getLvPlayers().setFocusTraversable( false );


        }

        // Start sound default

        startSound();

        // Start and end background music
        
        //TODO Manuel startSound() abfangen, dass es zweimal lauft

        super.view.getMenuItemMusicMute().setOnAction( event -> {
            plServiceLocator.audioClip.stop();
        } );

        super.view.getMenuItemMusicUnmute().setOnAction( event -> {
                startSound();
        } );

        // Sends message with Enter

        view.getTextFieldChat().setOnKeyPressed(event -> {
                    if (event.getCode().equals( KeyCode.ENTER)){
                       sendMessage();
        }  });

        // ActionHandler for start the game

        view.getBtnStart().setOnAction( (event) -> {
            startGame();

        } );

        // ActionHandler for testing

        view.getBtnTestGameView().setOnAction( event -> {
            goToGameView();
        } );

        // ActionHandler for sending msg by pressing the button

        view.getBtnChatSend().setOnAction( event -> {
            sendMessage();
        } );


        // ActionHandler for showing some help  by pressing the button

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
     *  */

    // Method for backgorund music

    // https://stackoverflow.com/questions/31784698/javafx-background-thread-task-should-play-music-in-a-loop-as-background-thread

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


    private void help(){

        view.alert(getText("lobbyview.help"), Alert.AlertType.INFORMATION );

    }


    private void loadData(){
        LobbyContainer lc = new LobbyContainer(Methods.Lobby_Players);
        try {
            serverConnectionService.sendObject(lc);
        } catch (IOException e) {
            view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    //TODO Manuel Wirz löschen -> only for testing

    private void goToGameView() {
       sendStartRequest();
    }

    /**
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
                view.observablePlayerList.add( item.getKey() + ": " + item.getValue().getUserName());
            }

            view.lvPlayers.setItems( view.observablePlayerList);
            view.lvPlayers.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
        } );
    }

    /**
     * @author Michel Schlatter
     */
    public void handleServerAnswer_startGame(LobbyContainer lc) {
        Platform.runLater(() -> {
            GameModel gameModel = new GameModel();
            GameView gameView = new GameView( this.view.getStage(), gameModel );
            GameController gameController = new GameController(gameModel,gameView, lc.getPlayers(), lc.getGameSettings());

            this.view.stop();
            gameView.start();
        });
    }

    /**
     * @author Michel Schlatter + Manuel Wirz
     */
    private void startGame() {
        ObservableList<String> names = view.lvPlayers.getSelectionModel().getSelectedItems();
        ObservableList<String> players = view.lvPlayers.getItems();

        // some checks for starting the correct end options

        if (!view.cbFinishPointCards.isSelected() && view.choiceBox.getSelectionModel().isEmpty()) {
            view.alert( getText( "lobbyview.falseStatement" ), Alert.AlertType.WARNING );

        } else if (view.cbFinishPointCards.isSelected() && !view.choiceBox.getSelectionModel().isEmpty()) {
            view.alert( getText( "lobbyview.falseStatement" ), Alert.AlertType.WARNING );
            view.choiceBox.getSelectionModel().clearSelection();
            view.cbFinishPointCards.setSelected( false );
        } else if (players.size() >= 2 && view.lvPlayers.getSelectionModel().getSelectedItems().size() <= 1) {

        view.startStage().show();

        view.btnDialogNo.setOnAction( event -> {
            view.stageDialog.close();
        } );

        view.btnDialogYes.setOnAction( event -> {
            sendStartRequest();
            view.stageDialog.close();
        } );

        } else if (names.size() < 2) {
            view.alert( getText( "lobbyview.notEnoughPlayers" ), Alert.AlertType.WARNING );
        } else if(!view.lvPlayers.getSelectionModel().isSelected( 0 )) {
            view.alert( getText( "lobbyview.serverIsNotSelected" ), Alert.AlertType.ERROR );
        } else {

            sendStartRequest();
        }
    }



    public void sendStartRequest(){

        ObservableList<String> names;

       if(view.lvPlayers.getSelectionModel().getSelectedItems().size() >= 2 ){
            names = view.lvPlayers.getSelectionModel().getSelectedItems();
        } else{

            names = view.lvPlayers.getItems();
        }


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
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }

    /**
     * author Manuel Wirz
     */

    // ChatController same Logic as in the GameController -> Look there for the comments for each method

    public void sendMessage() {

        String chatMessage =  plServiceLocator.getUser().getUserName() + ": " + view.textFieldChat.getText();
        ChatContainer cc = new ChatContainer();
        cc.setClientId(plServiceLocator.getServerConnectionService().getClientId());
        cc.setMsg( chatMessage );
        view.setChatMessage(chatMessage, ViewUtils.getColorByClientId(cc.getClientId()));
        view.textFieldChat.clear();

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }

    public void handleServerAnswer_receiveMessage(ChatContainer chatContainer) {
            Platform.runLater( () -> {
                view.setChatMessage(chatContainer.getMsg(), ViewUtils.getColorByClientId(chatContainer.getClientId()));
            } );

    }

    //@author Murat Kelleci am 24.10.2017

    private void goToRankingView() {
        Stage s = new Stage(  );
        RankingModel model = new RankingModel();
        RankingView view = new RankingView(s, model);
        RankingController rankingController = new RankingController(model, view);
        this.view.stop();
        view.start();
    }
}
