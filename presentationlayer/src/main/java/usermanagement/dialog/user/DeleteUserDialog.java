package usermanagement.dialog.user;

import javafx.scene.control.ButtonType;
import usermanagement.dialog.BaseDialog;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

/**
 * Delete user dialog.
 *
 * @author dmpliamplias
 */
public class DeleteUserDialog extends BaseDialog<Boolean> {

    // ---- Constructor

    /**
     * Constructor.
     */
    public DeleteUserDialog() {
        this.setContentText("You really want to delete the user?");

        final ButtonType confirmButton = new ButtonType("Confirm", OK_DONE);
        final ButtonType cancelButton = new ButtonType("Cancel", CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(confirmButton, cancelButton);

        this.setResultConverter(dialogButton -> dialogButton == confirmButton);
    }


    // ---- Methods

    @Override
    protected String title() {
        return "User deletion";
    }

    @Override
    protected String headerText() {
        return "Delete a user";
    }

}
