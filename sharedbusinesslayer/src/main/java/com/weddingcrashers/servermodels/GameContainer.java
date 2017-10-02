package com.weddingcrashers.servermodels;

import com.weddingcrashers.businessmodels.DominionSet;

public class GameContainer extends Container{
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
