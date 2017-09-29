package gamestart;

import com.weddingcrashers.model.Settings;
import com.weddingcrashers.model.User;

public class GameStartController {

    private final GameStartView _view;
    private final GameStartModel _model;
    private final User _user;

    public void initialize() {
        // load settings from database instead of following code:
        // main.java.com.weddingcrashers.model.Settings settings = dbContext.Users.Load(userId).main.java.com.weddingcrashers.model.Settings  ==> this is just an example on how to do it.

        Settings settings = new Settings();
        settings.setSound(true);
        _model.setSettings(settings);

        refreshView();
    }

    public GameStartController(GameStartView view, GameStartModel model, User user){
        _view = view;
        _model = model;
        _user = user; // I think you need id here for set ranking when game is over...

        initialize();
    }

    private void refreshView()
    {
        _view.refresh();
    }


}
