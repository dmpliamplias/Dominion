package lobby;

import app.PLServiceLocator;
import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.ViewStatus;
import app.ServerConnectionService;

import java.io.IOException;

/**
 *  author Manuel Wirz
 *  */

public class LobbyController extends Controller <LobbyModel, LobbyView> {

    private final ServerConnectionService _connection;
    private final User _user;

    public LobbyController(LobbyView view, LobbyModel model, User user){
        super(model,view);
        _user = user; // I think you need id here for set ranking when game is over...
        _connection = PLServiceLocator.getPLServiceLocator().getServerConnectionService();

        initialize();
    }

    public void initialize() {
        try {
            _connection.updateViewStatus(ViewStatus.Lobby); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
    }

}
