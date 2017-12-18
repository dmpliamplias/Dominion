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
     * @param key the message key.
     * @param alertType the alert type.
     */
    public DominionAlert(String key, AlertType alertType, Translator translator) {
        super(alertType);
        this.translator = translator;

        getDialogPane().setMinHeight(USE_PREF_SIZE);
        contentText(key);
        showAndWait();
    }


    // ---- Methods

    protected void title(String key) {
        final String title = translator.getString(key);
        setTitle(title);
    }

    protected void headerText(String key) {
        final String headerText = translator.getString(key);
        setHeaderText(headerText);
    }

    protected void contentText(String key) {
        final String contentText = translator.getString(key);
        setContentText(contentText);
    }

}
