package Game;


import util.ViewUtils;
import base.Controller;
import com.weddingcrashers.servermodels.ChatContainer;
import com.weddingcrashers.servermodels.ViewStatus;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;

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

        /**
         *  author Manuel Wirz
         *  */
        
        view.getBtnChatSend().setOnAction( event -> {
            sendMessage();
        } );

        view.getBtnSendText().setOnAction( event -> {
            sendButtonText();
        } );

        view.getTextFieldChat().setOnKeyPressed(event -> {
            if (event.getCode().equals( KeyCode.ENTER)){
                sendMessage();
            }  });


    }

    /**
     * author Manuel Wirz
     */

    // Method to send the ChatContainer to the server and display the message in the own screen

    public void sendMessage() {


        String message = plServiceLocator.getUser().getUserName() + ": " + view.getTextFieldChat().getText();
        ChatContainer cc = new ChatContainer();
        cc.setClientId(plServiceLocator.getServerConnectionService().getClientId());
        cc.setMsg( message);
        view.getTextFieldChat().clear();
        view.setChatMessage(message, ViewUtils.getColorByClientId(cc.getClientId()));

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }


    // Button to send predefined text to the server and display the message on the own screen

    public void sendButtonText() {

        String message = plServiceLocator.getUser().getUserName() + ": " + view.getBtnSendText().getText();
        ChatContainer cc = new ChatContainer();
        cc.setClientId(plServiceLocator.getServerConnectionService().getClientId());
        cc.setMsg( message );

        view.setChatMessage(message, ViewUtils.getColorByClientId(cc.getClientId()));

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }


    // Method for to receive the chatContainer from the server and set new text in the chat
    public void receiveMessage(ChatContainer chatContainer) {
        Platform.runLater( () -> {
            view.setChatMessage(chatContainer.getMsg(), ViewUtils.getColorByClientId(chatContainer.getClientId()));
        } );

    }

}