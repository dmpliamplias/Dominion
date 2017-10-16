package com.weddingcrashers.managers;

import com.weddingcrashers.model.User;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.util.businesslayer.ServerUtils;
import com.weddingcrashers.servermodels.LoginContainer;
import com.weddingcrashers.servermodels.RegisterContainer;
import com.weddingcrashers.service.UserService;

import static com.weddingcrashers.util.businesslayer.SecurityUtils.validatePassword;

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
          if (validatePassword(user, password)) {
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
