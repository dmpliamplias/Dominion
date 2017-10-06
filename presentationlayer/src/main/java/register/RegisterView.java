package register;

import base.View;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

/**
 * @author Murat Kelleci
 */

public class RegisterView extends View<RegisterModel>{

    Button register;
    Button cancel;
    TextField email;
    PasswordField pw;
    PasswordField pw_confirm;
    Label error;

    public RegisterView(Stage stage, RegisterModel model){
        super(stage, model);
    }

    public Scene create_GUI(){

        this.stage.setTitle("Register page");
        this.stage.setWidth(800);
        this.stage.setHeight(500);

        BorderPane root = new BorderPane();
        register =new Button("Register");
        cancel = new Button("Cancel");
        email = new TextField();
        pw = new PasswordField();
        pw_confirm= new PasswordField();

        VBox box = new VBox();
        box.getChildren().addAll(email, pw, pw_confirm);
        root.setCenter(box);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        return scene;
    }


    void refreshModel() {
        this.model.setPassword(pw.getText());
        this.model.setPassword(pw_confirm.getText());
        this.model.setEmail(email.getText());
    }

    void refreshView() {
        this.model.getPassword();
        this.model.getPassword_confirm();
        this.model.getEmail();
        // umgekehrte von refreshmodel
        error.setText(this.model.getError());
    }

    void setError(String string) {
        error.setText(this.model.getError());
    }

    public Stage getStage() {
        return this.stage;

    }


    void setRegisterError() {
        error.setText(model.getError());
    }

    public void start() {
        this.stage.show();
    }


    public void stop() {
        this.stage.hide();
    }


}


