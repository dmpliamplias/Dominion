import javafx.application.Application;
import javafx.stage.Stage;

public class dominion extends Application {

    private GameStartModel model;
    private GameStartView view;
    private GameStartController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        model = new GameStartModel();
        view = new GameStartView(primaryStage, model);
        controller = new GameStartController(view, model);
    }

    @Override
    public void stop() {
        if (view != null) view.stop();
    }

}
