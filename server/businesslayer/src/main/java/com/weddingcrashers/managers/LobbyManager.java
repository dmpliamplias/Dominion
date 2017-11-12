package com.weddingcrashers.managers;

import com.weddingcrashers.model.User;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.Server;
import com.weddingcrashers.servermodels.*;
import com.weddingcrashers.util.businesslayer.ServerUtils;

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
        LobbyContainer lc = getUsers(c);

        for(Client client : c.getAllClients()){
            if(client.getViewStatus() == ViewStatus.Lobby) {
                ServerUtils.sendObject(client, lc);
                System.out.println("Sended  lobby and my status is:" + client.getViewStatus());
            }
        }
    }

    public void startGame(LobbyContainer lcReceived){
        LobbyContainer lc = new LobbyContainer(Methods.StartGame);
        ArrayList<Integer> clientIds = lcReceived.getClientIds_startGame();
        GameSettings gs = lcReceived.getGameSettings();



        HashMap<Integer, User> users = new HashMap<Integer, User>();
        for(Integer clientId : clientIds) {
            for (Client c : client.getAllClients()) {
                if (clientId.equals(c.getClientId()) && c.getViewStatus() == ViewStatus.Lobby) {
                   users.put(c.getClientId(), c.getUser());
                }
            }
        }



        ArrayList<Client> players = new ArrayList<>();
        for(Integer clientId : clientIds){
            for(Client c : client.getAllClients()){
                if(clientId.equals(c.getClientId())&& c.getViewStatus() == ViewStatus.Lobby) {
                    lc.setUserNames(users);
                    lc.setGameSettings(lcReceived.getGameSettings());
                    players.add(c);
                    ServerUtils.sendObject(c, lc);
                }
            }
        }
        
        GameManager.setGameSettings(lc.getGameSettings());
        GameManager.setPlayers(players);
        GameManager.setUsers(users);
        GameManager.setGameRunning(true);
        GameManager.initialize(users.size());
    }

    public static LobbyContainer getUsers(Client c){
        HashMap<Integer, User> users = new HashMap<Integer, User>();

        for(Client client : c.getAllClients()){
            users.put(client.getClientId(), client.getUser());
        }

        LobbyContainer lc = new LobbyContainer(Methods.Lobby_Players);
        lc.setUserNames(users);

        ServerUtils.sendObject(c, lc);
        return lc;
    }
}
