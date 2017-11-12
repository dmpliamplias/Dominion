package com.weddingcrashers.servermodels;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.PlayerSet;

import java.io.Serializable;
import java.util.ArrayList;

public class GameContainer extends Container implements Serializable {
    PlayerSet dominionSet;
    int  userIdHasTurn;
    ArrayList<Card> unusedCards; // Karten die noch kaufbar sind => wenn nichts geändert hat => null!
    CardPlayedInfo cardPlayedInfo;
    int round;

    public PlayerSet getDominionSet() {
        return dominionSet;
    }

    public void setDominionSet(PlayerSet dominionSet) {
        this.dominionSet = dominionSet;
    }

    public GameContainer(Methods method){
        super(method);
    }

    public ArrayList<Card> getUnusedCards() {
        return unusedCards;
    }

    public void setUnusedCards(ArrayList<Card> unusedCards) {
        this.unusedCards = unusedCards;
    }

    public CardPlayedInfo getCardPlayedInfo() {
        return cardPlayedInfo;
    }

    public void setCardPlayedInfo(CardPlayedInfo cardPlayedInfo) {
        this.cardPlayedInfo = cardPlayedInfo;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }


    public int getUserIdHasTurn() {
        return userIdHasTurn;
    }

    public void setUserIdHasTurn(int userIdHasTurn) {
        this.userIdHasTurn = userIdHasTurn;
    }

    public boolean isYourTurn(int userId){
        return userIdHasTurn == userId;
    }
}
