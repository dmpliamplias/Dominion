package register;


import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.RegisterContainer;
import com.weddingcrashers.servermodels.ViewStatus;
import com.weddingcrashers.service.UserService;
import javafx.scene.control.Alert;
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
        PLServiceLocator.getPLServiceLocator().getServerConnectionService().setRegisterController(this);

        initialize();
        }

    public void initialize() {
        try {
            serverConnectionService.updateViewStatus(ViewStatus.Login); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }

        //TODO for Murat ActionEvent click on Cancel delete all fields

        this.view.btnRegister.setOnAction((event) -> {
            view.refreshModel();
            this.register();
            });
        }

    public void register() {
        String pw = this.view.txtPw.getText();
        String pw_confirm = this.view.txtPw_confirm.getText();
        String email = this.view.txtEmail.getText();
        String userName = this.view.txtUserName.getText();

        if(!(pw.isEmpty() || pw==pw_confirm|| email.isEmpty() || userName.isEmpty()));
            RegisterContainer registerContainer = new RegisterContainer();


    }

        //TODO for Murat a Methode which verifies Email Username and PW1 with PW2

        //TODO for Murat Methode goToLoginView sauber fertig implementieren

    private void goToLoginView(){
        LoginModel model =new LoginModel();
        LoginView view =new LoginView(this.view.getStage(), model);
        this.view.stop();
        view.start();

    }

        public void setError() {
            //this.model.setLoginError();
        }



    }

