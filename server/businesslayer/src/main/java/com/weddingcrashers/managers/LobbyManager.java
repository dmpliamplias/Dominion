package com.weddingcrashers.managers;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.PlayerSet;
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

    /**
     * Broadcasts each player in the lobby to all other players
     * So they can see each other in a list or smth.
     * This method gets called when a new User updates his ViewState to LoginView
     * @param c
     */
    public static void broadCastPlayersToAllClients(Client c){
        LobbyContainer lc = getUsers(c, false);

        for(Client client : c.getAllClients()){
            if(client.getViewStatus() == ViewStatus.Lobby) {
                ServerUtils.sendObject(client, lc);
                System.out.println("Sended  lobby and my status is:" + client.getViewStatus());
            }
        }
    }

    /**
     * this method gets called, when the hoster requests to start the game
     * the gamemanager gets the infos it has to know and each client gets an info to change to the gameview (changes automatically)
     * the hoster could also choose the players he wants to play with. it is dynamic.
     * @param lcReceived
     */
    public void startGame(LobbyContainer lcReceived){
        LobbyContainer lc = new LobbyContainer(Methods.StartGame);
        ArrayList<Integer> clientIds = lcReceived.getClientIds_startGame();
        if(clientIds.size() <= 1){
            ServerUtils.sendError(this.client, new Exception("Not possible to play with one or less players"));
            return;
        }

        GameSettings gs = lcReceived.getGameSettings();

        HashMap<Integer, User> users = new HashMap<Integer, User>();
        for(Integer clientId : clientIds) {
            for (Client c : client.getAllClients()) {
                if (clientId.equals(c.getClientId()) && c.getViewStatus() == ViewStatus.Lobby || c.getViewStatus() == ViewStatus.Ranking) {
                   users.put(c.getClientId(), c.getUser());
                }
            }
        }

        Collections.sort(clientIds);
        ArrayList<Client> players = new ArrayList<>();
        for(Integer clientId : clientIds){
            for(Client c : client.getAllClients()){
                if(clientId.equals(c.getClientId())&& c.getViewStatus() == ViewStatus.Lobby) {
                    if(clientIds.indexOf(clientId) == 0){
                        c.setActive(true);
                    }else{
                        c.setActive(false);
                    }
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
        GameManager.createDominionSet(players.size());

        ArrayList<Integer> ids = new ArrayList<Integer>();
        for(Client c : players){
            ids.add(c.getClientId());
        }
        Collections.sort(ids);
        int min = ids.get(0);

        for(Client player : players){
            ArrayList<Card> pullStack = GameManager.createInitalCardSet();
            PlayerSet set = new PlayerSet((int)player.getUser().getId());
            set.setPullStack(pullStack);
            player.setDominionSet(set);
            player.setActive(player.getClientId() == min);
        }
    }

    /**
     * pushes all clients with the viewstatus lobby or ranking to a hashmap (and sends this to the requested client)
     * @param c
     * @param sendToClient
     * @return Lobbycontainer
     */
    public static LobbyContainer getUsers(Client c, boolean sendToClient){
        HashMap<Integer, User> users = new HashMap<Integer, User>();

        for(Client client : c.getAllClients()){
            if(client.getUser() != null
                    && client.getViewStatus() == ViewStatus.Lobby
                    || client.getViewStatus() == ViewStatus.Ranking) {
                users.put(client.getClientId(), client.getUser());
            }
        }

        LobbyContainer lc = new LobbyContainer(Methods.Lobby_Players);
        lc.setUserNames(users);

        if(sendToClient) {
            ServerUtils.sendObject(c, lc);
        }
        return lc;
    }
}
