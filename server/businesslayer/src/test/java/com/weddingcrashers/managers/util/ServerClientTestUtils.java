package com.weddingcrashers.managers.util;

import com.weddingcrashers.managers.ChatManager;
import com.weddingcrashers.managers.LobbyManager;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.Server;

import java.net.Socket;

/**
 * Created by Manuel on 04.10.2017.
 */
public class ServerClientTestUtils {


    private Server server;
    private int port;

    public ServerClientTestUtils() {
        this.port = 8080;
        Server.startServer( port, 6 );
        try {
            Client c = new Client(new Socket( "localhost", port ), 0);
            Client c1 = new Client(new Socket( "localhost", port ), 1);



            System.out.println(c.getClientId());
            System.out.println(c1.getClientId());


        }catch (Exception e){

        }


    }
    public void waitForServer() {
        try {
            Socket s = new Socket( "localhost", this.port );
            while (!s.isConnected()){
                wait( 10000 );
            }
        } catch (Exception e) {

        }
    }

}
