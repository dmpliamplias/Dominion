import com.weddingcrashers.model.User;
import com.weddingcrashers.repositories.UserRepo;
import gamestart.GameStartController;
import gamestart.GameStartModel;
import gamestart.GameStartView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class LoginController {

    private final LoginView _view;
    private final LoginModel _model;

    public LoginController(LoginView view, LoginModel model){
        _view = view;
        _model = model;

        initialize();
    }

    @FXML
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
           final UserRepo repo = new UserRepo();
           User user = repo.getUserByEmailAndPw(email, pw);

           if(user != null){ // successfull login
               goToStartView(user.getId());
           }
       }
  }

  private void goToStartView(long userId){
     GameStartModel model = new GameStartModel();
     GameStartView view = new GameStartView(_view.getStage(), model);

     FXMLLoader loader = new FXMLLoader(getClass().getResource("gamestart/GameStartView.fxml"));
     loader.setController(new GameStartController(view, model,  (int)userId));

     this._view.stop();
     view.start();
 }

 private void setError(){
      _view.setLoginError();
 }



}
