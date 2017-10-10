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

            });
        }

    public void register() {
        view.refreshModel();
        String pw = model.getPassword();
        String pw_confirm = model.getPassword_confirm();
        String email = model.getEmail();
        String userName = model.getUserName();

        // TODO: 10.10.2017  murat => pw.equals(pw_confirm) da string ein objekt und ein objekt immer ein referenztyp ist, immer mit equals
        // vergleichen ansonsten prüft du nur ob die speicheradresse die selbe ist und nicht den inhalt davon.

        if (pw != null && !pw.isEmpty() && pw.equals(pw_confirm)
                && email != null && !email.isEmpty() &&
                userName != null && !userName.isEmpty()) {

            // TODO: 10.10.2017 murat validate email
            User user = new User();
            user.setUserEmail(email);
            user.setUserName(userName);
            user.setPassword(pw);
            RegisterContainer registerContainer = new RegisterContainer();
            registerContainer.setUser(user);

            try {
                serverConnectionService.sendObject(registerContainer);
            } catch (IOException e) {
                view.alert(e.getMessage(), Alert.AlertType.CONFIRMATION.ERROR);
            }
        }else{
            // TODO: 10.10.2017 murat => text in translator einfügen.
            view.alert("Bitte alle Felder ausfüllen.", Alert.AlertType.INFORMATION);
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

    private void goToLoginView(){
        LoginModel model =new LoginModel();
        LoginView view =new LoginView(this.view.getStage(), model);
        LoginController loginController = new LoginController(view,model);
        this.view.stop();
        view.start();
    }

    }

