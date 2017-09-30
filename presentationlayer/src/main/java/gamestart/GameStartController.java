package gamestart;

import base.Controller;
import com.weddingcrashers.model.Settings;
import com.weddingcrashers.model.User;

/**
 *  author Manuel Wirz
 *  */

public class GameStartController extends Controller <GameStartModel, GameStartView> {

    private final User _user;

    public void initialize() {
        // load settings from database instead of following code:
        // main.java.com.weddingcrashers.model.Settings settings = dbContext.Users.Load(userId).main.java.com.weddingcrashers.model.Settings  ==> this is just an example on how to do it.

        Settings settings = new Settings();
        settings.setSound(true);
        model.setSettings(settings);

        refreshView();
    }

    public GameStartController(GameStartView view, GameStartModel model, User user){
        super(model,view);
        _user = user; // I think you need id here for set ranking when game is over...

        initialize();
    }

    private void refreshView()
    {

        view.refresh();
    }


}
