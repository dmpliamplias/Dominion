package usermanagement;

import base.Controller;
import com.weddingcrashers.model.User;
import javafx.scene.control.Alert;
import login.LoginController;
import login.LoginModel;
import login.LoginView;
import usermanagement.dialog.user.CreateUserDialog;
import usermanagement.dialog.user.DeleteUserDialog;
import usermanagement.dialog.user.EditUserDialog;

import java.util.List;
import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

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
        openCreateDialog();
        openDeleteDialog();
        goToLoginButton();
    }

    /**
     * Goes back to login view.
     */
    private void goToLoginButton() {
        view.goToLoginButton.setOnAction(e -> {
            final LoginModel model = new LoginModel();
            final LoginView view = new LoginView(this.view.getStage(), model);
            new LoginController(view, model);

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
            if (selectedUser == null) {
                new SelectUserAlert();
            }
            else {
                final EditUserDialog editUserDialog = new EditUserDialog(selectedUser);
                final Optional<User> userOptional = editUserDialog.showAndWait();
                if (userOptional.isPresent()) {
                    final User user = userOptional.get();
                    serviceLocator.getUserService().update(user);
                    refreshUsers();
                }
            }
        });
    }

    /**
     * Opens the create user dialog.
     */
    private void openCreateDialog() {
        view.createButton.setOnAction(e -> {
            final User newUser = new User();
            final CreateUserDialog createUserDialog = new CreateUserDialog(newUser);
            final Optional<User> userOptional = createUserDialog.showAndWait();
            if (userOptional.isPresent()) {
                final User user = userOptional.get();
                serviceLocator.getUserService().create(user);
                refreshUsers();
            }
        });
    }

    /**
     * Opens the delete dialog.
     */
    private void openDeleteDialog() {
        view.deleteButton.setOnAction(e -> {
            final User selectedUser = view.listView.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                new SelectUserAlert();
            }
            else {
                final DeleteUserDialog deleteUserDialog = new DeleteUserDialog();
                final Optional<Boolean> optional = deleteUserDialog.showAndWait();
                final Boolean deleted = optional.get();
                if (deleted) {
                    serviceLocator.getUserService().delete(selectedUser);
                    refreshUsers();
                }
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

    /**
     * Refresh users.
     */
    private void refreshUsers() {
        final List<User> users = serviceLocator.getUserService().list();
        view.users.clear();
        view.users.addAll(users);
    }

    /**
     * Private select user alert.
     */
    private class SelectUserAlert extends Alert {

        // ---- Constructor

        /**
         * Constructor.
         */
        SelectUserAlert() {
            super(INFORMATION);
            this.setContentText("Please select a user.");
            this.showAndWait();
        }

    }

}