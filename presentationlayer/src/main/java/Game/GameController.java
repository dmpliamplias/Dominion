package Game;


import base.Controller;
import com.weddingcrashers.servermodels.ChatContainer;
import com.weddingcrashers.servermodels.ViewStatus;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

import java.io.IOException;


public class GameController extends Controller<GameModel, GameView> {


    public GameController(GameModel model, GameView view, boolean myTurn){
        super(model,view);
        serverConnectionService.setGameController(this);
        initialize();

    }


    public void initialize() {
        try {
            serverConnectionService.updateViewStatus(ViewStatus.Game); // set ViewStatus for Server

        } catch (IOException e) {
            this.view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }

        view.btnChatSend.setOnAction( event -> {
            sendMessage();
        } );

        view.btnSendText.setOnAction( event -> {
            sendButtonText();
        } );



    }

    /**
     *  author Manuel Wirz
     *  */

    public void sendMessage(){


            String message  = view.textFieldChat.getText();
            ChatContainer cc = new ChatContainer();
            cc.setMsg(message );
            String before = view.textAreaChat.getText();
            String newText = before +=  message +  System.getProperty("line.separator");
            view.textAreaChat.setText(newText );
            view.textFieldChat.clear();

            try {
                serverConnectionService.sendObject(cc);
            } catch (IOException e) {
                view.alert(e.getMessage(), Alert.AlertType.ERROR);
            }
    }


    public void sendButtonText(){

        String message  = view.btnSendText.getText();
        ChatContainer cc = new ChatContainer();
        cc.setMsg(message );
        String before = view.textAreaChat.getText();
        String newText = before +=  message +  System.getProperty("line.separator");
        view.textAreaChat.setText(newText );

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    public void receiveMessage(ChatContainer chatContainer){

        Platform.runLater(() -> {

            String beforeText = view.textAreaChat.getText();
            String newText = beforeText +=   createChatText(chatContainer) + System.getProperty("line.separator");
            view.textAreaChat.setText(newText);

        });

    }

    private String createChatText(ChatContainer chatContainer){
        Color c = getColorByClientId(chatContainer);

        String msg = plServiceLocator.getUser().getUserName() + ": " + chatContainer.getMsg();
        return msg;
    }

    private Color getColorByClientId(ChatContainer chatContainer){
       int id = chatContainer.getClientId();
       Color c = Color.WHITE;

       switch(id) {
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
