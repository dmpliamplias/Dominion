package com.weddingcrashers.managers;

import com.weddingcrashers.server.Client;
import com.weddingcrashers.util.businesslayer.ServerUtils;
import com.weddingcrashers.servermodels.ChatContainer;
import com.weddingcrashers.servermodels.ViewStatus;

/***
 * @Author Michel Schlatter
 */
public class ChatManager extends Manager {

    public ChatManager(Client c){
        super(c);
    }

    public  void broadCastChatMessageToAllClients(String msg){
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
