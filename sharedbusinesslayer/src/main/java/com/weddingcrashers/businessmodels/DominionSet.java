package com.weddingcrashers.businessmodels;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author  Michel Schlatter
 */
public class DominionSet implements Serializable {
    ArrayList<Card> trayStack; // Ablagestappel
    ArrayList<Card> pullStack; // Ziehstappel
    int clientId;

    public ArrayList<Card> getTrayStack() {
        return trayStack;
    }

    public void setTrayStack(ArrayList<Card> trayStack) {
        this.trayStack = trayStack;
    }

    public ArrayList<Card> getPullStack() {
        return pullStack;
    }

    public void setPullStack(ArrayList<Card> pullStack) {
        this.pullStack = pullStack;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }


    public static ArrayList<MoneyCard> filterMoneyCards(ArrayList<Card> cards){
        ArrayList<MoneyCard> list = new ArrayList<MoneyCard>();
        for(Card card : cards){
            if(card instanceof  MoneyCard){
                list.add((MoneyCard)card);
            }
        }
        return list;
    }

    public static ArrayList<KingCard> filterKingKards(ArrayList<Card> cards){
        ArrayList<KingCard> list = new ArrayList<KingCard>();
        for(Card card : cards){
            if(card instanceof  KingCard){
                list.add((KingCard)card);
            }
        }
        return list;
    }

    public static ArrayList<PointCard> filterPointCards(ArrayList<Card> cards){
        ArrayList<PointCard> list = new ArrayList<PointCard>();
        for(Card card : cards){
            if(card instanceof  PointCard){
                list.add((PointCard)card);
            }
        }
        return list;
    }

}
