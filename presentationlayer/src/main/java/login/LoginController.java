package login;

import app.PLServiceLocator;
import base.Controller;
import app.ServerConnectionService;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.LoginContainer;
import com.weddingcrashers.service.ServiceLocator;
import javafx.scene.control.Alert;
import lobby.LobbyController;
import lobby.LobbyModel;
import lobby.LobbyView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import register.RegisterModel;
import register.RegisterView;

/**
 *  @author Michel Schlatter
 *  */
public class LoginController extends Controller<LoginModel, LoginView>{

    private final ServerConnectionService _serverConnection;

    public LoginController(LoginView view, LoginModel model){
        super(model, view);
        PLServiceLocator.getPLServiceLocator().getServerConnectionService().setLoginController(this);
        _serverConnection = PLServiceLocator.getPLServiceLocator().getServerConnectionService();

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
       String pw = view.pw.getText();
       String email = view.pw.getText();

       if(pw != null && !pw.isEmpty() && email != null && !email.isEmpty()){
           LoginContainer loginContainer = new LoginContainer();
           loginContainer.setEmail(email);
           loginContainer.setPassword(pw);

           try {
               _serverConnection.sendObject(loginContainer);
           } catch (Exception e) {
              view.alert(e.getMessage(), Alert.AlertType.ERROR);
           }
       }
  }

  public void handleServerAnswer(LoginContainer result){
      Platform.runLater(() -> {
          User user = result.getUser();
          if(user != null){ // success
              goToStartView(user);
          }else{
              //unsuccessfull login, show error
              // TODO: Vanessa add translator code
              model.setError(ServiceLocator.getServiceLocator().getTranslator().getString("LoginView_LoginError"));
              view.setLoginError();
          }
      });
  }

  // change to goToWhatEverView ...
  private void goToStartView(User user){
     LobbyModel model = new LobbyModel();
     LobbyView view = new LobbyView(this.view.getStage(), model);

     FXMLLoader loader = new FXMLLoader(getClass().getResource("gamestart/LobbyView.fxml"));
     loader.setController(new LobbyController(view, model, user));

     this.view.stop();
     view.start();
 }
    //** @author Murat Kelleci - on 8.10.17
    //TODO for Murat Methode sauber fertig implementieren.
    private void goToRegisterView() {
        RegisterModel model = new RegisterModel();
        RegisterView view = new RegisterView(this.view.getStage(), model);

        this.view.stop();
        view.start();
    }

 private void setError(){
      view.setLoginError();
 }



}
