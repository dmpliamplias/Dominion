package register;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author Murat Kelleci
 */

public class RegisterView {


    Button register;
    Button cancel;
    TextField email;
    PasswordField pw;
    PasswordField pw_confirm;

    RegisterModel model;
    Stage stage;
    Label error;

    public RegisterView(Stage stage, RegisterModel model) {
        this.stage = stage;
        this.model = model;

        this.stage.setTitle("Register");
        this.stage.setHeight(500.00);
        this.stage.setWidth(800.00);

        BorderPane core = new BorderPane();
        register = new Button("Register");
        cancel = new Button("Cancel");
        email = new TextField();
        pw = new PasswordField();
        pw_confirm = new PasswordField();

        VBox vbox = new VBox();
        vbox.getChildren().addAll(register, cancel, email, pw, pw_confirm);
        core.setCenter(vbox);
        Scene scene = new Scene(core);
        stage.setScene(scene);
    }

    void refreshModel() {
        this.model.setPassword(pw.getText());
        this.model.setPassword(pw_confirm.getText());
        this.model.setEmail(email.getText());
    }

    void refreshView() {
        // umgekehrte von refreshmodel
        error.setText(this.model.getError());
    }

    void setError() {
        error.setText(this.model.getError());
    }

    Stage getStage() {
        return this.stage;

    }


    void setLoginError() {
        error.setText("Wrong PW und Login");
    }

    public void start() {
        this.stage.show();
    }


    public void stop() {
        this.stage.hide();
    }


}


