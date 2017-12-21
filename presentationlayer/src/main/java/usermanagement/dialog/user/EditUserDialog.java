package usermanagement.dialog.user;


import com.weddingcrashers.model.User;
import com.weddingcrashers.service.Translator;

/**
 * Edit user dialog.
 *
 * @author dmpliamplias
 */
public class EditUserDialog extends BaseUserDialog {

    // ---- Constructor

    /**
     * Constructor.
     *
     * @param user the user.
     */
    public EditUserDialog(User user, Translator translator) {
        super(user, translator);
    }


    // ---- Methods

    @Override
    protected void fillFields() {
        idField.setText(String.valueOf(user.getId()));
        nameField.setText(user.getUsername());
        emailField.setText(user.getUserEmail());
        passwordField.setText(user.getPassword());
        isBlockedBox.setSelected(user.isBlocked());
        isSuperUserBox.setSelected(user.isSuperUser());
    }

    @Override
    protected String title(Translator translator) {
        return translator.getString("usermanagementview.editDialog.title");
    }

    @Override
    protected String headerText(Translator translator) {
        return translator.getString("usermanagementview.editDialog.headerText");
    }

}
