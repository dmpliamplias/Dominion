package com.weddingcrashers.servermodels;

import java.io.Serializable;

public class ChatContainer extends Container implements Serializable{
    String msg;

    public ChatContainer(){
        super(Methods.Chat);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
