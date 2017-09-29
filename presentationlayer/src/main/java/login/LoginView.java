package login;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
/**
 *  @author Michel Schlatter
 *  */
public class LoginView {

    Button login;
    Button cancel;
    TextField email;
    PasswordField pw;
    Label error;

    LoginModel _model;
    Stage _stage;

    public LoginView(Stage stage, LoginModel model)
    {
        _stage = stage;
        _model = model;

        _stage.setTitle("Login");
        _stage.setHeight(500);
        _stage.setWidth(800);

        BorderPane root = new BorderPane();
       login = new Button();
       login.setText("Login");
       cancel = new Button();
       cancel.setText("Cancel");
       email = new TextField();
       pw = new PasswordField();

        HBox box = new HBox();
        box.getChildren().addAll(email,pw,login,cancel);
        root.setCenter(box);

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("GameStart.css").toExternalForm());
        stage.setScene(scene);

    }

    void refreshModel(){
        _model.setPassword(pw.getText());
        _model.setEmail(email.getText());
    }

    Stage getStage(){
        return _stage;
    }

    void setLoginError(){
        error.setText(_model.getError());
    }

    public void start() {
        _stage.show();
    }


    public void stop(){
        _stage.hide();
    }


}
