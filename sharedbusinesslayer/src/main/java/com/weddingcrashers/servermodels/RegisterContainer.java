package com.weddingcrashers.servermodels;

import com.weddingcrashers.model.User;

import java.io.Serializable;

public class RegisterContainer extends Container implements Serializable {
    User user;

    public RegisterContainer(){
        super(Methods.Register);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
