package register;


import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.servermodels.ViewStatus;
import com.weddingcrashers.validation.UserValidation;
import com.weddingcrashers.validation.ValidationResult;

import java.io.IOException;

/**
 * @author Murat Kelleci
 */
// TODO: 30.09.2017 murat extends Controler<..,..> siehe ConnectionController => mache dies auch bei Models und View
public class RegisterController extends Controller<RegisterModel, RegisterView> {

    private final ServerConnectionService connection;

    public RegisterController(RegisterView view, RegisterModel model){
            // TODO: 30.09.2017 murat => super(model,view);
        super(model,view);
        this.connection= PLServiceLocator.getPLServiceLocator().getServerConnectionService();

        initialize();
        }

    public void initialize() {
        try {
            connection.updateViewStatus(ViewStatus.Lobby); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
        this.view.register.setOnAction((event) -> {
            this.view.refreshModel();
            this.register();
            });
        }
    public void register(){
        String pw = this.view.pw.getText();
        String pw_confirm = this.view.pw_confirm.getText();
        String email = this.view.email.getText();

        if (!pw.equals(pw_confirm)) {
            final UserValidation userValidation = new UserValidation(email);
            final ValidationResult validate = userValidation.validate();
            switch (validate) {
                case OK:
                    break;
                case EMAIL_ALREADY_EXISTS:
                    break;
                default:
                    break;
            }
            // TODO: 29.09.17  Eine Methode die überprüft ob die Emailadresse schon verwendet wird = Abgleich mit DB.
            // TODO: 29.09.17  Eine Methode die mein PW mit meinem PW_Confirm vergleicht. Abgleich Feld Pw und Pw_Confirm
                }
            }

        public void setError() {
            this.view.setLoginError();
        }



    }

