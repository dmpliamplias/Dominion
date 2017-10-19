package usermanagement;

import base.Controller;
import com.weddingcrashers.model.User;
import lobby.EditUserDialog;

import java.util.List;
import java.util.Optional;

/**
 * The user management controller.
 *
 * @author dmpliamplias
 */
public class UserManagementController extends Controller<UserManagementModel, UserManagementView> {

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


    // --- Methods

    /**
     * Initialize controller.
     */
    private void initialize() {
        loadUsers();
        openEditDialog();
    }

    /**
     * Opens the edit dialog.
     */
    private void openEditDialog() {
        view.editButton.setOnAction(e -> {
            final User selectedUser = view.listView.getSelectionModel().getSelectedItem();
            final EditUserDialog editUserDialog = new EditUserDialog(selectedUser);
            final Optional<User> userOptional = editUserDialog.showAndWait();
            if (userOptional.isPresent()) {
                final User user = userOptional.get();
                serviceLocator.getUserService().update(user);
            }
        });
    }

    /**
     * Loads the users.
     */
    private void loadUsers() {
        final List<User> users = serviceLocator.getUserService().list();
        view.users.addAll(users);
        view.listView.setItems(view.users);
    }

}
