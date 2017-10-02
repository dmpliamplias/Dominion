package Game;

import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.servermodels.ViewStatus;

import java.io.IOException;

public class GameController extends Controller<GameModel, GameView> {
    private final ServerConnectionService _connection;


    public GameController(GameModel model, GameView view){
        super(model,view);
        _connection = PLServiceLocator.getPLServiceLocator().getServerConnectionService();

    }

    public void initialize() {
        try {
            _connection.updateViewStatus(ViewStatus.Game); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
    }
}
