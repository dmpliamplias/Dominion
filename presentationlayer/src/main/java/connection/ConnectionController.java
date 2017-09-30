package connection;

import base.Controller;
import com.weddingcrashers.service.ServerConnectionService;
import com.weddingcrashers.service.ServiceLocator;

import java.io.IOException;

public class ConnectionController extends Controller<ConnectionModel, ConnectionView> {

    public ConnectionController(ConnectionModel model, ConnectionView view){
        super(model,view);
    }
    public void connect(String url, int port){
        try {
            ServiceLocator.getServiceLocator().setServerConnectionService(new ServerConnectionService(url,port));
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
    }
}
