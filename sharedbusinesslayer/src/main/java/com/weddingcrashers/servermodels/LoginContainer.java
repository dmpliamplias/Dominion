package com.weddingcrashers.servermodels;

public class LoginContainer extends Container {
    private String Email;
    private String Password;
    private boolean success;

    public LoginContainer(){
        setMethod(Methods.Login);
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
