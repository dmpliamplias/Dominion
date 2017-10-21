package usermanagement.dialog.user;

import com.weddingcrashers.model.User;
import usermanagement.dialog.BaseDialog;

public class DeleteUserDialog extends BaseDialog<Void> {

    /**
     * Constructor.
     *
     * @param user the user.
     */
    public DeleteUserDialog(User user) {

    }

    @Override
    protected String title() {
        return "User deletion";
    }

    @Override
    protected String headerText() {
        return "Delete a user";
    }

}
