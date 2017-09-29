package com.weddingcrashers.managers;

import com.weddingcrashers.model.User;
import com.weddingcrashers.repositories.Utils;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.ServerUtils;
import com.weddingcrashers.servermodels.LoginContainer;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.UserService;

/**
 *  @author Michel Schlatter
 *  */
public class LoginManager {
    Client _client;
    UserService _userService;

    public LoginManager(Client c){
        _client = c;
        _userService = ServiceLocator.getUserService();
    }

    public void login(String email, String password){
       User user = _userService.getUserByEmail(email);
        LoginContainer loginContainer = new LoginContainer();

       if(user != null){
          if(user.getPassword().equals(Utils.hashPassword(password))) {
              loginContainer.setUser(user);
          }
       }
        ServerUtils.sendObject(_client, loginContainer);
    }
}
