package connection;

import base.Controller;
import com.weddingcrashers.server.Server;
import com.weddingcrashers.service.ServerConnectionService;
import com.weddingcrashers.service.ServiceLocator;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;

/**
 * @author  Michel Schlatter & Vanessa Cajochen
 */
public class ConnectionController extends Controller<ConnectionModel, ConnectionView> {

    public ConnectionController(ConnectionModel model, ConnectionView view){
        super(model,view);
    }

    /**
     *
     * @param port
     * @return ip address
     */
    public SocketAddress createServer(int port){
        try {
            // TODO: 30.09.2017 Dyoni, add server to presentation service locator.
            Server.startServer(port,4);
            join("localhost", port);
            // TODO: 30.09.2017  vannessa, teste ob das geht und anzeigen! (inkl. port)
            return ServiceLocator.getServiceLocator().getServerConnectionService().getConnection().getRemoteSocketAddress();
        } catch (Exception e) {
            this.view.alert(e.getMessage());
        }
        return null;
    }

    public void join(String url, int port){
        try {
            ServiceLocator.getServiceLocator().setServerConnectionService(new ServerConnectionService(url,port));
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
    }
}
