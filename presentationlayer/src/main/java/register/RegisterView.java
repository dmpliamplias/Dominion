package register;

import base.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;

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

    private Label lblEmail;
    private Label lblPw;
    private Label lblPwconfirm;
    private Label lblUserName;

    public RegisterView(Stage stage, RegisterModel model){
        super(stage, model);
    }

    public Scene create_GUI(){

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,600,500);
        GridPane grid = new GridPane();


        this.stage.setWidth(800);
        this.stage.setHeight(500);

        // Settings and positioning of m GridPane

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        stage.setScene(scene);

        // Creation of all needed Buttons
        this.btnRegister =new Button();
        this.btnRegister.setPrefSize(130,40);


        this.btnCancel = new Button();
        this.btnCancel.setPrefSize(130,40);



        // Creation of HBox
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btnRegister);
        hbBtn.getChildren().add(btnCancel);
        grid.add(hbBtn, 1, 5);


        // Creation of all needed TextFields
        lblEmail= new Label();
        grid.add(lblEmail,0,1);
        txtEmail = new TextField();
        grid.add(txtEmail,1,1);

        lblPw=new Label();
        grid.add(lblPw,0,2);
        txtPw = new PasswordField();
        grid.add(txtPw,1,2);

        lblPwconfirm=new Label();
        grid.add(lblPwconfirm,0,3);
        txtPw_confirm= new PasswordField();
        grid.add(txtPw_confirm,1,3);

        lblUserName = new Label();
        grid.add(lblUserName,0,4);
        txtUserName=new TextField();
        grid.add(txtUserName,1,4);


        root.setCenter(grid);

        this.stage.setScene(scene);

        setTexts();
        return scene;
    }

    //TODO Murat anpassen

    protected void setTexts() {

        this.stage.setTitle(getText("registerview.titel"));
        this.btnRegister.setText(getText("logginview.register"));
        this.btnCancel.setText(getText("btn.cancel"));
        this.lblEmail.setText(getText("registerview.email"));
        this.lblPw.setText(getText("registerview.password"));
        this.lblPwconfirm.setText(getText("registerview.confirmation"));
        this.lblUserName.setText(getText("registerview.username"));

    }

//    @Override
//    protected HashMap<Labeled, String> labeledToKeyMap() {
//        final HashMap<Labeled, String> map = new HashMap<>();
//        map.put(btnRegister, "Register");
//        map.put(btnCancel, "cancel");
//        map.put(lblEmail, "Email");
//        map.put(lblPw, "Password");
//        map.put(lblPwconfirm, "Password_Confirmation");
//        map.put(lblUserName, "Username");
//        return map;
//    }


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


