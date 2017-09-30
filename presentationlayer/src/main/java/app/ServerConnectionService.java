package app;

import com.weddingcrashers.servermodels.*;
import connection.ConnectionController;
import gamestart.GameStartController;
import login.LoginController;
import register.RegisterController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *  @author Michel Schlatter
 *  */
public class ServerConnectionService extends Thread{
    private  Socket _connection;
    private int _clientId;

    public ServerConnectionService(String url, int port) throws IOException{
        _connection = new Socket(url,port);

        try {
            _clientId = this.<ConnectionContainer>receiveObject().getId();
        }catch(ClassNotFoundException ex){}
    }

    public Socket getConnection() {
        return _connection;
    }

    public int get_clientId() {
        return _clientId;
    }


    @Override
    public void run() {
        while(true){
            try {
                Container c = this.<Container>receiveObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public  <T extends Container> void  sendObject(T object) throws  IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(_connection.getOutputStream());
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }

    private <T extends  Container> T receiveObject() throws  IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(_connection.getInputStream());
        return (T)objectInputStream.readObject();
    }

    public void updateViewStatus(ViewStatus status) throws IOException{
        ViewStatusUpdateContainer vc = new ViewStatusUpdateContainer();
        vc.setViewStatus(status);
        sendObject(vc);
    }

    //-------------------------- Controller Communications -----------------------------------------------

    private void runMethod(Container c){
        if(c.getMethod() == Methods.Login){
            loginController.handleServerAnswer((LoginContainer)c);
        }else if(c.getMethod() == Methods.Chat){
            // Manu run in your Controller, but with Platform.runLater(()->{}) because you're not on javafx thread anymore...
        }else if(c.getMethod() == Methods.Client_Server_Error){
            // handle error here...
        }
    }

    ConnectionController connectionController;
    GameStartController gameStartController;
    LoginController loginController;
    RegisterController registerController;


    public ConnectionController getConnectionController() {
        return connectionController;
    }

    public void setConnectionController(ConnectionController connectionController) {
        this.connectionController = connectionController;
    }

    public GameStartController getGameStartController() {
        return gameStartController;
    }

    public void setGameStartController(GameStartController gameStartController) {
        this.gameStartController = gameStartController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public RegisterController getRegisterController() {
        return registerController;
    }

    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }



}
