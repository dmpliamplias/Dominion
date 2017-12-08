package login;

import base.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import static util.StyleSheetPath.LOGIN;

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
        grPa.setVgap(10);
        grPa.setHgap(10);
        grPa.setPadding(new Insets(25, 25, 25, 25));

        setStylesheet(scene, LOGIN);

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


        grPa.setGridLinesVisible(false);

        user = new TextField();
        grPa.add(user,1,1);

        pw = new PasswordField();
        grPa.add(pw,1,2);

        // Create Icons
        ImageView imgVUser = new ImageView(new Image(getClass().getResourceAsStream("/login/user.png")));
        imgVUser.setFitHeight(20);
        imgVUser.setFitWidth(20);

        ImageView imgVLock = new ImageView(new Image(getClass().getResourceAsStream("/login/lock.png")));
        imgVLock.setFitHeight(20);
        imgVLock.setFitWidth(20);

        Label lblUser = new Label();
        lblUser.setGraphic(imgVUser);
        grPa.add(lblUser, 0, 1);

        Label lblLock = new Label();
        lblLock.setGraphic(imgVLock);
        grPa.add(lblLock, 0, 2);


        root.setCenter(grPa);

        this.stage.setScene(scene);

        return scene;
    }

    protected void setTexts(){
        this.stage.setTitle(getText("loginview.title"));
        user.setPromptText(getText("registerview.email"));
        pw.setPromptText(getText("registerview.password"));
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

