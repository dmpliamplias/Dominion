package com.weddingcrashers.servermodels;

import com.weddingcrashers.model.User;

/**
 *  @author Michel Schlatter
 *  */
public class LoginContainer extends Container {
    private String Email;
    private String Password;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginContainer(){
        super(Methods.Login);
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
