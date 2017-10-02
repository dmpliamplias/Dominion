package register;


import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.servermodels.ViewStatus;


import java.io.IOException;

/**
 * @author Murat Kelleci
 */

public class RegisterController extends Controller<RegisterModel, RegisterView> {

    private final ServerConnectionService _serverConnection;

    public RegisterController(RegisterView view, RegisterModel model){
            // TODO: 30.09.2017 murat => super(model,view);
        super(model,view);
        _serverConnection = PLServiceLocator.getPLServiceLocator().getServerConnectionService();
        PLServiceLocator.getPLServiceLocator().getServerConnectionService().setRegisterController(this);

        initialize();
        }

    public void initialize() {
        try {
            _serverConnection.updateViewStatus(ViewStatus.Lobby); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
        this.view.register.setOnAction((event) -> {
            this.view.refreshModel();
            this.register();
            });
        }

    public void register() {
        String pw = this.view.pw.getText();
        String pw_confirm = this.view.pw_confirm.getText();
        String email = this.view.email.getText();

    }
        public void setError() {
            //this.model.setLoginError();
        }



    }

