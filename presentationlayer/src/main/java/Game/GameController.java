package Game;

import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.servermodels.ChatContainer;
import com.weddingcrashers.servermodels.ViewStatus;

import java.io.IOException;

/**
 *  author Manuel Wirz
 *  */

public class GameController extends Controller<GameModel, GameView> {
    private final ServerConnectionService serverConnection;


    public GameController(GameModel model, GameView view, boolean myTurn){
        super(model,view);
        serverConnection = PLServiceLocator.getPLServiceLocator().getServerConnectionService();
        PLServiceLocator.getPLServiceLocator().getServerConnectionService().setGameController(this);

    }

    public void initialize() {
        try {
            serverConnection.updateViewStatus(ViewStatus.Game); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
    }

    // TODO Manuel send Objects via ServerConnectionService
    public void sendMessage(){

        view.sendButton.setOnAction((event) -> {

           // serverConnection.sendObject();

        });
    }


}
