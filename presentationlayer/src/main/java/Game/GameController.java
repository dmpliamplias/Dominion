package Game;


import base.Controller;
import com.weddingcrashers.servermodels.ChatContainer;
import com.weddingcrashers.servermodels.ViewStatus;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

import java.io.IOException;


public class GameController extends Controller<GameModel, GameView> {


    public GameController(GameModel model, GameView view, boolean myTurn) {
        super( model, view );
        serverConnectionService.setGameController( this );
        initialize();

    }


    public void initialize() {
        try {
            serverConnectionService.updateViewStatus( ViewStatus.Game ); // set ViewStatus for Server

        } catch (IOException e) {
            this.view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }

        view.btnChatSend.setOnAction( event -> {
            sendMessage();
        } );

        view.btnSendText.setOnAction( event -> {
            sendButtonText();
        } );


    }

    /**
     * author Manuel Wirz
     */

    // Method to send the ChatContainer to the server and display the message in the own screen

    public void sendMessage() {


        String message = view.textFieldChat.getText();
        ChatContainer cc = new ChatContainer();
        cc.setMsg( message );
        String before = view.textAreaChat.getText();
        String newText = before += message + System.getProperty( "line.separator" );
        view.textAreaChat.replaceText( newText );
        view.textFieldChat.clear();

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }


    //TODO SendButton doesnt work twice

    // Button to send predefined text to the server and display the message on the own screen

    public void sendButtonText() {

        String message = view.btnSendText.getText();
        ChatContainer cc = new ChatContainer();
        cc.setMsg( message );
        String before = view.textAreaChat.getText();
        String newText = before += message + System.getProperty( "line.separator" );
        view.textAreaChat.replaceText( newText );

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }


    // Method for to receive the chatContainer from the server and set new text in the chat
    public void receiveMessage(ChatContainer chatContainer) {

        Platform.runLater( () -> {

            String beforeText = view.textAreaChat.getText();
            String newText = beforeText += createChatText( chatContainer ) + System.getProperty( "line.separator" );
            view.textAreaChat.replaceText( newText );

        } );

    }

    // Method to get the user and the message from the chatContainer
    private String createChatText(ChatContainer chatContainer) {

        String msg = plServiceLocator.getUser().getUserName() + ": " + chatContainer.getMsg();
        return msg;
    }


    // Method for saving clients color
    private String getColorByIDasAString(ChatContainer chatContainer) {

        int id = chatContainer.getClientId();
        String color = new String();

        if (id == 1) {
            color = "BLUE";
        }
        if (id == 2) {
            color = "YELLOW";
        }
        if (id == 3) {
            color = "RED";
        }
        if (id == 4) {
            color = "GREEN";
        }
        return color;
    }
}