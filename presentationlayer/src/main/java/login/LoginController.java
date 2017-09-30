package login;

import app.PLServiceLocator;
import base.Controller;
import app.ServerConnectionService;
import com.sun.xml.internal.bind.v2.TODO;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.LoginContainer;
import com.weddingcrashers.service.ServiceLocator;
import gamestart.GameStartController;
import gamestart.GameStartModel;
import gamestart.GameStartView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;

/**
 *  @author Michel Schlatter
 *  */
public class LoginController extends Controller<LoginModel, LoginView>{

    private final ServerConnectionService _connection;

    public LoginController(LoginView view, LoginModel model){
        super(model, view);
        PLServiceLocator.getPLServiceLocator().getServerConnectionService().setLoginController(this);
        _connection = PLServiceLocator.getPLServiceLocator().getServerConnectionService();

        initialize();
    }


    public void initialize() {

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
               _connection.sendObject(loginContainer);
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
     GameStartModel model = new GameStartModel();
     GameStartView view = new GameStartView(this.view.getStage(), model);

     FXMLLoader loader = new FXMLLoader(getClass().getResource("gamestart/GameStartView.fxml"));
     loader.setController(new GameStartController(view, model, user));

     this.view.stop();
     view.start();
 }

 private void setError(){
      view.setLoginError();
 }



}
