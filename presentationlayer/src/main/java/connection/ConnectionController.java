package connection;

import app.PLServiceLocator;
import base.Controller;
import com.weddingcrashers.server.Server;
import app.ServerConnectionService;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.SocketAddress;

/**
 * @author  Michel Schlatter & Vanessa Cajochen
 */
public class ConnectionController extends Controller<ConnectionModel, ConnectionView> {

    public ConnectionController(ConnectionModel model, ConnectionView view){
        super(model,view);
        initialize();
    }


    private  void initialize(){

        view.startS.setOnAction((event) -> {
            view.create_Dialog();

            view.btnConnect.setOnAction((event2)->{
                int port = Integer.parseInt(view.fldPort.getText());
                createServer(port);
            });});


    }
    /**
     *@author Michel Schlatter
     * @param port
     * @return ip address
     */
    private SocketAddress createServer(int port){
        try {
            Server.startServer(port,4);
            join("localhost", port,true);
            // TODO: 30.09.2017  vannessa, teste ob das geht und anzeigen! (inkl. port)
            return PLServiceLocator.getPLServiceLocator().getServerConnectionService().getConnection().getRemoteSocketAddress();
        } catch (Exception e) {
            int i = 1;
            this.view.alert(e.getMessage());
        }
        return null;
    }

    /**
     * @author Michel Schlatter
     * @param url
     * @param port
     */
    private void join(String url, int port, boolean isHoster){
        try {
            ServerConnectionService serverConnectionService = new ServerConnectionService(url,port);
            PLServiceLocator.getPLServiceLocator().setServerConnectionService(serverConnectionService);
            PLServiceLocator.getPLServiceLocator().getServerConnectionService().setConnectionController(this);
            PLServiceLocator.getPLServiceLocator().getServerConnectionService().setIsHoster(isHoster);
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
    }

    
}
