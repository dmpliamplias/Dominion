package com.weddingcrashers.servermodels;

import com.weddingcrashers.businessmodels.DominionSet;

public class GameContainer extends Container{
    public String getChatMsg() {
        return ChatMsg;
    }

    public void setChatMsg(String chatMsg) {
        ChatMsg = chatMsg;
    }

    public DominionSet getDominionSet() {
        return dominionSet;
    }

    public void setDominionSet(DominionSet dominionSet) {
        this.dominionSet = dominionSet;
    }

    String ChatMsg;
    DominionSet dominionSet;

    public GameContainer(Methods method){
        super(method);
    }

}
