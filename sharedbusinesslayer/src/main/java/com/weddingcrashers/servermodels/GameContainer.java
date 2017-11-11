package com.weddingcrashers.servermodels;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.PlayerSet;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameContainer extends Container implements Serializable {
    PlayerSet dominionSet;
    boolean yourTurn;
    ArrayList<Card> unusedCards; // Karten die noch kaufbar sind => wenn nichts geändert hat => null!

    public PlayerSet getDominionSet() {
        return dominionSet;
    }

    public void setDominionSet(PlayerSet dominionSet) {
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

    public ArrayList<Card> getUnusedCards() {
        return unusedCards;
    }

    public void setUnusedCards(ArrayList<Card> unusedCards) {
        this.unusedCards = unusedCards;
    }


}
