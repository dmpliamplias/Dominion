package connection;

import base.Controller;
import com.weddingcrashers.server.Server;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginModel;
import login.LoginView;
import util.PLServiceLocator;
import util.ServerConnectionService;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import static com.weddingcrashers.service.Language.ENGLISH;
import static com.weddingcrashers.service.Language.GERMAN;
import static com.weddingcrashers.service.Language.SWISS_GERMAN;
import static javafx.scene.layout.Region.USE_PREF_SIZE;


/**
 * @author Michel Schlatter
 * @author Vanessa Cajochen
 */
public class ConnectionController extends Controller<ConnectionModel, ConnectionView> {

    public ConnectionController(ConnectionModel model, ConnectionView view){
        super(model,view);
        initialize();
    }

    private  void initialize(){

        view.imgViewChFlag.setOnMouseClicked(e -> view.switchTranslator(SWISS_GERMAN));
        view.imgViewDeFlag.setOnMouseClicked(e -> view.switchTranslator(GERMAN));
        view.imgViewEngFlag.setOnMouseClicked(e -> view.switchTranslator(ENGLISH));

        view.btnStartS.setOnAction((ActionEvent event) -> {
            view.create_Dialog().show();

            view.btnConnect.setOnAction((ActionEvent event2) ->{
                String portStr = view.fldPort.getText();
                if(portStr == null || portStr.equals("")){
                    this.view.alert(translator.getString("ConnectionView_Error_PortEmpty"), Alert.AlertType.WARNING);
                    return;
                }
                int port = Integer.parseInt(portStr);
                // port must be between 1024-49151 and not 9092
                if (port < 1024 || port > 49151 || port == 9092) {
                    final Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(translator.getString("connectionview.portDialog.title"));
                    alert.setHeaderText(translator.getString("connectionview.portDialog.headerText"));
                    alert.setContentText(translator.getString("connectionview.portDialog.contentText"));
                    alert.getDialogPane().setMinHeight(USE_PREF_SIZE);
                    alert.showAndWait();
                    return;
                }
                InetSocketAddress socketAddress = createServer(port);
                view.btnConnect.setDisable(true);
                view.btnJoinS.setDisable(true);
                view.btnStartS.setDisable(true);

                // TODO: 14.10.2017 mschlatter: When typing port out of range NPE and programm shuts down
                String address = socketAddress.toString();
                System.out.println("Socketadress: " + address);

                try {
                    model.setIP(InetAddress.getLocalHost().getHostAddress());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
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
