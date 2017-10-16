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


        String message = plServiceLocator.getUser().getUserName() + ": " + view.textFieldChat.getText();
        ChatContainer cc = new ChatContainer();
        cc.setClientId(plServiceLocator.getServerConnectionService().getClientId());
        cc.setMsg( view.textFieldChat.getText());
        view.textFieldChat.clear();

        view.setChatMessage(message, getColorByClientId(cc));
        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }


    //TODO SendButton doesnt work twice

    // Button to send predefined text to the server and display the message on the own screen

    public void sendButtonText() {

        String message = plServiceLocator.getUser().getUserName() + ": " + view.btnSendText.getText();
        ChatContainer cc = new ChatContainer();
        cc.setMsg( message );

        view.setChatMessage(message, getColorByClientId(cc));

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }


    // Method for to receive the chatContainer from the server and set new text in the chat
    public void receiveMessage(ChatContainer chatContainer) {
        Platform.runLater( () -> {
            view.setChatMessage(chatContainer.getMsg(), getColorByClientId(chatContainer));
        } );

    }


    /**
     * Author Michel Schlatter
     * @param chatContainer
     * @return
     */
    private Color getColorByClientId(ChatContainer chatContainer) {

        int id = chatContainer.getClientId();
        Color color = Color.WHITE;

        if (id == 1) {
            color = Color.BLUE;
        }
        if (id == 2) {
            color = Color.YELLOW;
        }
        if (id == 3) {
            color = Color.RED;
        }
        if (id == 4) {
            color = Color.GREEN;
        }
        return color;
    }
}