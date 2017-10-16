package com.weddingcrashers.managers;

import com.weddingcrashers.server.Client;
import com.weddingcrashers.util.businesslayer.ServerUtils;
import com.weddingcrashers.servermodels.ChatContainer;
import com.weddingcrashers.servermodels.ViewStatus;

public class ChatManager extends Manager {

    public ChatManager(Client c){
        super(c);
    }
    /**
     * @author Michel Schlatter
     * @param msg
     */
    public  void broadCastChatMessageToAllClients(String msg){
        // TODO: 02.10.2017 Manuel => broadcast you can use...
        ChatContainer cc = new ChatContainer();
        cc.setMsg(msg);
        cc.setClientId(this.client.getClientId());

        for(Client c : client.getOtherClients()){
            if(c.getViewStatus() == ViewStatus.Game || c.getViewStatus() == ViewStatus.Lobby) {
                ServerUtils.sendObject(c, cc);
            }
        }

    }
}
