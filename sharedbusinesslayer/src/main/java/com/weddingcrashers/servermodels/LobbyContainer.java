package com.weddingcrashers.servermodels;

import com.weddingcrashers.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class LobbyContainer extends Container implements Serializable {
    HashMap<Integer, User> users;
    ArrayList<Integer> clientIds_startGame;

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
