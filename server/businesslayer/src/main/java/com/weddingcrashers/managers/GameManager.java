package com.weddingcrashers.managers;

import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.ServerUtils;
import com.weddingcrashers.servermodels.GameContainer;
import com.weddingcrashers.servermodels.Methods;
import com.weddingcrashers.servermodels.ViewStatus;

public class GameManager extends Manager {

    public GameManager(Client client){
        super(client);
    }

    
    public void broadCastChatMessageToAllClients(String msg){
        // TODO: 02.10.2017 Manuel => broadcast you can use... 
        GameContainer gc = new GameContainer(Methods.Chat);
        gc.setChatMsg(msg);

        for(Client c : client.getOtherClients()){
            if(c.getViewStatus() == ViewStatus.Game) {
                ServerUtils.sendObject(c, gc);
            }
        }
    }
}
