package lobby;


import com.weddingcrashers.model.User;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

/**
 * @author dmpliamplias
 * @version $Id: xxx$
 */
public class EditUserDialog extends Dialog {

    // ---- Members

    /** The user. */
    private final User user;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param user the user.
     */
    public EditUserDialog(User user) {
        this.user = user;
        showDialog();
    }

    private void showDialog() {
        this.setTitle("Edit Dialog");
        this.setHeaderText("Edit User + User.getName()");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        final TextField id = new TextField();
        id.setDisable(true);
        final TextField name = new TextField();
        final TextField email = new TextField();
        // TODO: 19.10.17 check passwordfield
        final PasswordField password = new PasswordField();
        final TextField isBlocked = new TextField();
        final TextField isSuperUser = new TextField();

        id.setText(String.valueOf(user.getId()));
        name.setText(user.getUserName());
        email.setText(user.getUserEmail());
        password.setText(user.getPassword());
        isBlocked.setText("isBlocked");
        isSuperUser.setText("isSuperUser");

        grid.add(new Label("ID:"), 0, 0);
        grid.add(id, 1, 0);

        grid.add(new Label("Username:"), 0, 1);
        grid.add(name, 1, 1);

        grid.add(new Label("E-Mail:"), 0, 2);
        grid.add(email, 1, 2);

        grid.add(new Label("Password:"), 0, 3);
        grid.add(password, 1, 3);

        grid.add(new Label("Is blocked:"), 0, 4);
        grid.add(isBlocked, 1, 4);

        grid.add(new Label("Is super user:"), 0, 5);
        grid.add(isSuperUser, 1, 5);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = this.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        this.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> name.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        this.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(name.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = this.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
    }

}
