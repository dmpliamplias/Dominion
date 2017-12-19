package usermanagement.dialog.user;

import com.weddingcrashers.service.Translator;
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
    public DeleteUserDialog(Translator translator) {
        super(translator);
        setContentText(translator.getString("usermanagementview.deleteDialog.contentText"));

        final ButtonType confirmButton = new ButtonType(translator.getString("usermanagementview.dialog.confirm"), OK_DONE);
        final ButtonType cancelButton = new ButtonType(translator.getString("usermanagementview.dialog.cancel"), CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(confirmButton, cancelButton);

        this.setResultConverter(dialogButton -> dialogButton == confirmButton);
    }


    // ---- Methods

    @Override
    protected String title(Translator translator) {
        return translator.getString("usermanagementview.deleteDialog.title");
    }

    @Override
    protected String headerText(Translator translator) {
        return translator.getString("usermanagementview.deleteDialog.headerText");
    }

}
