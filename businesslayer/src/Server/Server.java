package Server;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ExportException;
import java.util.ArrayList;

public class Server extends Thread {
    int _port;
    int _maxPlayers;

    ArrayList<Socket> clients = new ArrayList<Socket>();

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
                clients.add(socket);

                for(Socket client : clients){
                    PrintWriter writer =  new PrintWriter(client.getOutputStream());
                    writer.write("There are :" + clients.size() + " Players registered.");
                    writer.flush();
                }
            }
        } catch (Exception ex){

        }

    }
}
