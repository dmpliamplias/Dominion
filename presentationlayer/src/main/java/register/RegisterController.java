package register;


import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.RegisterContainer;
import com.weddingcrashers.servermodels.ViewStatus;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import login.LoginController;
import login.LoginModel;
import login.LoginView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.io.IOException;

/**
 * @author Murat Kelleci  , Co-Author Michel Schlatter has fixed some bug fixes
 */

public class RegisterController extends Controller<RegisterModel, RegisterView> {

// Pattern-Definition E-Mail
    // https://stackoverflow.com/questions/8204680/java-regex-email
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public RegisterController(RegisterView view, RegisterModel model){
        // TODO: 30.09.2017 murat => super(model,view);
        super(model,view);
        serverConnectionService.setRegisterController(this);

        initialize();
    }

    private void initialize() {


        try {
            serverConnectionService.updateViewStatus(ViewStatus.Register); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }

        //TODO for Murat ActionEvent click on Cancel delete all fields

        this.view.btnRegister.setOnAction((event) -> {
            this.register();
            view.refreshModel();
        });
    }



    private void register() {
        String pw = this.view.txtPw.getText();
        String pw_confirm = this.view.txtPw_confirm.getText();
        String email = this.view.txtEmail.getText();
        String userName = this.view.txtUserName.getText();

        RegisterContainer registerContainer = new RegisterContainer();
        //registerContainer.setUser(null);//mit MIchel abklären

        if (!(pw.isEmpty() || pw.equals(pw_confirm) || !email.isEmpty() || !userName.isEmpty())) {

            User user = new User();
            if (validate(email)){
                user.setUserEmail(email);

            } else {
                setInfoMail();
            }

            user.setUserName(userName);
            user.setPassword(pw);
            registerContainer.setUser(user);

        }

        try {
            serverConnectionService.sendObject(registerContainer);
        } catch (Exception e) {
            view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    public void handleServerAnswer(RegisterContainer memberContainer){
        Platform.runLater(() -> {
            User user = memberContainer.getUser();

            if (user.getUserEmail()==null){
                setEmailExistsInfo();
            } else {

            if (user.getUserName() == null) {
                setUserExistsInfo();
            } else {
                goToLoginView();
            }
            }
        });
    }


    //TODO for Murat a Methode which verifies Email Username and PW1 with PW2

    //TODO for Murat Methode goToLoginView sauber fertig implementieren
    //@author Murat Kelleci -Credits Michel Schlatter from ConnectionController Class
    private void goToLoginView(){
        LoginModel model =new LoginModel();
        LoginView view =new LoginView(this.view.getStage(), model);
        LoginController loginController = new LoginController(view,model);
        this.view.stop();
        view.start();

    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    private void setError(){
        this.view.alert("setError", Alert.AlertType.WARNING);
    }

    private void setInfoMail(){
        this.view.alert("errorMail", Alert.AlertType.INFORMATION);
    }

    private void setUserExistsInfo(){

        this.view.alert("errorUser", Alert.AlertType.INFORMATION);

    }

    private void setEmailExistsInfo() {

        this.view.alert("Email Already Exists", Alert.AlertType.INFORMATION);

    }

}

