package register;


import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.RegisterContainer;
import com.weddingcrashers.servermodels.ViewStatus;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.UserService;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import login.LoginController;
import login.LoginModel;
import login.LoginView;

import java.io.IOException;

/**
 * @author Murat Kelleci
 */

public class RegisterController extends Controller<RegisterModel, RegisterView> {


    public RegisterController(RegisterView view, RegisterModel model){
            // TODO: 30.09.2017 murat => super(model,view);
        super(model,view);
        serverConnectionService.setRegisterController(this);

        initialize();
        }

    public void initialize() {
        // TODO: 10.10.2017  murat due das unskommentierte wieder ine? wieso hesches usegno?
        try {
            serverConnectionService.updateViewStatus(ViewStatus.Register); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }

        //TODO for Murat ActionEvent click on Cancel delete all fields

        this.view.btnRegister.setOnAction((event) -> {
            this.register();
            view.refreshModel();
            });
        }

    public void register() {
        String pw = this.view.txtPw.getText();
        String pw_confirm = this.view.txtPw_confirm.getText();
        String email = this.view.txtEmail.getText();
        String userName = this.view.txtUserName.getText();

        if (!(pw.isEmpty() || pw == pw_confirm || email.isEmpty() || userName.isEmpty())) ;
        User user = new User();
        user.setUserEmail(email);
        user.setUserName(userName);
        user.setPassword(pw);
        RegisterContainer registerContainer = new RegisterContainer();
        registerContainer.setUser(user);


        try {
            serverConnectionService.sendObject(registerContainer);
        } catch (Exception e) {
            view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    public void handleServerAnswer(RegisterContainer member){
        Platform.runLater(() -> {
            User user = member.getUser();
            if(user != null){
                goToLoginView();
            }
        });
    }


        //TODO for Murat a Methode which verifies Email Username and PW1 with PW2

        //TODO for Murat Methode goToLoginView sauber fertig implementieren

    private void goToLoginView(){
        LoginModel model =new LoginModel();
        LoginView view =new LoginView(this.view.getStage(), model);
        LoginController loginController = new LoginController(view,model);
        this.view.stop();
        view.start();

    }

        private void setError(){
            view.setError("error");
}



    }

