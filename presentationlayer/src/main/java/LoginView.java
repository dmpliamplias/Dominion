import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

public class LoginView {

    protected Button login;
    protected Button cancel;
    protected TextField email;
    protected  PasswordField pw;
    protected  Label error;

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

    protected void refreshModel(){
        _model.setPassword(pw.getText());
        _model.setEmail(email.getText());
    }

    protected Stage getStage(){
        return _stage;
    }

    protected  void setLoginError(){
        error.setText("Wrong Login or PW");
    }

    protected void start() {
        _stage.show();
    }


    protected void stop(){
        _stage.hide();
    }


}
