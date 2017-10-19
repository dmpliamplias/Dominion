package usermanagement;

import base.Controller;
import com.weddingcrashers.model.User;
import lobby.EditUserDialog;

import java.util.List;

/**
 * The user management controller.
 *
 * @author dmpliamplias
 */
public class UserManagementController extends Controller<UserManagementModel, UserManagementView> {

    // ---- Members


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param model the model.
     * @param view  the view.
     */
    public UserManagementController(UserManagementModel model, UserManagementView view) {
        super(model, view);
        initialize();
    }

    private void initialize() {
        loadUsers();
        openEditDialog();
    }

    private void openEditDialog() {
        view.editButton.setOnAction(e -> {
            final User selectedUser = view.listView.getSelectionModel().getSelectedItem();
            final EditUserDialog editUserDialog = new EditUserDialog(selectedUser);
        });
    }

    private void loadUsers() {
        final List<User> users = serviceLocator.getUserService().list();
        view.users.addAll(users);
        view.listView.setItems(view.users);
    }


    // --- Methods


}
