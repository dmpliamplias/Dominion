package com.weddingcrashers.managers;

import com.weddingcrashers.businessmodels.*;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.util.businesslayer.ServerUtils;
import com.weddingcrashers.servermodels.GameContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameManager extends Manager {
    private static final String PATH = "/translation/trans";
    private static List<Card> unusedCards; // this field is static 'cause it's for every gamemanager instance the same cards
    
    public GameManager(Client client){
        super(client);
    }
    
    public static  void initialize(){
        unusedCards = new ArrayList<Card>();
        // TODO: 03.10.2017 add card set here and delete cards from the list, when user pulls it. 
    }


    private void afterGameTurn(GameContainer container){
      int nxtId = getNextTurnClientId();

      container.getDominionSet().setClientId(client.getClientId());

      for(Client c : client.getAllClients()){ // send container to all clients
          container.setYourTurn(c.getClientId() == nxtId); // the user can see if the next turn is his turn
          ServerUtils.sendObject(c, container);
      }
    }

    private int getNextTurnClientId(){
        int currentIdActive = client.getClientId();
        List<Integer> ids = new ArrayList<Integer>();

        for(Client c : client.getAllClients()){
            ids.add(c.getClientId());
        }
        Collections.sort(ids);
        int min = ids.get(0);
        int max = ids.get(ids.size()-1);

        int currentIdx = ids.indexOf(currentIdActive);
        int nxtIdx = ++currentIdx;

        if(nxtIdx > (ids.size()-1)){
            nxtIdx = 0;
        }

        int nxtIdActive = ids.get(nxtIdx);
        return nxtIdActive;
    }

    /**
     * @author Murat Kelleci
     */
    public static void shuffle(List<Card> cardDeck){
        Collections.shuffle(cardDeck);
    }

    public static void createDominionSet(int players){

        // PointCards
        for(int i = 0; i < getNumberOfCards(players, CardType.Anwesen); i++) {
            PointCard pc1 = new PointCard();
            pc1.setPointCardType(PointCardType.Estate);
            pc1.setCost(2);
            pc1.setValue(1);
            pc1.setName("Anwesen");
            pc1.setFilePath("Punkte_01.jpg");
            unusedCards.add(pc1);
        }

        for(int i = 0; i < getNumberOfCards(players, CardType.Herzogtum); i++) {
            PointCard pc2 = new PointCard();
            pc2.setPointCardType(PointCardType.Duchy);
            pc2.setCost(5);
            pc2.setValue(3);
            pc2.setName("Herzogtum");
            pc2.setFilePath("Punkte_03.jpg");
            unusedCards.add(pc2);
        }

        for(int i = 0; i < getNumberOfCards(players, CardType.Provinz); i++) {
            PointCard pc3 = new PointCard();
            pc3.setPointCardType(PointCardType.Province);
            pc3.setCost(6);
            pc3.setValue(8);
            pc3.setName("Provinz");
            pc3.setFilePath("Punkte_06.jpg");
            unusedCards.add(pc3);
        }

        // MoneyCards
        for(int i = 0; i < getNumberOfCards(players, CardType.Kupfer); i++) {
            MoneyCard mc1 = new MoneyCard();
            mc1.setMoneyType(MoneyType.Copper);
            mc1.setValue(1);
            mc1.setCost(0);
            mc1.setName("Kupfer");
            mc1.setFilePath("Geld_01.jpg");
            unusedCards.add(mc1);
        }

        for(int i = 0; i < getNumberOfCards(players, CardType.Silber); i++) {
            MoneyCard mc2 = new MoneyCard();
            mc2.setMoneyType(MoneyType.Silver);
            mc2.setValue(2);
            mc2.setCost(3);
            mc2.setName("Silber");
            mc2.setFilePath("Geld_02.jpg");
            unusedCards.add(mc2);
        }

        for(int i = 0; i < getNumberOfCards(players, CardType.Gold); i++) {
            MoneyCard mc3 = new MoneyCard();
            mc3.setMoneyType(MoneyType.Gold);
            mc3.setValue(3);
            mc3.setCost(6);
            mc3.setName("Gold");
            mc3.setFilePath("Geld_03.jpg");
            unusedCards.add(mc3);
        }

        // TODO: 19.10.2017  Michel => fertig machen 
        // KingCards => 
        KingCard kc1 = new KingCard();
        kc1.setActions(2);
        kc1.setBuys(0);
        kc1.setCards(1);
        kc1.setCost(3);
        kc1.setKingCardType(KingCardType.Action);
        kc1.setFilePath("Dorf.jpg");

        KingCard kc2 = new KingCard();
        kc2.setActions(2);
        kc2.setBuys(0);
        kc2.setCards(1);
        kc2.setCost(3);
        kc2.setKingCardType(KingCardType.Action);

    }



    private static int getNumberOfCards(int players, CardType cardType){
        // PointCards
       if(cardType == CardType.Anwesen
               || cardType == CardType.Herzogtum
               || cardType == CardType.Provinz){
           if(players == 2) return 8;
           if(players == 3) return 12;
           if(players == 4) return 12;
       }

       //MoneyCards
       if(cardType == CardType.Gold){
            return 30;
       }

        if(cardType == CardType.Silber){
            return 40;
        }

        if(cardType == CardType.Kupfer){
            if(players == 2) return 146;
            if(players == 3) return 139;
            if(players == 4) return 132;
        }

       return 0;
    }

    private enum CardType{
        Anwesen,
        Herzogtum,
        Provinz,
        Gold,
        Silber,
        Kupfer,
    }
}
