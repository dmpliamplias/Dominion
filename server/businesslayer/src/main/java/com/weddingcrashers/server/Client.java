package com.weddingcrashers.server;

import com.weddingcrashers.businessmodels.PlayerSet;
import com.weddingcrashers.managers.*;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.*;
import com.weddingcrashers.util.businesslayer.ServerUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static java.lang.System.out;

/**
 *  @author Michel Schlatter
 *  */
public class Client extends Thread {
    private Socket _clientSocket;
    private int _clientId;
    private boolean isActive; // his turn;
    private User user;
    private PlayerSet dominionSet;
    private ObjectInputStream objectInputStream;
    private ViewStatus viewStatus = ViewStatus.Login; // after Connection he's redirected to Login
    private ArrayList<Client> otherClients;
    private ObjectOutputStream objectOutputStream;

    private LoginManager _loginManager;
    private LobbyManager _lobbyManager;
    private GameManager _gameManager;
    private RankingManager _rankingsManager;
    private ChatManager _chatManager;

    public Client(Socket clientSocket, int id) {
        _clientSocket = clientSocket;
        try {
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        _clientId = id;

        _loginManager = new LoginManager(this);
        _lobbyManager = new LobbyManager(this);
        _gameManager = new GameManager(this);
        _rankingsManager = new RankingManager(this);
        _chatManager = new ChatManager(this);
    }

    @Override
    public void run() {
        try {
            objectInputStream = new ObjectInputStream(_clientSocket.getInputStream());
            while(_clientSocket != null && ! _clientSocket.isClosed()) {
                Container container = (Container) objectInputStream.readObject();
                runMethod(container);
            }
        } catch (Exception ex) {
           ServerUtils.sendError(this,ex);
        }
    }

    public Socket get_clientSocket() {
        return _clientSocket;
    }

    public int getClientId() {
        return _clientId;
    }

    private void runMethod(Container c) {
        if (c != null) {
            out.println("Server got message =>  Method: " + c.getMethod() + " ClientId: " + _clientId);

            if (c.getMethod() == Methods.Login) {
                LoginContainer lc = (LoginContainer) c;
                _loginManager.login(lc.getEmail(), lc.getPassword());
            } else if (c.getMethod() == Methods.SetViewStatus) {
                ViewStatusUpdateContainer vc = (ViewStatusUpdateContainer) c;
                this.viewStatus = vc.getViewStatus();

                if (this.viewStatus == ViewStatus.Lobby) {
                    LobbyManager.broadCastPlayersToAllClients(this);
                }

            } else if (c.getMethod() == Methods.Register) {
                RegisterContainer rc = (RegisterContainer) c;
                _loginManager.createUser(rc.getUser());
            } else if (c.getMethod() == Methods.ChatGame) {
                ChatContainer cc = (ChatContainer) c;
                _chatManager.broadCastChatMessageToAllClients(cc.getMsg(),  Methods.ChatGame);
            } else if (c.getMethod() == Methods.ChatLobby) {
                ChatContainer cc = (ChatContainer) c;
                _chatManager.broadCastChatMessageToAllClients(cc.getMsg(),  Methods.ChatLobby);
            }else if (c.getMethod() == Methods.StartGame) {
                LobbyContainer lc = (LobbyContainer) c;
                _lobbyManager.startGame(lc);
            } else if (c.getMethod() == Methods.Lobby_Players) {
                LobbyContainer lc = (LobbyContainer) c;
                LobbyManager.getUsers(this, true);
            } else if (c.getMethod() == Methods.Login_SetUser_TestPurposesOnly) {
                LoginContainer lc = (LoginContainer) c;
                this.setUser(lc.getUser());
                ServerUtils.sendObject(this, lc);
            } else if (c.getMethod() == Methods.CardPlayed) {
                GameContainer gc = (GameContainer) c;
                _gameManager.cardPlayed(gc.getCardPlayedInfo());
            } else if (c.getMethod() == Methods.BuyCard) {
                _gameManager.buyCard((GameContainer) c);
            } else if (c.getMethod() == Methods.TurnFinished) {
                _gameManager.moveFinished((GameContainer) c);
            } else if (c.getMethod() == Methods.Rankings) {
                _rankingsManager.sendRanking();
            } else if (c.getMethod() == Methods.InitialCardSets) {
                _gameManager.sendInitalCardSets();
            }else if(c.getMethod() == Methods.ImOut){
                for(Client client : Server.clients){
                    if(client.getClientId() == this.getClientId()){
                        Server.clients.remove(client);
                    }
                }
                Server.setOtherClientsForAllClients();
            }
        }
    }

    public ArrayList<Client> getOtherClients() {
        return otherClients;
    }

    public ArrayList<Client> getAllClients() {
       ArrayList<Client> clients = new ArrayList<Client>();
       clients.addAll(getOtherClients());
       clients.add(this);
       return clients;
    }


    public void dispose() throws IOException {
        if(_clientSocket != null && !_clientSocket.isClosed()) {
            _clientSocket.close();
        }
    }

    public void setOtherClients(ArrayList<Client> otherClients) {
        this.otherClients = otherClients;
    }

    public ViewStatus getViewStatus() {
        return viewStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public PlayerSet getDominionSet() {
        return dominionSet;
    }

    public void setDominionSet(PlayerSet dominionSet) {
        this.dominionSet = dominionSet;
    }

    public ObjectOutputStream getObjectOutputStream(){
        return this.objectOutputStream;
    }
}