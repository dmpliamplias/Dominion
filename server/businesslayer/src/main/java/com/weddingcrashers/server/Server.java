package com.weddingcrashers.server;

import com.weddingcrashers.servermodels.ConnectionContainer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *  @author Michel Schlatter
 *  */

public class Server extends Thread {
    int _port;
    int _maxPlayers;
    private static int idCounter = 0;

    ArrayList<Client> clients = new ArrayList<Client>();

    public Server(int port, int maxPlayers){
        _port = port;
        _maxPlayers = maxPlayers;
    }
    public static void startServer(int port, int maxPlayers){
        Server s = new Server(1, maxPlayers);
        s.start();
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

                ConnectionContainer c = new ConnectionContainer();
                c.setId(idCounter);
                ServerUtils.sendObject(clientThread ,c);
            }
        } catch (Exception ex){
            for(Client client : clients){
                ServerUtils.sendError(client,ex);
            }

        }

    }
}
