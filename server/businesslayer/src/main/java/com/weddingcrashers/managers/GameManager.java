package com.weddingcrashers.managers;

import com.weddingcrashers.businessmodels.*;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.servermodels.GameSettings;
import com.weddingcrashers.util.businesslayer.ServerUtils;
import com.weddingcrashers.servermodels.GameContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameManager extends Manager {
    private static final String PATH = "/translation/trans";
    private static List<Card> unusedCards; // this field is static 'cause it's for every gamemanager instance the same cards
    private static List<Client> players;
    private static boolean gameRunning;

    private static GameSettings gameSettings;

    public GameManager(Client client){
        super(client);
    }
    
    public static  void initialize(){
        unusedCards = new ArrayList<Card>();
        // TODO: 03.10.2017 add card set here and delete cards from the list, when user pulls it. 
    }

    private void pullInitalCardSetForEachClient(){
     for(Client player : players){
         DominionSet set = new DominionSet();
         ArrayList<Card> pullStack = new ArrayList<Card>();

         for(int i = 0; i < 7; i++){
             pullStack.add(this.getCardByMoneyType(MoneyType.Copper));
         }
         for(int i = 0; i < 3; i++){
             pullStack.add(this.getCardByPointCardType(PointCardType.Estate));
         }
         set.setPullStack(pullStack);
         set.pullHandStack();
         player.setDominionSet(set);
     }
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


    // jeder spieler bekommt 7 kupfer und 3 anwesen (Pointcards), Estate

    public static void createDominionSet(int players){

        // PointCards
        for(int i = 0; i < getNumberOfCards(players, CardType.Anwesen); i++) {
            PointCard pc1 = new PointCard();
            pc1.setPointCardType(PointCardType.Estate);
            pc1.setCost(2);
            pc1.setValue(1);
            pc1.setName("Anwesen");
            pc1.setFilePath("anwesen_{0}.png");
            unusedCards.add(pc1);
        }

        for(int i = 0; i < getNumberOfCards(players, CardType.Herzogtum); i++) {
            PointCard pc2 = new PointCard();
            pc2.setPointCardType(PointCardType.Duchy);
            pc2.setCost(5);
            pc2.setValue(3);
            pc2.setName("Herzogtum");
            pc2.setFilePath("herzogtum_{0}.png");
            unusedCards.add(pc2);
        }

        for(int i = 0; i < getNumberOfCards(players, CardType.Provinz); i++) {
            PointCard pc3 = new PointCard();
            pc3.setPointCardType(PointCardType.Province);
            pc3.setCost(6);
            pc3.setValue(8);
            pc3.setName("Provinz");
            pc3.setFilePath("provinz_{0}.png");
            unusedCards.add(pc3);
        }

        // MoneyCards
        for(int i = 0; i < getNumberOfCards(players, CardType.Kupfer); i++) {
            MoneyCard mc1 = new MoneyCard();
            mc1.setMoneyType(MoneyType.Copper);
            mc1.setValue(1);
            mc1.setCost(0);
            mc1.setName("Kupfer");
            mc1.setFilePath("kupfer_{0}.png");
            unusedCards.add(mc1);
        }

        for(int i = 0; i < getNumberOfCards(players, CardType.Silber); i++) {
            MoneyCard mc2 = new MoneyCard();
            mc2.setMoneyType(MoneyType.Silver);
            mc2.setValue(2);
            mc2.setCost(3);
            mc2.setName("Silber");
            mc2.setFilePath("silber_{0}.png");
            unusedCards.add(mc2);
        }

        for(int i = 0; i < getNumberOfCards(players, CardType.Gold); i++) {
            MoneyCard mc3 = new MoneyCard();
            mc3.setMoneyType(MoneyType.Gold);
            mc3.setValue(3);
            mc3.setCost(6);
            mc3.setName("Gold");
            mc3.setFilePath("gold_{0}.png");
            unusedCards.add(mc3);
        }

        // KingCards

        for(int i = 0; i < 10; i++) {

            KingCard kc1 = new KingCard();
            kc1.setActions(2);
            kc1.setBuys(0);
            kc1.setCards(1);
            kc1.setCost(3);
            kc1.setValue(0);
            kc1.setKingCardType(KingCardType.Action);
            kc1.setFilePath("dorf_{0}.png");
            kc1.setName("Dorf");

            KingCard kc2 = new KingCard();
            kc2.setActions(0);
            kc2.setBuys(0);
            kc2.setCards(0);
            kc2.setCost(4);
            kc2.setValue(1);
            kc2.setSpecialAction(SpecialAction.Value1ForThenCardsInStack);
            kc2.setFilePath("gaerten_{0}.png");
            kc2.setName("Garten");
            kc2.setKingCardType(KingCardType.Special);

            KingCard kc3 = new KingCard();
            kc3.setActions(0);
            kc3.setBuys(0);
            kc3.setCards(0);
            kc3.setCost(4);
            kc3.setValue(3);
            kc3.setSpecialAction(SpecialAction.RemoveCopperGet3Coins);
            kc3.setFilePath("geldverleiher_{0}.png");
            kc3.setName("Geldverleiher");
            kc3.setKingCardType(KingCardType.Special);

            KingCard kc4 = new KingCard();
            kc4.setActions(0);
            kc4.setBuys(1);
            kc4.setCards(0);
            kc4.setCost(3);
            kc4.setValue(2);
            kc4.setFilePath("holzfaeller_{0}.png");
            kc4.setName("Holzfäller");
            kc4.setKingCardType(KingCardType.Action);

            KingCard kc5 = new KingCard();
            kc5.setActions(2);
            kc5.setBuys(1);
            kc5.setCards(0);
            kc5.setCost(5);
            kc5.setValue(2);
            kc5.setFilePath("jahrmarkt_{0}.png");
            kc5.setName("Jahrmarkt");
            kc5.setKingCardType(KingCardType.Action);

            KingCard kc6 = new KingCard();
            kc6.setActions(1);
            kc6.setBuys(0);
            kc6.setCards(2);
            kc6.setCost(5);
            kc6.setValue(0);
            kc6.setFilePath("laboratorium_{0}.png");
            kc6.setName("Laboratorium");
            kc6.setKingCardType(KingCardType.Action);

            KingCard kc7 = new KingCard();
            kc7.setActions(1);
            kc7.setBuys(1);
            kc7.setCards(1);
            kc7.setCost(5);
            kc7.setValue(1);
            kc7.setFilePath("markt_{0}.png");
            kc7.setName("Markt");
            kc7.setKingCardType(KingCardType.Action);

            KingCard kc8 = new KingCard();
            kc8.setActions(0);
            kc8.setBuys(0);
            kc8.setCards(3);
            kc8.setCost(4);
            kc8.setValue(0);
            kc8.setFilePath("schmiede_{0}.png");
            kc8.setName("Schmiede");
            kc8.setKingCardType(KingCardType.Action);

            unusedCards.add(kc1);
            unusedCards.add(kc2);
            unusedCards.add(kc3);
            unusedCards.add(kc4);
            unusedCards.add(kc5);
            unusedCards.add(kc6);
            unusedCards.add(kc7);
            unusedCards.add(kc8);
        }

    }

    public Card getCardByName(String name){
        Card result = null;
        for(Card c : unusedCards){
            if(c.getName().equals(name)){
                result = c;
                unusedCards.remove(c);
            }
        }
        return result;
    }


    public <T extends Card> T getCardByClass(Class<T> cls){
        T result = null;
        for(Card c : unusedCards){
            if(cls.isInstance(c)){
                result = (T)c;
                unusedCards.remove(c);
            }
        }
        return result;
    }

    public PointCard getCardByPointCardType(PointCardType type){
        PointCard result = null;
        for(Card c : unusedCards){
            if(c instanceof  PointCard){
                PointCard card = (PointCard)c;
                if(card.getPointCardType() == type) {
                    unusedCards.remove(c);
                    result = card;
                }
            }
        }
        return result;
    }

    public MoneyCard getCardByMoneyType(MoneyType type){
        MoneyCard result = null;
        for(Card c : unusedCards){
            if(c instanceof  MoneyCard){
                MoneyCard card = (MoneyCard)c;
                if(card.getMoneyType() == type) {
                    unusedCards.remove(c);
                    result = card;
                }
            }
        }
        return result;
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

    public static List<Client> getPlayers() {
        return players;
    }

    public static void setPlayers(List<Client> players) {
        GameManager.players = players;
    }

    public static boolean isGameRunning() {
        return gameRunning;
    }

    public static void setGameRunning(boolean gameRunning) {
        GameManager.gameRunning = gameRunning;
    }

    public static GameSettings getGameSettings() {
        return gameSettings;
    }

    public static void setGameSettings(GameSettings gameSettings) {
        GameManager.gameSettings = gameSettings;
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
