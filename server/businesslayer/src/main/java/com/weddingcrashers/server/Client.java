package com.weddingcrashers.server;

import com.weddingcrashers.managers.LoginManager;
import com.weddingcrashers.servermodels.Container;
import com.weddingcrashers.servermodels.Methods;

import java.io.ObjectInputStream;
import java.net.Socket;
/**
 *  @author Michel Schlatter
 *  */
public class Client extends Thread {
    Socket _clientSocket;
    int _clientId;

    LoginManager _loginManager;

    public Client(Socket clientSocket, int id) {
        _clientSocket = clientSocket;
        _clientId = id;

        _loginManager = new LoginManager(this);
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

        }
    }
}