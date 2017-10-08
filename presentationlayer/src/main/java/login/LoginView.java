package login;

import base.View;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
/**
 *  @author updated by Murat Kelleci 8.10.17 -
 *  Some changes - Credits: http://docs.oracle.com/javafx/2/get_started/form.htm#BABHGHFI
 *  */

public class LoginView extends View<LoginModel> {


    protected Button btnLogin;
    protected Button btnSign;
    protected TextField user;
    protected PasswordField pw;
    protected Label lblError;


    public LoginView(Stage stage, LoginModel model) {
        super(stage, model);
    }

    public Scene create_GUI() {

        BorderPane root = new BorderPane();
        Scene scene=new Scene(root,500,450);
        GridPane grPa = new GridPane();
        this.stage.setTitle("Login Page");
        this.stage.setHeight(500);
        this.stage.setWidth(800);

        grPa.setAlignment(Pos.CENTER);
        grPa.setHgap(10);
        grPa.setVgap(10);
        grPa.setPadding(new Insets(25, 25, 25, 25));

        Scene scene1 = new Scene(grPa, 300, 275);
        stage.setScene(scene);

        //Creation of all needed Buttons and Fields

        btnLogin = new Button();
        btnLogin.setText("Login");
        btnLogin.setPrefSize(130,40);
        btnSign = new Button();
        btnSign.setText("Sign Up");
        btnSign.setPrefSize(130,40);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btnLogin);
        hbBtn.getChildren().add(btnSign);
        grPa.add(hbBtn, 1, 4);

        Label userName = new Label("User Name:");
        grPa.add(userName, 0, 1);
        user = new TextField();
        grPa.add(user,1,1);

        Label password = new Label("Password:");
        grPa.add(password, 0, 2);
        pw = new PasswordField();
        grPa.add(pw,1,2);

        root.setCenter(grPa);


        //scene.getStylesheets().add(getClass().getResource("GameStart.css").toExternalForm());
        this.stage.setScene(scene);

        return scene;
    }


    void refreshModel(){
        model.setPassword(pw.getText());
        model.setEmail(user.getText());
    }

    public Stage getStage(){
        return this.stage;
    }

    void setLoginError(){
        lblError.setText(model.getError());
    }


}

