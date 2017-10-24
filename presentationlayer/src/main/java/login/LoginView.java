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
    protected Button btnSignUp;
    protected TextField user;
    protected PasswordField pw;


    private Label lblPw;
    private Label lblEmail;

    public LoginView(Stage stage, LoginModel model) {
        super(stage, model);
    }

    public Scene create_GUI() {

        BorderPane root = new BorderPane();
        Scene scene=new Scene(root,500,450);
        GridPane grPa = new GridPane();

        this.stage.setHeight(500);
        this.stage.setWidth(800);

        grPa.setAlignment(Pos.CENTER);
        grPa.setHgap(10);
        grPa.setVgap(10);
        grPa.setPadding(new Insets(25, 25, 25, 25));

        scene.getStylesheets().addAll(this.getClass().getResource("/login/LoginView.css").toExternalForm());

        stage.setScene(scene);

        //Creation of all needed Buttons and Fields

        btnLogin = new Button();
        btnLogin.setPrefSize(130,40);
        btnSignUp = new Button();
        btnSignUp.setPrefSize(130,40);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btnLogin);
        hbBtn.getChildren().add(btnSignUp);
        grPa.add(hbBtn, 1, 4);

        lblEmail = new Label();
        grPa.add(lblEmail, 0, 1);
        user = new TextField();
        grPa.add(user,1,1);

        lblPw = new Label();
        grPa.add(lblPw, 0, 2);
        pw = new PasswordField();
        grPa.add(pw,1,2);

        root.setCenter(grPa);


        //scene.getStylesheets().add(getClass().getResource("GameStart.css").toExternalForm());
        this.stage.setScene(scene);

        setTexts();
        return scene;
    }


    protected void setTexts(){
        this.stage.setTitle(getText("loginview.title"));
        lblPw.setText(getText("registerview.password"));
        lblEmail.setText(getText("registerview.email"));
        btnLogin.setText(getText("logginview.login"));
        btnSignUp.setText(getText("logginview.register"));
    }

    void refreshModel(){
        model.setPassword(pw.getText());
        model.setEmail(user.getText());
    }

    public Stage getStage(){
        return this.stage;
    }



}

