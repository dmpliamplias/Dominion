package com.weddingcrashers.servermodels;

import com.weddingcrashers.businessmodels.Card;

import java.io.Serializable;

public class CardPlayedInfo implements Serializable {
    int userId;
    Card card;
    int clientId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getClientId() {
        return clientId;
    }


}
