package login;

import app.PLServiceLocator;
import base.Controller;
import app.ServerConnectionService;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.LoginContainer;
import com.weddingcrashers.service.ServiceLocator;
import lobby.LobbyController;
import lobby.LobbyModel;
import lobby.LobbyView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

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


    public void initialize() {
        // must not set ViewStatus on Server, Login is the first, so it's set on default.

        view.login.setOnAction((event) -> {
           view.refreshModel();
           this.login();
       });
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
              view.alert(e.getMessage());
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

 private void setError(){
      view.setLoginError();
 }



}
