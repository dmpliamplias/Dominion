package register;

import base.View;
import com.weddingcrashers.service.Language;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
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

import static util.StyleSheetPath.REGISTER;

/**
 * @author Murat Kelleci - Update of View - Several changes
 */

public class RegisterView extends View<RegisterModel>{

    protected Button btnRegister;
    protected Button btnCancel;
    protected TextField txtEmail;
    protected TextField txtUserName;
    protected PasswordField txtPw;
    protected PasswordField txtPw_confirm;
    protected Label error;


    public RegisterView(Stage stage, RegisterModel model){
        super(stage, model);
    }

    public Scene create_GUI(){

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,416,372);
        GridPane grPa = new GridPane();



        // Settings and positioning of m GridPane

        grPa.setAlignment(Pos.CENTER);
        grPa.setHgap(10);
        grPa.setVgap(10);
        grPa.setPadding(new Insets(15, 0, 15, 0));

        setStylesheet(scene, REGISTER);

        stage.setScene(scene);

        // Creation of all needed Buttons
        this.btnRegister =new Button();
        this.btnRegister.setPrefSize(130,54);

        this.btnCancel = new Button();
        this.btnCancel.setPrefSize(130,54);

        // Creating of Button Images
        ImageView imgVbtnRegister = new ImageView(new Image(getClass().getResourceAsStream("/login/buttonWood.png")));
        imgVbtnRegister.setPreserveRatio(true);
        imgVbtnRegister.setFitWidth(130);

        ImageView imgVbtnCancel = new ImageView(new Image(getClass().getResourceAsStream("/login/buttonWood.png")));
        imgVbtnCancel.setPreserveRatio(true);
        imgVbtnCancel.setFitWidth(130);


        // Creation of HBox
        HBox hbImg = new HBox(10);
        hbImg.setAlignment(Pos.BOTTOM_CENTER);
        hbImg.getChildren().addAll(imgVbtnRegister, imgVbtnCancel);
        grPa.add(hbImg, 1, 5);


        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btnRegister);
        hbBtn.getChildren().add(btnCancel);
        grPa.add(hbBtn, 1, 5);


        // Creation of all needed TextFields
        txtEmail = new TextField();
        grPa.add(txtEmail,1,1);

        txtUserName=new TextField();
        grPa.add(txtUserName,1,2);

        txtPw = new PasswordField();
        grPa.add(txtPw,1,3);

        txtPw_confirm= new PasswordField();
        grPa.add(txtPw_confirm,1,4);


        // Create Title Register
        ImageView imgVRegisterEng = new ImageView(new Image(getClass().getResourceAsStream("/register/register_EN.png")));
        imgVRegisterEng.setPreserveRatio(true);
        imgVRegisterEng.setFitHeight(100);

        ImageView imgVRegisterDe = new ImageView(new Image(getClass().getResourceAsStream("/register/register_DE.png")));
        imgVRegisterDe.setPreserveRatio(true);
        imgVRegisterDe.setFitHeight(100);

        ImageView imgVRegisterCh = new ImageView(new Image(getClass().getResourceAsStream("/register/register_CH.png")));
        imgVRegisterCh.setPreserveRatio(true);
        imgVRegisterCh.setFitHeight(100);

        // Sets Title depending on Language
        Translator tr = ServiceLocator.getServiceLocator().getTranslator();
        final Language currentLanguage = tr.getCurrentLanguage();

        if (currentLanguage == Language.SWISS_GERMAN){
            root.setTop(imgVRegisterCh);
            root.setAlignment(imgVRegisterCh, Pos.CENTER);
            root.setMargin(imgVRegisterCh, new Insets(20,0,-40,0));
        } else if (currentLanguage == Language.ENGLISH) {
            root.setTop(imgVRegisterEng);
            root.setAlignment(imgVRegisterEng, Pos.CENTER);
            root.setMargin(imgVRegisterEng, new Insets(20,0,-40,0));
        } else {
            root.setTop(imgVRegisterDe);
            root.setAlignment(imgVRegisterDe, Pos.CENTER);
            root.setMargin(imgVRegisterDe, new Insets(20,0,-40,0));
        }


        root.setCenter(grPa);

        this.stage.setScene(scene);

        return scene;
    }

    // here are the texts set same like loginview etc...
    protected void setTexts() {

        this.stage.setTitle(getText("registerview.titel"));
        this.btnRegister.setText(getText("logginview.register"));
        this.btnCancel.setText(getText("btn.cancel"));
        txtEmail.setPromptText(getText("registerview.email"));
        txtPw.setPromptText(getText("registerview.password"));
        txtPw_confirm.setPromptText(getText("registerview.confirmation"));
        txtUserName.setPromptText(getText("registerview.username"));



    }




    void refreshModel() {
        this.model.setPassword(txtPw.getText());
        this.model.setPassword_confirm(txtPw_confirm.getText());
        this.model.setEmail(txtEmail.getText());
        this.model.setUserName((txtUserName.getText()));
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


