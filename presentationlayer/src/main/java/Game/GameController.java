package Game;

import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.servermodels.ViewStatus;

import java.io.IOException;

public class GameController extends Controller<GameModel, GameView> {
    private final ServerConnectionService _serverConnection;


    public GameController(GameModel model, GameView view){
        super(model,view);
        _serverConnection = PLServiceLocator.getPLServiceLocator().getServerConnectionService();
        PLServiceLocator.getPLServiceLocator().getServerConnectionService().setGameController(this);

    }

    public void initialize() {
        try {
            _serverConnection.updateViewStatus(ViewStatus.Game); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
    }
}
