package usermanagement.dialog;

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
    protected BaseDialog() {
        this.setTitle(title());
        this.setHeaderText(headerText());
    }

    // ---- Methods

    /**
     * Returns the title of the dialog.
     *
     * @return the title of the dialog.
     */
    protected abstract String title();

    /**
     * Returns the header text of the dialog.
     *
     * @return the header text of the dialog.
     */
    protected abstract String headerText();

}
