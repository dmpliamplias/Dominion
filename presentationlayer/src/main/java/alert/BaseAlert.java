package alert;

import javafx.scene.control.Alert;

/**
 * Base alert.
 *
 * @author dmpliamplias
 */
public abstract class BaseAlert extends Alert {

    // ---- Constructor

    /**
     * Constructor.
     *
     * @param alertType the alert type.
     */
    BaseAlert(AlertType alertType) {
        super(alertType);
    }


    // ---- Methods

    protected abstract void setTexts();

}
