import com.weddingcrashers.model.User;
import com.weddingcrashers.service.ObjectUpdateService;
import com.weddingcrashers.service.ObjectUpdateServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.util.List;

public class dominion extends Application {

    private GameStartModel model;
    private GameStartView view;
    private GameStartController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // first call Login Page and after successfull login call the 'GamestartView'
        // and give the userId to the Gamestartcontroller, so he can load the user settings, etc.

        model = new GameStartModel();
        view = new GameStartView(primaryStage, model);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameStartView.fxml"));
        loader.setController(new GameStartController(view, model, 1));

     view.start();
    }

    @Override
    public void stop() {
        if (view != null) view.stop();
    }

}
