package splashscreen;

import app.Dominion;
import base.Controller;
import javafx.concurrent.Worker;

public class SplashScreenController extends Controller<SplashScreenModel, SplashScreenView> {

    // ---- Constructor

    /**
     * Constructor
     *
     * @param main the main program.
     * @param model the model.
     * @param view the view.
     */
    public SplashScreenController(Dominion main, SplashScreenModel model, SplashScreenView view) {
        super(model,view);
        view.progress.progressProperty().bind(model.initializer.progressProperty());

        model.initializer.stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED)
                main.startApp();
        });
    }

}
