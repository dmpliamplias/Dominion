package usermanagement;

import base.Controller;
import com.weddingcrashers.model.User;
import javafx.stage.Stage;
import login.LoginView;
import usermanagement.dialog.user.CreateUserDialog;
import usermanagement.dialog.user.DeleteUserDialog;
import usermanagement.dialog.user.EditUserDialog;

import java.util.List;
import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.INFORMATION;
import static util.ViewFactory.createLoginView;

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
        setEditAction();
        setCreateAction();
        setDeleteAction();
        setGotoLoginAction();
    }

    /**
     * Goes back to login view.
     */
    private void setGotoLoginAction() {
        view.goToLoginButton.setOnAction(e -> {
            final Stage stage = new Stage();
            final LoginView loginView = createLoginView(stage);

            view.stop();
            loginView.start();
        });
    }

    /**
     * Opens the edit dialog.
     */
    private void setEditAction() {
        view.editButton.setOnAction(e -> {
            final User selectedUser = view.listView.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                view.alert("usermanagementview.selectUserContent",
                        "usermanagementview.selectUserHeader",
                        "usermanagementview.selectUserTitle",
                        INFORMATION);
            }
            else if (selectedUser.isDeleted()) {
                view.alert("usermanagementview.cannotEditContent",
                        "usermanagementview.cannotEditHeader",
                        "usermanagementview.cannotEditTitle",
                        INFORMATION);
            }
            else {
                final EditUserDialog editUserDialog = new EditUserDialog(selectedUser, view.getTranslator());
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
    private void setCreateAction() {
        view.createButton.setOnAction(e -> {
            final User newUser = new User();
            final CreateUserDialog createUserDialog = new CreateUserDialog(newUser, view.getTranslator());
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
    private void setDeleteAction() {
        view.deleteButton.setOnAction(e -> {
            final User selectedUser = view.listView.getSelectionModel().getSelectedItem();
            if (selectedUser == null) {
                view.alert("usermanagementview.selectUserContent",
                        "usermanagementview.selectUserHeader",
                        "usermanagementview.selectUserTitle",
                        INFORMATION);
            }
            else {
                final DeleteUserDialog deleteUserDialog = new DeleteUserDialog(view.getTranslator());
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
        final List<User> users = serviceLocator.getUserService().list(true);
        view.users.addAll(users);
        view.listView.setItems(view.users);
    }

    /**
     * Refresh users.
     */
    private void refreshUsers() {
        final List<User> users = serviceLocator.getUserService().list(true);
        view.users.clear();
        view.users.addAll(users);
    }

}
