package util;

import Game.GameController;
import com.weddingcrashers.servermodels.*;
import connection.ConnectionController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lobby.LobbyController;
import login.LoginController;
import ranking.RankingController;
import register.RegisterController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;

import static java.lang.System.out;

/**
 *  @author Michel Schlatter
 *  */
public class ServerConnectionService extends Thread{
    private  Socket connection;
    private int clientId;
    private  boolean isHoster;
    private ObjectInputStream objectInputStream;
    private  ObjectOutputStream objectOutputStream;

    public ServerConnectionService(String url, int port) throws IOException{
        connection = new Socket(url,port);
        try {
            objectInputStream = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            showExceptionToUser(e);
        }
        objectOutputStream = new ObjectOutputStream(connection.getOutputStream());

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
            displayError(new ErrorContainer(e.getMessage()));
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
                showExceptionToUser(ex);
                ex.printStackTrace();
            }
        }
    }


    public  <T extends Container> void  sendObject(T object) throws  IOException{
        objectOutputStream.reset(); // TODO: 08.12.2017 nach write? 
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        out.println("Client sent message: " + object.getMethod() + "  to Client: " + clientId);
    }

    private <T extends  Container> T receiveObject() throws  IOException, ClassNotFoundException{
        T obj = (T)objectInputStream.readObject();
        return obj;
    }

    public void updateViewStatus(ViewStatus status) throws IOException{
        ViewStatusUpdateContainer vc = new ViewStatusUpdateContainer();
        vc.setViewStatus(status);
        sendObject(vc);
    }

    private void showExceptionToUser(Exception ex){
        Platform.runLater(()->{
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("There was a problem with the server-connection. Please restart the programm and connect to the server again.");
            alert.showAndWait();
        });
    }
    //-------------------------- Controller Communications -----------------------------------------------

    private void runMethod(Container c){
        String additive = clientId > 0 ?" to Client: " + clientId : "";
        out.println("Client received message. Method: \"" + c.getMethod() + "\"" + additive);

        if(c.getMethod() == Methods.Connect){
            ConnectionContainer connectionContainer = (ConnectionContainer)c;
            this.clientId = connectionContainer.getId();
            System.out.println("I'm client: " + this.clientId);
        }else if(c.getMethod()==Methods.Register && registerController != null) {
            registerController.handleServerAnswer((RegisterContainer)c);
        }else if(c.getMethod() == Methods.Login && loginController != null){
            loginController.handleServerAnswer((LoginContainer)c);
        }else if(c.getMethod() == Methods.Login_SetUser_TestPurposesOnly && loginController != null){
            loginController.handleServerAnswer_TestPurposeLogin((LoginContainer)c);
        }else if(c.getMethod() == Methods.Chat && gameController != null){
            gameController.handleServerAnswer_receiveMessage((ChatContainer) c);
        } else if(c.getMethod() == Methods.StartGame && lobbyController != null){
            lobbyController.handleServerAnswer_startGame((LobbyContainer)c);
        }else if(c.getMethod() == Methods.Rankings) {
            RankingContainer rc = (RankingContainer) c;
            rankingController.handleServerAnswer( rc.getHighScores() );
        }else if(c.getMethod() == Methods.Chat && lobbyController != null){
            lobbyController.handleServerAnswer_receiveMessage( (ChatContainer)c );
        }else if(c.getMethod() == Methods.Lobby_Players && lobbyController != null){
            lobbyController.handleServerAnswer_newPlayer((LobbyContainer) c);
        }
        else if(c.getMethod() == Methods.SpreadCards && gameController != null){
            GameContainer gc = (GameContainer)c;
           gameController.handleServerAnswer_receiveInitalPlayerSet(gc.getPlayerSets(), gc.getUnusedCards(), gc.getUserIdHasTurn());
        }
        else if(c.getMethod() == Methods.CardPlayed && gameController != null){
            GameContainer gc = (GameContainer)c;
            gameController.handleServerAnswer_logCardPlayed(gc.getCardPlayedInfo());
        }
        else if(c.getMethod() == Methods.UpdateRound && gameController != null){
            GameContainer gc = (GameContainer)c;
            gameController.handleServerAnswer_updateRound(gc.getRound());
        }else if(c.getMethod() == Methods.BuyCard && gameController != null){
            gameController.handleServerAnswer_cardBuyed((GameContainer)c);
        }else if(c.getMethod() == Methods.TurnFinished && gameController != null){
            gameController.handleServerAnswer_gameTurnFinished((GameContainer)c);
        }
        else if(c.getMethod() == Methods.Client_Server_Error){
            ErrorContainer ec = (ErrorContainer)c;
            displayError(ec);
        }
    }

    private void displayError(ErrorContainer ec){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.ERROR, ec.getError());

            if(ec.getErrorCode()==403) {
                alert.setOnCloseRequest((event)->{
                    Platform.exit();
                });
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
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

