package com.weddingcrashers.servermodels;

import com.weddingcrashers.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
/***
 * @author Michel Schlatter
 */
public class LobbyContainer extends Container implements Serializable {
    HashMap<Integer, User> users; // clientId, User
    ArrayList<Integer> clientIds_startGame;
    GameSettings gameSettings;

    public GameSettings getGameSettings() {
        return gameSettings;
    }

    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    public LobbyContainer(Methods method){
        super(method);
    }
    
    public ArrayList<Integer> getClientIds_startGame() {
        return clientIds_startGame;
    }

    public void setClientIds_startGame(ArrayList<Integer> clientIds_startGame) {
        this.clientIds_startGame = clientIds_startGame;
    }

    public HashMap<Integer, User> getPlayers() {
        return users;
    }

    public void setUserNames(HashMap<Integer, User> users) {
        this.users = users;
    }


}
