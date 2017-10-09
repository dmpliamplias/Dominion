package connection;

import app.PLServiceLocator;
import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.server.Server;
import app.ServerConnectionService;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lobby.LobbyController;
import lobby.LobbyModel;
import lobby.LobbyView;
import login.LoginController;
import login.LoginModel;
import login.LoginView;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author  Michel Schlatter & Vanessa Cajochen
 */
public class ConnectionController extends Controller<ConnectionModel, ConnectionView> {

    Translator _translator;

    public ConnectionController(ConnectionModel model, ConnectionView view){
        super(model,view);
        initialize();
        _translator = ServiceLocator.getServiceLocator().getTranslator();
    }


    private  void initialize(){

        view.btnStartS.setOnAction((event) -> {
            view.create_Dialog().show();

            view.btnConnect.setOnAction((event2)->{
                String portStr = view.fldPort.getText();
                if(portStr == null || portStr.equals("")){
                    this.view.alert(_translator.getString("ConnectionView_Error_PortEmpty"), Alert.AlertType.WARNING);
                    return;
                }
                int port = Integer.parseInt(portStr);
                InetSocketAddress socketAddress = createServer(port);
                view.btnConnect.setDisable(true);
                view.btnJoinS.setDisable(true);
                view.btnStartS.setDisable(true);

                String address = socketAddress.toString();
                System.out.println("Socketadress: " + address);

                model.setIP(socketAddress.getHostName());
                model.setPort(socketAddress.getPort());

                view.createConnectedInfoDialog().show();
                view.refreshInfoDialog();

                 view.btnOK.setOnAction((event3) -> {
                    goToLoginView();
                });
            });
        });

        view.btnJoinS.setOnAction((event) -> {
          Stage stage =  view.createJoinDialog();
              view.btnOK.setOnAction((event2) ->{
                  view.refreshModelFromInfoDialog();
                  this.join(model.getIP(), model.getPort(),false);
                  goToLoginView();
              });
              stage.show();
        });

    }
    /**
     *@author Michel Schlatter
     * @param port
     * @return ip address
     */
    private InetSocketAddress createServer(int port){
        try {
            Server.startServer(port,4);
            join("localhost", port,true);
            return ((InetSocketAddress)PLServiceLocator.getPLServiceLocator()
                    .getServerConnectionService().getConnection().getRemoteSocketAddress());
        } catch (Exception e) {
            int i = 1;
            this.view.alert(e.getMessage(), Alert.AlertType.ERROR);
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
            if(PLServiceLocator.getPLServiceLocator().getServerConnectionService() == null) {
                ServerConnectionService serverConnectionService = new ServerConnectionService(url, port);
                PLServiceLocator.getPLServiceLocator().setServerConnectionService(serverConnectionService);
                PLServiceLocator.getPLServiceLocator().getServerConnectionService().setConnectionController(this);
                PLServiceLocator.getPLServiceLocator().getServerConnectionService().setIsHoster(isHoster);
            }
        } catch (IOException e) {
            this.view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private void goToLoginView(){
        LoginModel model = new LoginModel();
        LoginView view = new LoginView(this.view.getStage(), model);
        LoginController loginController = new LoginController(view,model);

        this.view.stop();
        view.start();
    }
    
}
