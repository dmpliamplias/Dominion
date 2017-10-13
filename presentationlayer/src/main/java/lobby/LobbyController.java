package lobby;

import Game.GameController;
import Game.GameModel;
import Game.GameView;
import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.*;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.paint.Color;

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
    private ObservableList<String> list = FXCollections.observableArrayList();
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
    }


    private void goToGameView() {
        GameModel model = new GameModel();
        GameView view = new GameView( this.view.getStage(), model );
        GameController gameController = new GameController( model, view, serverConnectionService.isHoster() );

        this.view.stop();
        view.start();

    }

    public void handleServerAnswer_newPlayer(LobbyContainer lc) {
        Platform.runLater( () -> {
            String res = "";
            players = lc.getPlayers();
            Iterator iter = lc.getPlayers().entrySet().iterator();

            while (iter.hasNext()) {
                Map.Entry<Integer, User> item = (Map.Entry) iter.next();
                list.add( item.getKey() + ": " + item.getValue().getUserName() );
            }

            view.lvPlayers.setItems( list );
            view.lvPlayers.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE );
        } );
    }

    public void handleServerAnswer_startGame(boolean myTurn) {
        GameModel gameModel = new GameModel();
        GameView gameView = new GameView( this.view.getStage(), gameModel );
        GameController gameController = new GameController( gameModel, gameView, myTurn );

        this.view.stop();
        gameView.start();
    }

    private void startGame() {
        ObservableList<String> names = view.lvPlayers.getSelectionModel().getSelectedItems();
        if (names.size() < 2) {
            // TODO: 02.10.2017 vanessa: text erstellen => Nicht genügend Spieler selektiert um zu spielen.
            this.view.alert( translator.getString( "LobbyView_NotEnoughPlayers" ),
                    Alert.AlertType.WARNING );
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
    // ChatController

    public void sendMessage() {

        String chatMessage = view.textFieldChat.getText();
        ChatContainer cc = new ChatContainer();
        cc.setMsg( chatMessage );
        String before = view.txtAreaChat.getText();
        String newText = before += chatMessage + System.getProperty( "line.separator" );
        view.txtAreaChat.setText( newText );
        view.textFieldChat.clear();

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }

    public void receiveMessage(ChatContainer chatContainer) {

        Platform.runLater( () -> {

            String beforeText = view.txtAreaChat.getText();
            String newText = beforeText += createChatText( chatContainer ) + System.getProperty( "line.separator" );
            view.txtAreaChat.setText( newText );

        } );

    }

    private String createChatText(ChatContainer chatContainer) {
        Color c = getColorByClientId( chatContainer );
        String msg = /*plServiceLocator.getUser().getUserName() + " :" + */chatContainer.getMsg();
        return msg;
    }

    private Color getColorByClientId(ChatContainer chatContainer) {
        int clientId = chatContainer.getClientId();
        Color c = Color.WHITE;

        switch (clientId) {
            case 1:
                c = Color.BLUE;
                break;
            case 2:
                c = Color.YELLOW;
                break;
            case 3:
                c = Color.GREEN;
                break;
            case 4:
                c = Color.RED;
                break;
        }

        return c;


    }
}
