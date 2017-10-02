package com.weddingcrashers.businessmodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public class KingCard extends Card implements Serializable {

   int buys;
   int actions;
   int cards;

    KingCardType kingCardType;
    public int getBuys() {
        return buys;
    }

    public void setBuys(int buys) {
        this.buys = buys;
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public int getCards() {
        return cards;
    }

    public void setCards(int cards) {
        this.cards = cards;
    }

    public KingCardType getKingCardType() {
        return kingCardType;
    }

    public void setKingCardType(KingCardType kingCardType) {
        this.kingCardType = kingCardType;
    }






}
