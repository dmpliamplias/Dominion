package com.weddingcrashers.servermodels;

public class GameSettings {

    int finishAfterRounds;
    boolean pointCards;

    public int getFinishAfterRounds() {
        return finishAfterRounds;
    }

    public void setFinishAfterRounds(int finishAfterRounds) {
        this.finishAfterRounds = finishAfterRounds;
    }

    public boolean isPointCards() {
        return pointCards;
    }

    public void setPointCards(boolean pointCards) {
        this.pointCards = pointCards;
    }

}
