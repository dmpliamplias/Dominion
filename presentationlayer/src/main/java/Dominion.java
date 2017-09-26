import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginModel;
import login.LoginView;

public class Dominion extends Application {

    private LoginModel model;
    private LoginView view;
    private LoginController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // first call Login Page and after successfull login call the 'GamestartView'
        // and give the userId to the Gamestartcontroller, so he can load the user settings, etc.

        model = new LoginModel();
        view = new LoginView(primaryStage, model);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login/LoginView.fxml"));
        loader.setController(new LoginController(view, model));

        view.start();
    }

    @Override
    public void stop() {
        if (view != null) view.stop();
    }

}
