package com.weddingcrashers.servermodels;

import com.weddingcrashers.businessmodels.DominionSet;

import java.io.Serializable;

public class GameContainer extends Container implements Serializable {
    String chatMsg;
    DominionSet dominionSet;


    public String getChatMsg() {
        return chatMsg;
    }

    public void setChatMsg(String chatMsg) {
        this.chatMsg = chatMsg;
    }

    public DominionSet getDominionSet() {
        return dominionSet;
    }

    public void setDominionSet(DominionSet dominionSet) {
        this.dominionSet = dominionSet;
    }

    public GameContainer(Methods method){
        super(method);
    }

}
