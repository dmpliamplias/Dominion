package splashscreen;

import base.Model;
import javafx.concurrent.Task;

import static java.lang.Thread.sleep;

public class SplashScreenModel extends Model {

    // ---- Members

    /** The initializer. */
    final Task<Void> initializer = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            // For every transition 1.3 seconds
            updateProgress(1, 5);
            sleep(1300);
            updateProgress(2, 5);
            sleep(1300);
            updateProgress(3, 5);
            sleep(1300);
            updateProgress(4, 5);
            sleep(1300);
            updateProgress(5, 5);

            return null;
        }
    };

    
    // ---- Constructor

    /**
     * Constructor.
     */
    public SplashScreenModel() {
        super();
    }


    // ---- Methods

    public void initialize() {
        new Thread(initializer).start();
    }

}
