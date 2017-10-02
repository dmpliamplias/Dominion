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
 *  @author Michel Schlatter
 *  */

public class LoginView extends View<LoginModel>{

    Button login;
    Button cancel;
    TextField email;
    PasswordField pw;
    Label error;


    public LoginView(Stage stage, LoginModel model){
        super(stage, model);
    }

    public Scene create_GUI() {

        stage.setTitle( "Login" );
        stage.setHeight( 500 );
        stage.setWidth( 800 );

        BorderPane root = new BorderPane();
        login = new Button();
        login.setText( "Login" );
        cancel = new Button();
        cancel.setText( "Cancel" );
        email = new TextField();
        pw = new PasswordField();

        HBox box = new HBox();
        box.getChildren().addAll( email, pw, login, cancel );
        root.setCenter( box );

        Scene scene = new Scene( root );
        //scene.getStylesheets().add(getClass().getResource("GameStart.css").toExternalForm());
        stage.setScene( scene );

        return scene;
    }


    void refreshModel(){
        model.setPassword(pw.getText());
        model.setEmail(email.getText());
    }

    public Stage getStage(){
        return stage;
    }

    void setLoginError(){
        error.setText(model.getError());
    }


}

