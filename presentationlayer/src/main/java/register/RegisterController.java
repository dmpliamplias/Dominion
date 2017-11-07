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

        this.view.btnRegister.setOnAction((event) -> {
            this.register();
        });

        this.view.btnCancel.setOnAction((event) ->{
            this.cancel();

        });
    }

    private void register() {
        view.refreshModel();
        String pw = model.getPassword();
        String pw_confirm = model.getPassword_confirm();
        String email = model.getEmail();
        String userName = model.getUserName();


        if (pw != null && !pw.isEmpty() && pw.equals(pw_confirm) && email != null && !email.isEmpty() && userName != null && !userName.isEmpty()) {

            RegisterContainer registerContainer = new RegisterContainer();

            User user = new User();
            if (validate(email)){
                user.setUserEmail(email);
                user.setUserName(userName);
                user.setPassword(pw);
                registerContainer.setUser(user);

                try {
                    serverConnectionService.sendObject(registerContainer);
                } catch (Exception e) {
                    view.alert(e.getMessage(), Alert.AlertType.ERROR);
                }


            } else {
                setInfoMail();
            }



        }else{

            view.alert(getText("registerview.error.uncompletedFields"), Alert.AlertType.WARNING);
        }

    }

    public void handleServerAnswer(RegisterContainer memberContainer){
        Platform.runLater(() -> {
            User user = memberContainer.getUser();

            if (user == null) {
                setUserExistsInfo();
            } else {
                goToLoginView();
            }

        });
    }

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

    public void cancel(){
        view.refreshModel();
        goToLoginView();

    }


    private void setError(){
        this.view.alert("setError", Alert.AlertType.WARNING);
    }

    private void setInfoMail(){
        this.view.alert("errorMail", Alert.AlertType.INFORMATION);
    }

    private void setUserExistsInfo(){

        this.view.alert("This User exists already!", Alert.AlertType.INFORMATION);

    }




}

