import javafx.fxml.FXML;

public class GameStartController {

    private final GameStartView _view;
    private final GameStartModel _model;
    private final int _userId;
    @FXML
    public void initialize() {
        // load settings from database instead of following code:
        // Settings settings = dbContext.Users.Load(userId).Settings  ==> this is just an example on how to do it.

        Settings settings = new Settings();
        settings.setSound(true);

        displaySettings(settings);
    }

    public GameStartController(GameStartView view, GameStartModel model, int userId){
        _view = view;
        _model = model;
        _userId = userId;

        initialize();
    }

    private void displaySettings(Settings settings){
        _view.lbl.setText("Sound is on: " + settings.isSoundOn());
    }


}
