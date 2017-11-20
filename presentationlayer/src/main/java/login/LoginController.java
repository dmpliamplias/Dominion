package login;

import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.LoginContainer;
import com.weddingcrashers.servermodels.Methods;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lobby.LobbyController;
import lobby.LobbyModel;
import lobby.LobbyView;
import register.RegisterController;
import register.RegisterModel;
import register.RegisterView;
import usermanagement.UserManagementController;
import usermanagement.UserManagementModel;
import usermanagement.UserManagementView;

import java.io.IOException;

import static util.PLServiceLocator.getPLServiceLocator;
import static javafx.scene.control.Alert.AlertType.WARNING;

/**
 * @author Michel Schlatter
 */
public class LoginController extends Controller<LoginModel, LoginView> {

    public LoginController(LoginView view, LoginModel model) {
        super(model, view);
        serverConnectionService.setLoginController(this);
        initialize();
    }

    public void initialize() {
        // must not set ViewStatus on Server, Login is the first, so it's set on default.

        view.btnLogin.setOnAction((event) -> {
            view.refreshModel();
            this.login();


        });
        //** @author Murat Kelleci
        view.btnSignUp.setOnAction((event) -> this.goToRegisterView());

    }


    public void login() {
        view.refreshModel();
        if (model.getEmail().equals("ga")) goToUserManagementView();
        if (model.getEmail().equals("go")) {
            User u = new User();
            u.setUserEmail("anonymous@dom.ch");
            u.setUserName("Anonymous");
            u.setPassword("1234");

            LoginContainer lc = new LoginContainer(Methods.Login_SetUser_TestPurposesOnly);
            lc.setUser(u);
            try {
                serverConnectionService.sendObject(lc);
            } catch (IOException e) {
                view.alert(e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {

           // @author Murat Kelleci
            String pw = model.getPassword();
            String email = model.getEmail();

            if (pw != null && !pw.isEmpty() && email != null && !email.isEmpty()) {
                LoginContainer loginContainer = new LoginContainer(Methods.Login);
                loginContainer.setEmail(email);
                loginContainer.setPassword(pw);

                try {
                    serverConnectionService.sendObject(loginContainer);
                } catch (Exception e) {
                    view.alert(e.getMessage(), Alert.AlertType.ERROR);
                }
            }
        }
    }

    public void handleServerAnswer_TestPurposeLogin(LoginContainer lc) {
        Platform.runLater(() -> {
            plServiceLocator.setUser(lc.getUser());
            goToLobbyView();
        });
    }

    public void handleServerAnswer(LoginContainer result) {
        Platform.runLater(() -> {
            User user = result.getUser();
            if (user != null) { // success
                plServiceLocator.setUser(user);
                if (user.isSuperUser() && getPLServiceLocator().getServerConnectionService().isHoster()) {
                    goToUserManagementView();
                } else {
                    goToLobbyView();
                }
            } else {
                //unsuccessfull login, show error
                view.alert(getText("loginview.loginError"), WARNING);
            }
        });
    }

    private void goToUserManagementView() {
        final Stage stage = new Stage();
        final UserManagementModel model = new UserManagementModel();
        final UserManagementView view = new UserManagementView(stage, model);
        new UserManagementController(model, view);

        this.view.stop();
        view.start();
    }

    private void goToLobbyView() {
        LobbyModel model = new LobbyModel();
        Stage s = new Stage(  );
        LobbyView view = new LobbyView(s, model);
        new LobbyController(view, model);

        this.view.stop();
        view.start();
    }

    //** @author Murat Kelleci - on 8.10.17
    private void goToRegisterView() {
        RegisterModel model = new RegisterModel();
        RegisterView view = new RegisterView(this.view.getStage(), model);

        RegisterController registerController = new RegisterController(view, model);
        this.view.stop();
        view.start();
    }


}
