package com.weddingcrashers.servermodels;

import com.weddingcrashers.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class LobbyContainer extends Container {
    HashMap<Integer, User> users;

    public LobbyContainer(Methods method){
        super(method);
    }

    public HashMap<Integer, User> getUserNames() {
        return users;
    }

    public void setUserNames(HashMap<Integer, User> users) {
        this.users = users;
    }


}
