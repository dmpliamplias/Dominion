package register;


import com.weddingcrashers.clientconnectionutils.ServerConnectionService;
import com.weddingcrashers.model.User;
import com.weddingcrashers.service.ServiceLocator;

/** @author Murat Kelleci
 *
 */

public class RegisterController {

        private final RegisterView view;
        private final RegisterModel model;
        private final ServerConnectionService connection;

        public RegisterController(RegisterView view, RegisterModel model){
            this.view = view;
            this.model = model;
            this.connection=ServiceLocator.getServiceLocator().getServerConnectionService();


            initialize();
        }

        public void initialize() {
            this.view.register.setOnAction((event) -> {
                this.view.refreshModel();
                this.register();
            });
        }
    public void register(){
        String pw = this.view.pw.getText();
        String pw_confirm=this.view.pw_confirm.getText();
        String email = this.view.email.getText();

        if (pw !=pw_confirm){

           User user =  ServiceLocator.getUserService().getUserByEmail(email);
           if(user == null){
               //über user UserService Kreiieren
           }else{
               this.model.setError(ServiceLocator.getServiceLocator().
                       getTranslator().getString("RegisterView_Error_EmailAlreadyExists"));
           }
            // TODO: 29.09.17  Eine Methode die überprüft ob die Emailadresse schon verwendet wird = Abgleich mit DB.
            // TODO: 29.09.17  Eine Methode die mein PW mit meinem PW_Confirm vergleicht. Abgleich Feld Pw und Pw_Confirm
                }
            }

        public void setError() {
            this.view.setLoginError();
        }



    }

