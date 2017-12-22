package com.weddingcrashers.servermodels;

import java.io.Serializable;

/***
 * @author Michel Schlatter
 */
public class ChatContainer extends Container implements Serializable{
    String msg;
    int clientId;

    public ChatContainer(Methods chatMethod){
        super(chatMethod);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
