package com.weddingcrashers.managers;

import com.weddingcrashers.model.User;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.ServerUtils;
import com.weddingcrashers.servermodels.LobbyContainer;
import com.weddingcrashers.servermodels.Methods;
import com.weddingcrashers.servermodels.ViewStatus;

import java.util.ArrayList;
import java.util.Collections;
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

        for(Client client : c.getAllClients()){
            if(client.getViewStatus() == ViewStatus.Lobby) {
                ServerUtils.sendObject(client, lc);
                System.out.println("Sended  lobby and my status is:" + client.getViewStatus());
            }
        }
    }

    public void startGame(ArrayList<Integer> clientIds){
        LobbyContainer lc = new LobbyContainer(Methods.StartGame);
        int lowestClientId = Collections.min(clientIds);

        for(Integer clientId : clientIds){
            for(Client c : client.getAllClients()){
                if(clientId.equals(c.getClientId())&& c.getViewStatus() == ViewStatus.Lobby) {
                    c.setActive(c.getClientId() == lowestClientId); // first registered can start first.
                    lc.setYourTurn(c.isActive());
                    ServerUtils.sendObject(c, lc);
                }
            }
        }
    }
}
