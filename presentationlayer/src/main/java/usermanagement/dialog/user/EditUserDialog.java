package usermanagement.dialog.user;


import com.weddingcrashers.model.User;

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
    public EditUserDialog(User user) {
        super(user);
    }


    // ---- Methods

    @Override
    protected void fillFields() {
        idField.setText(String.valueOf(user.getId()));
        nameField.setText(user.getUserName());
        emailField.setText(user.getUserEmail());
        passwordField.setText(user.getPassword());
        isBlockedBox.setSelected(user.isBlocked());
        isSuperUserBox.setSelected(user.isSuperUser());
    }

    @Override
    protected String title() {
        return "Editing user";
    }

    @Override
    protected String headerText() {
        return "Edit a user.";
    }

}
