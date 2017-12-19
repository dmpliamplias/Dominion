package alert;

import com.weddingcrashers.service.Translator;
import javafx.scene.control.Alert;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

/**
 * Dominion alert.
 *
 * @author dmpliamplias
 */
public class DominionAlert extends Alert {

    // ---- Members

    /** The translator. */
    private Translator translator;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param contentTextKey the message key.
     * @param alertType the alert type.
     */
    public DominionAlert(String contentTextKey, AlertType alertType, Translator translator) {
        super(alertType);
        this.translator = translator;

        getDialogPane().setMinHeight(USE_PREF_SIZE);
        contentText(contentTextKey);
        showAndWait();
    }

    /**
     * Constructor.
     *
     * @param contentTextKey the message key.
     * @param alertType the alert type.
     */
    public DominionAlert(String contentTextKey, String headerTextKey, AlertType alertType, Translator translator) {
        super(alertType);
        this.translator = translator;

        getDialogPane().setMinHeight(USE_PREF_SIZE);
        contentText(contentTextKey);
        headerText(headerTextKey);
        showAndWait();
    }

    /**
     * Constructor.
     *
     * @param contentTextKey the message key.
     * @param alertType the alert type.
     */
    public DominionAlert(String contentTextKey, String headerTextKey, String titleTextKey, AlertType alertType, Translator translator) {
        super(alertType);
        this.translator = translator;

        getDialogPane().setMinHeight(USE_PREF_SIZE);
        contentText(contentTextKey);
        headerText(headerTextKey);
        title(titleTextKey);
        showAndWait();
    }


    // ---- Methods

    private void title(String key) {
        final String title = translator.getString(key);
        setTitle(title);
    }

    private void headerText(String key) {
        final String headerText = translator.getString(key);
        setHeaderText(headerText);
    }

    private void contentText(String key) {
        final String contentText = translator.getString(key);
        setContentText(contentText);
    }

}
