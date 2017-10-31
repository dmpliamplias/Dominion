package com.weddingcrashers.managers;

import com.weddingcrashers.model.User;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.Server;
import com.weddingcrashers.servermodels.GameSettings;
import com.weddingcrashers.util.businesslayer.ServerUtils;
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

        int lowestClientId = Collections.min(clientIds);

        ArrayList<Client> players = new ArrayList<>();
        for(Integer clientId : clientIds){
            for(Client c : client.getAllClients()){
                if(clientId.equals(c.getClientId())&& c.getViewStatus() == ViewStatus.Lobby) {
                    c.setActive(c.getClientId() == lowestClientId); // first registered can start first.
                    lc.setYourTurn(c.isActive());
                    players.add(c);
                    // TODO: 31.10.2017  Migi eventuell jedem client noch die anderen spieler schicken? 
                    ServerUtils.sendObject(c, lc);
                }
            }
        }
        
        GameManager.setGameSettings(lc.getGameSettings());
        GameManager.setPlayers(players);
        GameManager.setGameRunning(true);
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
