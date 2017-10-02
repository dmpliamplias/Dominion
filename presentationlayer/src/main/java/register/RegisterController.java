package register;


import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.servermodels.ViewStatus;


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
        RegisterValidator regValidator = new RegisterValidator();

        String message = regValidator.validateModel(this.model);

        if(message != null){
            // Ich würde die Error-Message nicht im Model speichern. Sondern der View direkt übergeben und so anzeigen lassen.
            // this.model.setError(message);
            //setError();
            this.model.setError(message);
            setError();
        }else {
            this.view.clearErrorText();
        }
            }
            // TODO: 29.09.17  Eine Methode die überprüft ob die Emailadresse schon verwendet wird = Abgleich mit DB.
            // TODO: 29.09.17  Eine Methode die mein PW mit meinem PW_Confirm vergleicht. Abgleich Feld Pw und Pw_Confirm
                }
            }

        public void setError() {
            this.model.setLoginError();
        }



    }

