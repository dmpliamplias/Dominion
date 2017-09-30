package com.weddingcrashers.servermodels;

import com.weddingcrashers.model.User;

public class RegisterContainer extends Container {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
