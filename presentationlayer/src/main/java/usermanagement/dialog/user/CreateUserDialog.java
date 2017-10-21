package usermanagement.dialog.user;

import com.weddingcrashers.model.User;

/**
 * The create user dialog.
 *
 * @author dmpliamplias
 */
public class CreateUserDialog extends BaseUserDialog {

    // ---- Constructor

    /**
     * Constructor.
     *
     * @param user the user.
     */
    public CreateUserDialog(final User user) {
        super(user);
    }


    // ---- Methods

    @Override
    protected void fillFields() {
        idField.setText("generated");
    }

    @Override
    protected String title() {
        return "User creation";
    }

    @Override
    protected String headerText() {
        return "Create a new user";
    }

}
