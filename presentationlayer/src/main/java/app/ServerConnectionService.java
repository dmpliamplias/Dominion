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

import static java.lang.System.out;

/**
 *  @author Michel Schlatter
 *  */
public class ServerConnectionService extends Thread{
    private  Socket connection;
    private int clientId;
    private  boolean isHoster;

    public ServerConnectionService(String url, int port) throws IOException{
        connection = new Socket(url,port);
        this.start();
    }

    public Socket getConnection() {
        return connection;
    }

    public int getClientId() {
        return clientId;
    }

    public void dispose(){
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (IOException e) {
            displayError(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(!connection.isClosed()){
            try {
                Container c = this.<Container>receiveObject();
                runMethod(c);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    public  <T extends Container> void  sendObject(T object) throws  IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(connection.getOutputStream());
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();

        out.println("Client sent message: " + object.getMethod() + "  to Client: " + clientId);
    }

    private <T extends  Container> T receiveObject() throws  IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(connection.getInputStream());
        return (T)objectInputStream.readObject();
    }

    public void updateViewStatus(ViewStatus status) throws IOException{
        ViewStatusUpdateContainer vc = new ViewStatusUpdateContainer();
        vc.setViewStatus(status);
        sendObject(vc);
    }

    //-------------------------- Controller Communications -----------------------------------------------

    private void runMethod(Container c){
        out.println("Client received message Method: " + c.getMethod() + "  to Client: " + clientId);

        if(c.getMethod() == Methods.Connect){
            ConnectionContainer connectionContainer = (ConnectionContainer)c;
            this.clientId = connectionContainer.getId();
            System.out.println("I'm client: " + this.clientId);
        }
        else if(c.getMethod() == Methods.Login && loginController != null){
            loginController.handleServerAnswer((LoginContainer)c);
        }else if(c.getMethod() == Methods.Chat && gameController != null){
            ChatContainer chatContainer = (ChatContainer)c;
            gameController.recievedMessage(chatContainer );
        } else if(c.getMethod() == Methods.StartGame && lobbyController != null){
            lobbyController.handleServerAnswer_startGame(((LobbyContainer)c).isYourTurn());
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

