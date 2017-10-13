package register;

import base.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

/**
 * @author Murat Kelleci - Update of View - Several changes
 */

public class RegisterView extends View<RegisterModel>{

    Button btnRegister;
    Button btnCancel;
    TextField txtEmail;
    TextField txtUserName;
    PasswordField txtPw;
    PasswordField txtPw_confirm;
    Label error;

    public RegisterView(Stage stage, RegisterModel model){
        super(stage, model);
    }

    public Scene create_GUI(){

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,600,500);
        GridPane grid = new GridPane();

        this.stage.setTitle("Register page");
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
        this.btnRegister.setText("Register");

        this.btnCancel = new Button();
        this.btnCancel.setPrefSize(130,40);
        this.btnCancel.setText("Cancel");


        // Creation of HBox
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btnRegister);
        hbBtn.getChildren().add(btnCancel);
        grid.add(hbBtn, 1, 5);


        // Creation of all needed TextFields
        Label Email= new Label("Email");
        grid.add(Email,0,1);
        txtEmail = new TextField();
        grid.add(txtEmail,1,1);

        Label pw=new Label("Password");
        grid.add(pw,0,2);
        txtPw = new PasswordField();
        grid.add(txtPw,1,2);

        Label pwconfirm=new Label("Password bestätigen");
        grid.add(pwconfirm,0,3);
        txtPw_confirm= new PasswordField();
        grid.add(txtPw_confirm,1,3);

        Label UserName = new Label("User Name");
        grid.add(UserName,0,4);
        txtUserName=new TextField();
        grid.add(txtUserName,1,4);


        root.setCenter(grid);


        stage.setScene(scene);
        return scene;
    }

    //TODO Murat anpassen
    protected void setTexts() {

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


