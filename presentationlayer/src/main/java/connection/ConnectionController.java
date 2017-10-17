package connection;

import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.server.Server;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginModel;
import login.LoginView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author Michel Schlatter
 * @author Vanessa Cajochen
 */
public class ConnectionController extends Controller<ConnectionModel, ConnectionView> {

    Translator _translator;

    public ConnectionController(ConnectionModel model, ConnectionView view){
        super(model,view);
        initialize();
        _translator = ServiceLocator.getServiceLocator().getTranslator();
    }


    private  void initialize(){

        view.imgViewChFlag.setOnMouseClicked((MouseEvent eventCh) -> {
            System.out.println("Clicked!");
        });

        view.imgViewDeFlag.setOnMouseClicked((MouseEvent eventDe) -> {
            System.out.println("Clicked!");
        });

        view.imgViewEngFlag.setOnMouseClicked((MouseEvent eventEng) -> {
            System.out.println("Clicked!");
        });


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

                // TODO: 14.10.2017 mschlatter: When typing port out of range NPE and programm shuts down
                String address = socketAddress.toString();
                System.out.println("Socketadress: " + address);

                model.setIP(socketAddress.getHostName());
                model.setPort(socketAddress.getPort());

                view.createConnectedDialog().show();
                view.refreshInfoDialog();

                view.btnCopyPort.setOnAction((event4)->{
                    CopytoCbPort();
                });

                view.btnCopyIP.setOnAction((event5)->{
                    CopytoCbIP();
                });

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


    public void CopytoCbPort (){
        String str = Integer.toString(model.getPort());
        StringSelection stringS = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringS, stringS);
    }

    public void CopytoCbIP (){
        String str = model.getIP();
        StringSelection stringS = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringS, stringS);
    }


    
}
