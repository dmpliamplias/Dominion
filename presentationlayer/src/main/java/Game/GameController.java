package Game;

import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.servermodels.ChatContainer;
import com.weddingcrashers.servermodels.ViewStatus;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Separator;

import java.io.IOException;


public class GameController extends Controller<GameModel, GameView> {
    private final ServerConnectionService serverConnection;


    public GameController(GameModel model, GameView view, boolean myTurn){
        super(model,view);
        serverConnection = PLServiceLocator.getPLServiceLocator().getServerConnectionService();
        initialize();

    }


    public void initialize() {
        try {
            serverConnection.updateViewStatus(ViewStatus.Game); // set ViewStatus for Server

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

            try {
                serverConnection.sendObject(cc);
            } catch (IOException e) {
                view.alert(e.getMessage(), Alert.AlertType.ERROR);
            }
    }

    public void sendButtonText(){

            ChatContainer cc = new ChatContainer();
            cc.setMsg( view.btnSendText.getText());

            try {
                serverConnection.sendObject( cc );
            } catch (IOException e) {
                view.alert(e.getMessage(), Alert.AlertType.ERROR);
            }
    }


    public void receiveMessage(ChatContainer chatContainer){

        Platform.runLater(() -> {

            String beforeText = view.textAreaChat.getText();
            String newText = beforeText += System.getProperty("line.separator") + chatContainer.getMsg();
            view.textAreaChat.setText(newText);

        });

    }


}
