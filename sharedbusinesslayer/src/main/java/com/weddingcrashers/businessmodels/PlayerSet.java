package com.weddingcrashers.businessmodels;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author  Michel Schlatter
 */
public class PlayerSet implements Serializable {
    ArrayList<Card> trayStack; // Ablagestappel
    ArrayList<Card> pullStack; // Ziehstappel
    ArrayList<Card> handStack; // Ziehstappel

    int userId;

    public PlayerSet(int userId){
        this.userId = userId;
        this.trayStack = new ArrayList<Card>();
        this.pullStack =  new ArrayList<Card>();
        this.handStack = new ArrayList<Card>();
    }

    // ziehen bis keine Karten mehr auf Ziehstapel, dann ablagestappel zurück und mischeln
    public void pullHandStack(){
        int toCount = handStack.size() - 5;

        for(int i = toCount; i < 0; i++){
            if(pullStack.size() > 0){
                Card c = pullStack.get(0);
                handStack.add(c);
                pullStack.remove(c);
            }else{
              fillPullStack();
              i--;
            }
        }
    }

    private void fillPullStack(){
        for(Card c : trayStack){
            pullStack.add(c);
            trayStack.remove(c);
        }
        Collections.shuffle(pullStack);
    }

    public ArrayList<Card> getAllCardsFromSet(){
        ArrayList<Card> cards = new ArrayList<Card>();
        for(Card c : pullStack){
            cards.add(c);
        }
        for(Card c : handStack){
            cards.add(c);
        }
        for(Card c : trayStack){
            cards.add(c);
        }
        return cards;
    }

    public int calculatePoints(){
        ArrayList<Card> cards = getAllCardsFromSet();
        ArrayList<PointCard> pointCards = filterPointCards(cards);
        int points = 0;
        for(PointCard pc : pointCards){
            points += pc.getValue();
        }
        return points;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Card> getHandStack() {
        return handStack;
    }

    public void setHandStack(ArrayList<Card> handStack) {
        this.handStack = handStack;
    }



    /**
     * @author  Vanessa Cajochen
     */

    public void addCardToHandStack (Card card){
        handStack.add(card);
    }

    public void removeCardFromHandStack (Card card){
        handStack.remove(card);
    }

    public void addCardToPullStack (Card card){
        pullStack.add(card);
    }

    public void removeCardFromPullStack (Card card){
        pullStack.remove(card);
    }

    public void addCardToTrayStack (Card card){
        trayStack.add(card);
    }

    public void removeCardFromTrayStack (Card card){
        trayStack.remove(card);
    }


}
