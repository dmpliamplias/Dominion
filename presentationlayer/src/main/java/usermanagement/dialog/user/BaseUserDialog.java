package usermanagement.dialog.user;

import com.weddingcrashers.model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import usermanagement.dialog.BaseDialog;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;
import static javafx.scene.control.ButtonBar.ButtonData.OK_DONE;

/**
 * Base user dialog.
 *
 * @author dmpliamplias
 */
public abstract class BaseUserDialog extends BaseDialog<User> {

    // ---- Members

    /** The user. */
    protected User user;

    /** The grid. */
    private GridPane grid;
    /** The ID field. */
    protected TextField idField;
    /** The name field. */
    protected TextField nameField;
    /** The email field. */
    protected TextField emailField;
    /** The password field. */
    protected PasswordField passwordField;
    /** The is blocked checkbox. */
    protected CheckBox isBlockedBox;
    /** The is super user checkbox. */
    protected CheckBox isSuperUserBox;
    /** The confirm button type. */
    private ButtonType confirmButton;
    /** The login button node. */
    private Node confirmNode;


    // ---- Constructor

    /**
     * Constructor.
     */
    protected BaseUserDialog(User user) {
        this.user = user;

        addBaseLayout();
        fillFields();
        convertResult();
        addFieldListeners();
    }

    // ---- Methods

    /**
     * Fill the fields.
     */
    protected abstract void fillFields();

    /**
     * Adds the base layout.
     */
    private void addBaseLayout() {
        confirmButton = new ButtonType("Confirm", OK_DONE);
        final ButtonType cancelButton = new ButtonType("Cancel", CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(confirmButton, cancelButton);

        // initialize fields
        idField = new TextField();
        // id cannot be changed
        idField.setDisable(true);

        nameField = new TextField();
        emailField = new TextField();
        passwordField = new PasswordField();
        isBlockedBox = new CheckBox();
        isSuperUserBox = new CheckBox();

        createGrid();

        // layout fields
        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Username:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("E-Mail:"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(passwordField, 1, 3);
        grid.add(new Label("Is blocked:"), 0, 4);
        grid.add(isBlockedBox, 1, 4);
        grid.add(new Label("Is super user:"), 0, 5);
        grid.add(isSuperUserBox, 1, 5);
    }

    /**
     * Adds the listeners to the fields.
     */
    private void addFieldListeners() {
        nameField.textProperty().addListener(new UserDialogChangeListener<>());
        emailField.textProperty().addListener(new UserDialogChangeListener<>());
        passwordField.textProperty().addListener(new UserDialogChangeListener<>());
        isBlockedBox.textProperty().addListener(new UserDialogChangeListener<>());
        isSuperUserBox.textProperty().addListener(new UserDialogChangeListener<>());
    }

    /**
     * Converts the result.
     */
    private void convertResult() {
        // Enable/Disable login button depending on whether a username was entered.
        confirmNode = this.getDialogPane().lookupButton(confirmButton);
        confirmNode.setDisable(true);

        this.getDialogPane().setContent(grid);

        this.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButton) {
                final String name = nameField.getText();
                final String email = emailField.getText();
                final String password = passwordField.getText();
                final boolean isBlocked = isBlockedBox.isSelected();
                final boolean isSuperUser = isSuperUserBox.isSelected();

                user.setUserName(name);
                user.setUserEmail(email);
                user.setPassword(password);
                user.setBlocked(isBlocked);
                user.setSuperUser(isSuperUser);
                return user;
            }
            return null;
        });
    }

    /**
     * Creates the grid.
     */
    private void createGrid() {
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
    }

    /**
     * Private change listener class.
     */
    private class UserDialogChangeListener<T> implements ChangeListener<T> {

        // ---- Methods

        @Override
        public void changed(final ObservableValue observable, final Object oldValue, final Object newValue) {
            confirmNode.setDisable(oldValue.equals(newValue));
        }

    }

}
