package com.weddingcrashers.server;

import com.weddingcrashers.managers.LobbyManager;
import com.weddingcrashers.servermodels.ConnectionContainer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *  @author Michel Schlatter
 *  */

public class Server extends Thread {
    private static Server server;

    int _port;
    int _maxPlayers;
    private static int idCounter = 0;


    ArrayList<Client> clients = new ArrayList<Client>();

    public Server(int port, int maxPlayers){
        _port = port;
        _maxPlayers = maxPlayers;
    }
    public static void startServer(int port, int maxPlayers){
        if(server == null) {
            server = new Server(1, maxPlayers);
            server.start();
        }
    }

    public static Server getInstance(){
        return server;
    }


    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(_port);
            while(clients.size() < _maxPlayers){
                Socket socket = serverSocket.accept();

                Client clientThread = new Client(socket, ++idCounter);
                clientThread.start();
                clients.add(clientThread);
                setOtherClientsForAllClients();

                ConnectionContainer c = new ConnectionContainer();
                c.setId(idCounter);
                ServerUtils.sendObject(clientThread ,c);

                LobbyManager.broadCastPlayersToAllClients(clientThread);
            }
        } catch (Exception ex){
            for(Client client : clients){
                ServerUtils.sendError(client,ex);
            }

        }

    }

    private void setOtherClientsForAllClients(){
        for(Client c : this.clients){
            ArrayList<Client> otherClients = new ArrayList<Client>();
            for(Client c2 : this.clients){
                if(c2.getClientId() != c.getClientId()){
                    otherClients.add(c2);
                }
            }
            c.setOtherClients(otherClients);
        }
    }
}
