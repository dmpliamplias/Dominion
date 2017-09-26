package com.weddingcrashers.repositories;

import com.weddingcrashers.model.User;

public class UserRepo {
    public int _dbSession;

    public UserRepo(){
        // ServiceLocator getDbSession
    }

    public User getUserByEmailAndPw(String email, String pw){
        // just as example; replace with real code:
        User user = new User();
        user.setUserEmail("michel.schlatter@gmx.net");
        return user;
    }

}
