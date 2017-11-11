package lobby;

import Game.GameController;
import Game.GameModel;
import Game.GameView;
import javafx.stage.Stage;
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

        if (!serverConnectionService.isHoster()){

            view.getBtnStart().setVisible( false );
            view.gethBoxOption1().setVisible( false );
            view.gethBoxOption2().setVisible( false );
            view.setWaitText();
            view.setTexts();


        }

        view.getBtnStart().setOnAction( (event) -> {
            startGame();
        } );

        /**
         *  author Manuel Wirz
         *  */

        view.getTextFieldChat().setOnKeyPressed(event -> {
                    if (event.getCode().equals( KeyCode.ENTER)){
                       sendMessage();
        }  });

        view.getBtnTestGameView().setOnAction( event -> {
            goToGameView();
        } );

        view.getBtnChatSend().setOnAction( event -> {
            sendMessage();
        } );

        view.getBtnLogout().setOnAction( event -> {
            logout();
        } );

        view.getBtnHelp().setOnAction( event -> {
            help();
        } );

        //author Murat Kelleci am 24.10.2017
        //TODO fix bug murat
        view.btnRanking.setOnAction(event -> {
            goToRankingView();
        });



        loadData();
    }

    /**
     *  author Manuel Wirz
     *  */

    private void help(){

        //TODO@Manuel help key anpassen
        view.alert(getText("lobbyview.help"), Alert.AlertType.INFORMATION );

    }

    private void logout(){

        plServiceLocator.setUser(null);

        LoginModel model = new LoginModel();
        LoginView view = new LoginView( this.view.getStage(), model );
        LoginController loginController = new LoginController( view, model );

        this.view.stop();
        view.start();
    }

    private void loadData(){
        LobbyContainer lc = new LobbyContainer(Methods.Lobby_Players);
        try {
            serverConnectionService.sendObject(lc);
        } catch (IOException e) {
            view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


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
            GameController gameController = new GameController(gameModel,gameView, lc.getPlayers(), lc.getGameSettings(), lc.isYourTurn());

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

        if (view.cbFinishPointCards.isSelected() && !view.choiceBox.getSelectionModel().isEmpty()) {
            view.alert( getText( "lobbyview.falseStatement" ), Alert.AlertType.WARNING );
            view.choiceBox.getSelectionModel().clearSelection();
            view.cbFinishPointCards.setSelected( false );
        }
        if (!view.cbFinishPointCards.isSelected() && view.choiceBox.getSelectionModel().isEmpty()) {
            view.alert( getText( "lobbyview.falseStatement" ), Alert.AlertType.WARNING );

        } else if (players.size() >= 2 && view.lvPlayers.getSelectionModel().getSelectedItems().isEmpty()) {
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
        } else {

            sendStartRequest();
        }
    }


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

    public void receiveMessage(ChatContainer chatContainer) {
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
