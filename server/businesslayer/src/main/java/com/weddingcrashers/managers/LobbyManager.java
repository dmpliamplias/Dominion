package com.weddingcrashers.managers;

import com.weddingcrashers.model.User;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.ServerUtils;
import com.weddingcrashers.servermodels.LobbyContainer;
import com.weddingcrashers.servermodels.Methods;
import com.weddingcrashers.servermodels.ViewStatus;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author  Michel Schlatter
 */
public class LobbyManager extends Manager{

    public LobbyManager(Client c){
        super(c);
    }

    public static void broadCastPlayersToAllClients(Client c){
        HashMap<Integer, User> users = new HashMap<Integer, User>();

       for(Client client : c.getAllClients()){
           users.put(client.getClientId(), client.getUser());
       }

        LobbyContainer lc = new LobbyContainer(Methods.Lobby_Players);
        lc.setUserNames(users);
        ServerUtils.sendObject(c,lc);

        for(Client client : c.getAllClients()){
            if(client.getViewStatus() == ViewStatus.Lobby) {
                ServerUtils.sendObject(client, lc);
            }
        }
    }
}
