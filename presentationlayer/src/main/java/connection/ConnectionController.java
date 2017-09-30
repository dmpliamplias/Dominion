package connection;

import app.PLServiceLocator;
import base.Controller;
import com.weddingcrashers.server.Server;
import app.ServerConnectionService;
import com.weddingcrashers.service.ServiceLocator;

import java.io.IOException;
import java.net.SocketAddress;

/**
 * @author  Michel Schlatter & Vanessa Cajochen
 */
public class ConnectionController extends Controller<ConnectionModel, ConnectionView> {

    public ConnectionController(ConnectionModel model, ConnectionView view){
        super(model,view);
        PLServiceLocator.getPLServiceLocator().getServerConnectionService().setConnectionController(this);
    }

    /**
     *@author Michel Schlatter
     * @param port
     * @return ip address
     */
    private SocketAddress createServer(int port){
        try {
            Server.startServer(port,4);
            join("localhost", port);
            // TODO: 30.09.2017  vannessa, teste ob das geht und anzeigen! (inkl. port)
            return PLServiceLocator.getPLServiceLocator().getServerConnectionService().getConnection().getRemoteSocketAddress();
        } catch (Exception e) {
            this.view.alert(e.getMessage());
        }
        return null;
    }

    /**
     * @author Michel Schlatter
     * @param url
     * @param port
     */
    private void join(String url, int port){
        try {
            PLServiceLocator.getPLServiceLocator().setServerConnectionService(new ServerConnectionService(url,port));
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
    }
}
