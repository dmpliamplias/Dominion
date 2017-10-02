package com.weddingcrashers.server;

import com.weddingcrashers.managers.LobbyManager;
import com.weddingcrashers.managers.LoginManager;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.*;

import javax.swing.text.View;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *  @author Michel Schlatter
 *  */
public class Client extends Thread {
    Socket _clientSocket;
    int _clientId;
    User user;

    ViewStatus viewStatus = ViewStatus.Login; // after Connection he's redirected to Login
    ArrayList<Client> otherClients;

    LoginManager _loginManager;
    LobbyManager _lobbyManager;

    public Client(Socket clientSocket, int id) {
        _clientSocket = clientSocket;
        _clientId = id;

        _loginManager = new LoginManager(this);
        _lobbyManager = new LobbyManager(this);
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(_clientSocket.getInputStream());
            Container container = (Container)objectInputStream.readObject();

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

    private void runMethod(Container c){
        if(c.getMethod() == Methods.Login){
            LoginContainer lc = (LoginContainer)c;
            _loginManager.login(lc.getEmail(), lc.getPassword());
        }else if(c.getMethod() == Methods.SetViewStatus){
            ViewStatusUpdateContainer vc = (ViewStatusUpdateContainer)c;
            this.viewStatus = vc.getViewStatus();
        }else if(c.getMethod() == Methods.Register){

        }
        else if(c.getMethod() == Methods.GetRankings){
            //.... continue
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
}