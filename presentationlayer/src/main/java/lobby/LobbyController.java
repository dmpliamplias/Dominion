package lobby;

import Game.GameController;
import Game.GameModel;
import Game.GameView;
import app.ViewUtils;
import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.*;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import login.LoginController;
import login.LoginModel;
import login.LoginView;

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

        view.btnStart.setDisable( !serverConnectionService.isHoster() ); // only hoster can start game.
        view.btnStart.setOnAction( (event) -> {
            startGame();
        } );

        view.btnGameView.setOnAction( (event) -> {
            goToGameView();
        } );

        view.btnChatSend.setOnAction( event -> {
            sendMessage();
        } );

        view.btnLogout.setOnAction( event -> {
            logout();
        } );

        loadData();
    }

    /**
     *  author Manuel Wirz
     *  */

    private void logout(){

        //TODO Migi User auf Null setzen

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
        GameModel model = new GameModel();
        GameView view = new GameView( this.view.getStage(), model );
        GameController gameController = new GameController( model, view, serverConnectionService.isHoster() );

        this.view.stop();
        view.start();

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
    public void handleServerAnswer_startGame(boolean myTurn) {
        Platform.runLater(() -> {
            GameModel gameModel = new GameModel();
            GameView gameView = new GameView( this.view.getStage(), gameModel );
            GameController gameController = new GameController( gameModel, gameView, myTurn );

            this.view.stop();
            gameView.start();
        });
    }

    /**
     * @author Michel Schlatter
     */
    private void startGame() {
        ObservableList<String> names = view.lvPlayers.getSelectionModel().getSelectedItems();
        if (names.size() < 2) {
            // TODO: 02.10.2017 vanessa: text erstellen => Nicht genügend Spieler selektiert um zu spielen.
            view.alert( translator.getString( "lobbyview.notEnoughPlayers" ), Alert.AlertType.WARNING );
        } else {
            ArrayList<Integer> clientIds = new ArrayList<Integer>();

            for (String name : names) {
                String[] array = name.split( ":" );
                int clientId = Integer.parseInt( array[0] );
                clientIds.add( clientId );
            }

            LobbyContainer lc = new LobbyContainer( Methods.StartGame );
            lc.setClientIds_startGame( clientIds );

            try {
                serverConnectionService.sendObject( lc );
            } catch (IOException e) {
                view.alert( e.getMessage(), Alert.AlertType.ERROR );
            }
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
}
