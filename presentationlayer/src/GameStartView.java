import javafx.stage.Stage;

public class GameStartView {
    private Stage _stage;

    public GameStartView(Stage stage, GameStartModel model){
        _stage = stage;
    }

    public void stop(){
        _stage.close();
    }
}
