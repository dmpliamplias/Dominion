package usermanagement.dialog.user;


import com.weddingcrashers.model.User;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Edit user dialog.
 *
 * @author dmpliamplias
 */
public class EditUserDialog extends BaseUserDialog {

    // ---- Members

    /** The user. */
    private final User user;

    /** The id field. */
    private TextField idField;
    /** The name field. */
    private TextField nameField;
    /** The e-mail field. */
    private TextField emailField;
    /** The password field. */
    private TextField passwordField;
    /** The is blocked checkbox. */
    private CheckBox isBlockedBox;
    /** The is super user checkbox. */
    private CheckBox isSuperUserBox;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param user the user.
     */
    public EditUserDialog(User user) {
        this.user = user;
        initializeDialog();
    }

    private void initializeDialog() {
        this.setTitle("Edit Dialog");
        this.setHeaderText("Edit " + user.getUserName() + " (" + user.getUserEmail() + ")");

        final ButtonType loginButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        final ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(loginButtonType, cancelButtonType);

        final GridPane grid = createGrid();

        // initialize fields
        idField = new TextField();
        // id cannot be changed
        idField.setDisable(true);

        nameField = new TextField();
        emailField = new TextField();
        passwordField = new PasswordField();
        isBlockedBox = new CheckBox();
        isSuperUserBox = new CheckBox();

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

        // set values for fields
        idField.setText(String.valueOf(user.getId()));
        nameField.setText(user.getUserName());
        emailField.setText(user.getUserEmail());
        // TODO: 19.10.17 decrypt password
        passwordField.setText(user.getPassword());
        // TODO: 19.10.17 extend user and db (p.s. Edited by & at)
        isBlockedBox.setSelected(false);
        isSuperUserBox.setSelected(true);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = this.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        this.getDialogPane().setContent(grid);

        this.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                final String name = nameField.getText();
                final String email = emailField.getText();
                final String password = passwordField.getText();
                final boolean isBlocked = isBlockedBox.isSelected();
                final boolean isSuperUser = isSuperUserBox.isSelected();
                user.setUserName(name);
                user.setUserEmail(email);
                user.setPassword(password);
//                user.setBlocked(isBlocked);
//                user.setSuperUser(isSuperUser);
                return user;
            }
            return null;
        });
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        return grid;
    }

}
