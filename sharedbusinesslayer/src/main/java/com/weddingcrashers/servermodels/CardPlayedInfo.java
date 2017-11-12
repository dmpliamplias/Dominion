package com.weddingcrashers.servermodels;

import com.weddingcrashers.businessmodels.Card;

public class CardPlayedInfo {
    int userId;
    Card card;

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


}
