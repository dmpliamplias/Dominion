package com.weddingcrashers.servermodels;

import com.weddingcrashers.model.User;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public class LoginContainer extends Container  implements Serializable {
    private String Email;
    private String Password;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginContainer(Methods method){
        super(method);
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
