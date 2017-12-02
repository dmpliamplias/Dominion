package com.weddingcrashers.servermodels;

import java.io.Serializable;

public class GameSettings implements Serializable {

    Integer finishAfterRounds;
    boolean pointCards;

    public Integer getFinishAfterRounds() {
        if(finishAfterRounds == null) return -1;
        return finishAfterRounds;
    }

    public void setFinishAfterRounds(Integer finishAfterRounds) {
        this.finishAfterRounds = finishAfterRounds;
    }

    public boolean isPointCards() {
        return pointCards;
    }

    public void setPointCards(boolean pointCards) {
        this.pointCards = pointCards;
    }

}
