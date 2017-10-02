package com.weddingcrashers.managers;

import com.sun.xml.internal.bind.v2.TODO;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.ServerUtils;
import com.weddingcrashers.servermodels.GameContainer;
import com.weddingcrashers.servermodels.Methods;

public class GameManager extends Manager {

    public GameManager(Client client){
        super(client);
    }

    
    public void broadCastChatMessageToAllClients(String msg){
        // TODO: 02.10.2017 Manuel => broadcast you can use... 
        GameContainer gc = new GameContainer(Methods.Chat);
        gc.setChatMsg(msg);

        for(Client c : client.getOtherClients()){
            ServerUtils.sendObject(c, gc);
        }
    }
}
