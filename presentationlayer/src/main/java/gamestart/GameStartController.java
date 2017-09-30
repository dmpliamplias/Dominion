package gamestart;

import base.Controller;
import com.weddingcrashers.model.Settings;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.ViewStatus;
import com.weddingcrashers.service.ServerConnectionService;
import com.weddingcrashers.service.ServiceLocator;

import java.io.IOException;

/**
 *  author Manuel Wirz
 *  */

public class GameStartController extends Controller <GameStartModel, GameStartView> {

    private final ServerConnectionService _connection;
    private final User _user;

    public void initialize() {
        try {
            _connection.updateViewStatus(ViewStatus.Game); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }

        refreshView();
    }

    public GameStartController(GameStartView view, GameStartModel model, User user){
        super(model,view);
        _user = user; // I think you need id here for set ranking when game is over...
        _connection = ServiceLocator.getServiceLocator().getServerConnectionService();

        initialize();
    }

    private void refreshView()
    {

        view.refresh();
    }


}
