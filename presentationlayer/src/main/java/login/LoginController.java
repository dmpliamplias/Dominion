package login;

import com.weddingcrashers.service.ServerConnectionService;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.LoginContainer;
import com.weddingcrashers.service.ServiceLocator;
import gamestart.GameStartController;
import gamestart.GameStartModel;
import gamestart.GameStartView;
import javafx.fxml.FXMLLoader;

/**
 *  @author Michel Schlatter
 *  */
public class LoginController {

    private final LoginView _view;
    private final LoginModel _model;
    private final ServerConnectionService _connection;

    public LoginController(LoginView view, LoginModel model){
        _view = view;
        _model = model;
        _connection = ServiceLocator.getServiceLocator().getServerConnectionService();

        initialize();
    }


    public void initialize() {
       _view.login.setOnAction((event) -> {
           _view.refreshModel();
           this.login();
       });
    }

  public void login(){
       String pw = _view.pw.getText();
       String email = _view.pw.getText();

       if(pw != null && !pw.isEmpty() && email != null && !email.isEmpty()){
           LoginContainer loginContainer = new LoginContainer();
           loginContainer.setEmail(email);
           loginContainer.setPassword(pw);

           try {
               _connection.sendObject(loginContainer);
               LoginContainer result = _connection.<LoginContainer>receiveObject();
               User user = result.getUser();
               if(user != null){ // success
                   goToStartView(user);
               }else{
                   //unsuccessfull login, show error
                   _model.setError(ServiceLocator.getServiceLocator().getTranslator().getString("LoginView_LoginError"));
                   _view.setLoginError();
               }
           } catch (Exception e) {
               e.printStackTrace();
           }


       }
  }

  // change to goToWhatEverView ...
  private void goToStartView(User user){
     GameStartModel model = new GameStartModel();
     GameStartView view = new GameStartView(_view.getStage(), model);

     FXMLLoader loader = new FXMLLoader(getClass().getResource("gamestart/GameStartView.fxml"));
     loader.setController(new GameStartController(view, model, user));

     this._view.stop();
     view.start();
 }

 private void setError(){
      _view.setLoginError();
 }



}
