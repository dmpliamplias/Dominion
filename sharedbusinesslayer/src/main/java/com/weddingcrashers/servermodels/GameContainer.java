package com.weddingcrashers.servermodels;

import com.weddingcrashers.businessmodels.DominionSet;

import java.io.Serializable;

public class GameContainer extends Container implements Serializable {
    DominionSet dominionSet;
    boolean yourTurn;

    public DominionSet getDominionSet() {
        return dominionSet;
    }

    public void setDominionSet(DominionSet dominionSet) {
        this.dominionSet = dominionSet;
    }

    public GameContainer(Methods method){
        super(method);
    }
    public boolean isYourTurn() {
        return yourTurn;
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }


}
