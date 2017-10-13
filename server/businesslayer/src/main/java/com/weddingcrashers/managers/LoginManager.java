package com.weddingcrashers.managers;

import com.weddingcrashers.model.User;
import com.weddingcrashers.repositories.Utils;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.ServerUtils;
import com.weddingcrashers.servermodels.LoginContainer;
import com.weddingcrashers.servermodels.RegisterContainer;
import com.weddingcrashers.service.UserService;

/**
 *  @author Michel Schlatter
 *  */
public class LoginManager extends Manager {
    UserService _userService;

    public LoginManager(Client c)
    {
        super(c);
        _userService = serviceLocator.getUserService();
    }

    public void login(String email, String password){
       User user = _userService.getUserByEmail(email);
       LoginContainer loginContainer = new LoginContainer();

       if(user != null){
           // TODO: 13.10.2017  Dyoni:  if(user.getPassword().equals(Utils.hashPassword(password))) {
          if(user.getPassword().equals(password)) {
              loginContainer.setUser(user);
          }
       }
        ServerUtils.sendObject(client, loginContainer);
    }

    public void createUser(User u){
        User user = _userService.create(u);

        RegisterContainer rc = new RegisterContainer();
        rc.setUser(user);
        ServerUtils.sendObject(client, rc);
    }
}
