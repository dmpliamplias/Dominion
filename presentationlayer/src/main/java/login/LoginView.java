package login;

import base.View;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 *  @author updated by Murat Kelleci 8.10.17 - Some changes
 *
 *  */

public class LoginView extends View<LoginModel> {


    protected Button btnLogin;
    protected Button btnSign;
    protected TextField email;
    protected PasswordField pw;
    protected Label lblError;


    public LoginView(Stage stage, LoginModel model) {
        super(stage, model);
    }

    public Scene create_GUI() {

        this.stage.setTitle("Login Page");
        this.stage.setHeight(500);
        this.stage.setWidth(800);

        BorderPane root = new BorderPane();
        btnLogin = new Button();
        btnLogin.setText("Login");
        btnSign = new Button();
        btnSign.setText("Sign Up");
        email = new TextField();
        pw = new PasswordField();

        HBox box = new HBox();
        box.getChildren().addAll(email, pw, btnLogin, btnSign);
        root.setCenter(box);

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("GameStart.css").toExternalForm());
        this.stage.setScene(scene);

        return scene;
    }

    void refreshModel(){
        model.setPassword(pw.getText());
        model.setEmail(email.getText());
    }

    public Stage getStage(){
        return this.stage;
    }

    void setLoginError(){
        lblError.setText(model.getError());
    }


}

