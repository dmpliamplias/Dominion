package login;

import app.PLServiceLocator;
import base.Controller;
import app.ServerConnectionService;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.LoginContainer;
import com.weddingcrashers.servermodels.Methods;
import com.weddingcrashers.service.ServiceLocator;
import javafx.scene.control.Alert;
import lobby.LobbyController;
import lobby.LobbyModel;
import lobby.LobbyView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import register.RegisterController;
import register.RegisterModel;
import register.RegisterView;

import java.io.IOException;

/**
 *  @author Michel Schlatter
 *  */
public class LoginController extends Controller<LoginModel, LoginView>{


    public LoginController(LoginView view, LoginModel model){
        super(model, view);
        serverConnectionService.setLoginController(this);
        initialize();
    }

    //TODO for Murat create an ActionEvent when clicking on Register button
    public void initialize() {
        // must not set ViewStatus on Server, Login is the first, so it's set on default.

        view.btnLogin.setOnAction((event) -> {
           view.refreshModel();
           this.login();

       }); //** @author Murat Kelleci

        view.btnSignUp.setOnAction((event)-> this.goToRegisterView());

    }

  public void login(){
       view.refreshModel();
       if(model.getEmail().equals("go")){
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
       }else {
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

  public void handleServerAnswer_TestPurposeLogin(LoginContainer lc){
      Platform.runLater(()->{
          plServiceLocator.setUser(lc.getUser());
          goToLobbyView();
      });
  }

  public void handleServerAnswer(LoginContainer result){
      Platform.runLater(() -> {
          User user = result.getUser();
          if(user != null){ // success
              plServiceLocator.setUser(user);
              goToLobbyView();
          }else{
              //unsuccessfull login, show error
              view.alert(translator.getString("loginview.loginError"), Alert.AlertType.WARNING);
          }
      });
  }

  private void goToLobbyView(){
     LobbyModel model = new LobbyModel();
     LobbyView view = new LobbyView(this.view.getStage(), model);
     LobbyController lobbyController = new LobbyController(view, model);

     this.view.stop();
     view.start();
 }
    //** @author Murat Kelleci - on 8.10.17
    //TODO for Murat Methode sauber fertig implementieren.
    private void goToRegisterView() {
        RegisterModel model = new RegisterModel();
        RegisterView view = new RegisterView(this.view.getStage(), model);
        // TODO: 10.10.2017 Murat...hier hast du den RegisterController vergessen zu instanzieren
        RegisterController registerController = new RegisterController(view,model);
        this.view.stop();
        view.start();
    }



}
