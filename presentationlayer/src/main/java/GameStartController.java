import com.weddingcrashers.model.Settings;
import javafx.fxml.FXML;

public class GameStartController {

    private final GameStartView _view;
    private final GameStartModel _model;
    private final int _userId;
    @FXML
    public void initialize() {
        // load settings from database instead of following code:
        // main.java.com.weddingcrashers.model.Settings settings = dbContext.Users.Load(userId).main.java.com.weddingcrashers.model.Settings  ==> this is just an example on how to do it.

        Settings settings = new Settings();
        settings.setSound(true);
        _model.setSettings(settings);

        refreshView();
    }

    public GameStartController(GameStartView view, GameStartModel model, int userId){
        _view = view;
        _model = model;
        _userId = userId;

        initialize();
    }

    private void refreshView()
    {
        _view.refresh();
    }


}
