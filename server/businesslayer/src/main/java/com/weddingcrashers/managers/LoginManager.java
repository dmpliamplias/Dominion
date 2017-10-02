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
          if(user.getPassword().equals(Utils.hashPassword(password))) {
              loginContainer.setUser(user);
          }
       }
        ServerUtils.sendObject(client, loginContainer);
    }

    public void createUser(User u){
        _userService.create(u);

        RegisterContainer rc = new RegisterContainer();
        rc.setUser(u); // TODO: 02.10.2017 Michel => ändere zu user der von DB
        ServerUtils.sendObject(client, rc);
    }
}
