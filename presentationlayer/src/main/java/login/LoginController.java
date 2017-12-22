package login;

import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.LoginContainer;
import com.weddingcrashers.servermodels.Methods;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import lobby.LobbyView;
import register.RegisterView;
import usermanagement.UserManagementView;
import util.ViewFactory;

import static javafx.scene.control.Alert.AlertType.WARNING;
import static util.PLServiceLocator.getPLServiceLocator;
import static util.ViewFactory.createLobbyView;
import static util.ViewFactory.createUserManagementView;

/**
 * @author Murat Kelleci
 */
public class LoginController extends Controller<LoginModel, LoginView> {

    public LoginController(LoginView view, LoginModel model) {
        super(model, view);
        serverConnectionService.setLoginController(this);
        initialize();
    }

    public void initialize() {
        // must not set ViewStatus on Server, Login is the first, so it's set on default.

        /**
         *  author Manuel Wirz
         *  Events for KeyCode Enter
         *  */

        view.user.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                view.refreshModel();
                login();
            }
        });


        view.pw.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                view.refreshModel();
                login();
            }
        });


        /**
         *  author Murat Kelleci
         *  */

        view.btnLogin.setOnAction((event) -> {
            view.refreshModel();
            this.login();
        });

        view.btnSignUp.setOnAction((event) -> this.goToRegisterView());

    }

    // dummy user login
    public void login() {
        view.refreshModel();
        // rules/guidelines to log in
        String pw = model.getPassword();
        String email = model.getEmail();

        if (pw != null && !pw.isEmpty() && email != null && !email.isEmpty()) {
            LoginContainer loginContainer = new LoginContainer(Methods.Login);
            loginContainer.setEmail(email);
            loginContainer.setPassword(pw);

            try {
                serverConnectionService.sendObject(loginContainer);
            } catch (Exception e) {
                view.simpleAlert(e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            view.alert("loginview.erroruncompletedfields", Alert.AlertType.WARNING);
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
                if (user.isBlocked()) {
                    view.alert("loginview.isBlocked", WARNING);
                    return;
                }
                plServiceLocator.setUser(user);
                if (user.isSuperUser() && getPLServiceLocator().getServerConnectionService().isHoster()) {
                    goToUserManagementView();
                } else {
                    goToLobbyView();
                }
            } else {
                //unsuccessfull login, show error
                view.alert("loginview.loginError", WARNING);
            }
        });
    }

    private void goToUserManagementView() {
        final Stage stage = new Stage();
        final UserManagementView userManagementView = createUserManagementView(stage);

        this.view.stop();
        userManagementView.start();
    }

    private void goToLobbyView() {
        Stage stage = new Stage();
        final LobbyView lobbyView = createLobbyView(stage);

        this.view.stop();
        lobbyView.start();
    }

    private void goToRegisterView() {
        final RegisterView registerView = ViewFactory.createRegisterView(this.view.getStage());

        this.view.stop();
        registerView.start();
    }

}
