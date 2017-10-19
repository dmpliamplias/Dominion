package usermanagement;

import base.Controller;
import com.weddingcrashers.model.User;
import lobby.EditUserDialog;
import lobby.LobbyController;
import lobby.LobbyModel;
import lobby.LobbyView;
import login.LoginController;
import login.LoginModel;
import login.LoginView;

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
        goToLoginButton();
        goToLobbyButton();
    }

    private void goToLoginButton() {
        view.goToLoginButton.setOnAction(e -> {
            final LoginModel model = new LoginModel();
            final LoginView view = new LoginView(this.view.getStage(), model);
            new LoginController(view, model);

            this.view.stop();
            view.start();
        });
    }

    private void goToLobbyButton() {
        view.goToLobbyButton.setOnAction(e -> {
            final LobbyModel model = new LobbyModel();
            final LobbyView view = new LobbyView(this.view.getStage(), model);
            new LobbyController(view, model);

            this.view.stop();
            view.start();
        });
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
