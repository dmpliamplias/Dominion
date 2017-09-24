import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameStartView {
    final private Stage _stage;
    final private GameStartModel _model;

    protected Label lbl;

    public GameStartView(Stage stage, GameStartModel model)
    {
        _stage = stage;
        _model = model;

        _stage.setTitle("Dominion Start");
        _stage.setHeight(500);
        _stage.setWidth(800);

        BorderPane root = new BorderPane();
        lbl = new Label();
        root.setCenter(lbl);

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("GameStart.css").toExternalForm());
        stage.setScene(scene);

    }

    protected void displayModel(GameStartModel model){
        lbl.setText("Sound is on: "+ model.getSettings().isSoundOn());
        //...conitinue...
    }

    protected void start() {
        _stage.show();
    }


    protected void stop(){
        _stage.hide();
    }
}
