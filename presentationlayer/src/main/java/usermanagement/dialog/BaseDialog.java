package usermanagement.dialog;

import com.weddingcrashers.service.Translator;
import javafx.scene.control.Dialog;

/**
 * Base dialog.
 *
 * @param <T> the type of the dialog.
 * @author dmpliamplias
 */
public abstract class BaseDialog<T> extends Dialog<T> {

    // ---- Constructor

    /**
     * Constructor.
     */
    protected BaseDialog(Translator translator) {
        setTitle(title(translator));
        setHeaderText(headerText(translator));
    }

    // ---- Methods

    /**
     * Returns the title of the dialog.
     *
     * @param translator the translator.
     * @return the title of the dialog.
     */
    protected abstract String title(Translator translator);

    /**
     * Returns the header text of the dialog.
     *
     * @param translator the translator.
     * @return the header text of the dialog.
     */
    protected abstract String headerText(Translator translator);

}
