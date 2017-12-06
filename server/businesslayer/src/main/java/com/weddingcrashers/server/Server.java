package com.weddingcrashers.server;

import com.weddingcrashers.managers.LobbyManager;
import com.weddingcrashers.servermodels.ConnectionContainer;
import com.weddingcrashers.servermodels.ErrorContainer;
import com.weddingcrashers.util.businesslayer.ServerUtils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import static java.lang.System.out;

/**
 *  @author Michel Schlatter
 *  */

public class Server extends Thread {
    private static Server server;
    private static ServerSocket serverSocket;

    int _port;
    int _maxPlayers;
    private static int idCounter = 0;


    static ArrayList<Client> clients = new ArrayList<Client>();

    private Server(int port, int maxPlayers){
        _port = port;
        _maxPlayers = maxPlayers;
    }

    public static void startServer(int port, int maxPlayers){
        if(server == null) {
            server = new Server(port, maxPlayers);
            server.start();
        }
    }

    public static void dispose(){
        if(server != null && !serverSocket.isClosed()){
            try {
                for(Client c: clients){
                    c.dispose();
                    c = null;
                }
                serverSocket.close();
                server = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Server getInstance(){
        return server;
    }


    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(_port);
            out.println("Server started");
            while(!serverSocket.isClosed()){
                if(clients.size() < _maxPlayers) {
                    Socket socket = serverSocket.accept();

                    Client clientThread = new Client(socket, ++idCounter);
                    clientThread.start();
                    clients.add(clientThread);
                    setOtherClientsForAllClients();

                    out.println("Client accepted id:" + idCounter);

                    ConnectionContainer c = new ConnectionContainer();
                    c.setId(idCounter);
                    ServerUtils.sendObject(clientThread, c);
                }else{
                    Socket deniedClient = serverSocket.accept();
                    ErrorContainer errorContainer = new ErrorContainer("LOBBY IS FULL. YOU CAN NOT CONNECT TO SERVER, SORRY.");
                    errorContainer.setErrorCode(403);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(deniedClient.getOutputStream());
                    objectOutputStream.writeObject(errorContainer);
                    objectOutputStream.flush();
                    System.out.println("Just denied a client...");
                }
            }
        } catch (Exception ex){
            for(Client client : clients){
                if(!client.get_clientSocket().isClosed()) {
                    ServerUtils.sendError(client, ex);
                }
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
