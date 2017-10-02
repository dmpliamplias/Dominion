package app;

import Game.GameController;
import com.weddingcrashers.servermodels.*;
import connection.ConnectionController;
import lobby.LobbyController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import login.LoginController;
import ranking.RankingController;
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
    private  boolean isHoster;

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
        if(c.getMethod() == Methods.Login && loginController != null){
            loginController.handleServerAnswer((LoginContainer)c);
        }else if(c.getMethod() == Methods.Chat && gameController != null){
            // Manu run in your Controller, but with Platform.runLater(()->{}) because you're not on javafx thread anymore...
        } else if(c.getMethod() == Methods.StartGame && lobbyController != null){
            lobbyController.handleServerAnswer_startGame();
        }else if(c.getMethod() == Methods.Rankings){
            RankingContainer rc = (RankingContainer)c;
            rankingController.handleServerAnswer(rc.getHighScores());
        }
        else if(c.getMethod() == Methods.Client_Server_Error){
            ErrorContainer ec = (ErrorContainer)c;
            displayError(ec.getError());
        }
    }

    // TODO: 30.09.2017 Michel displays correctly?
    private void displayError(String msg){
        Platform.runLater(()->{
            Stage dialog = new Stage();
            BorderPane root = new BorderPane();
            Label lbl = new Label();
            lbl.setText(msg);
            Button btnClose = new Button();
            btnClose.setText("Close");
            
            root.getChildren().add(lbl);
            root.getChildren().add(btnClose);
            Scene scene = new Scene(root);


            dialog.setScene(scene);
           // dialog.initOwner(stage);
            dialog.initModality(Modality.APPLICATION_MODAL);

            dialog.showAndWait();

            btnClose.setOnAction((e) -> {
                dialog.close();
            });
            
            dialog.show();

        });

    }


    ConnectionController connectionController;
    LobbyController lobbyController;
    LoginController loginController;
    RegisterController registerController;
    GameController gameController;
    RankingController rankingController;

    public ConnectionController getConnectionController() {
        return connectionController;
    }



    public void setConnectionController(ConnectionController connectionController) {
        this.connectionController = connectionController;
    }

    public void setLobbyController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void setRegisterController(RegisterController registerController) {
        this.registerController = registerController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setRankingController(RankingController rankingController) {
        this.rankingController = rankingController;
    }

    public boolean isHoster() {
        return isHoster;
    }

    public void setIsHoster(boolean hoster) {
        isHoster = hoster;
    }


    }

