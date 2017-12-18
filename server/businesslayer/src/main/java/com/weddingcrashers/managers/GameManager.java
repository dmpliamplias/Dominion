package com.weddingcrashers.managers;

import com.weddingcrashers.businessmodels.*;
import com.weddingcrashers.model.Highscore;
import com.weddingcrashers.model.User;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.servermodels.*;
import com.weddingcrashers.service.HighscoreService;
import com.weddingcrashers.util.businesslayer.ServerUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
@author Michel Schlatter
*/
public class GameManager extends Manager {
    private static final String PATH = "/translation/trans";
    private static ArrayList<Card> unusedCards; // this field is static 'cause it's for every gamemanager instance the same cards
    private static List<Client> players;
    private static HashMap<Integer, User> users; // Key = ClientId
    private static boolean gameRunning;
    private static int round = 1;
    private static ArrayList<Integer> usersRoundPlayed = new ArrayList<Integer>();

    private static GameSettings gameSettings;

    public GameManager(Client client){
        super(client);
    }

    /*----------------------------------------
     PUBLIC METHODS
     */

    /**
     * sends all inital cardsets to the requested client
     */
    public void sendInitalCardSets(){
        ArrayList<PlayerSet> playerSets = new ArrayList<PlayerSet>();
        GameContainer gc = new GameContainer(Methods.InitialCardSets);

        for(Client player : players){
            playerSets.add(player.getDominionSet());
            if(player.isActive()){
                gc.setUserIdHasTurn((int)player.getUser().getId());
            }
        }

        gc.setUnusedCards(unusedCards);
        gc.setPlayerSets(playerSets);
        ServerUtils.sendObject(client, gc);
    }

    /**
     * this method gets called when a user made a completed turn.
     * @param gcReceived
     */
    public void moveFinished(GameContainer gcReceived){
        if(client.isActive()) {
            //PlayerSet set = gcReceived.getDominionSet(); // everything which gets buyed is logged, so no need for this code...
            //client.setDominionSet(set);

            GameContainer gc = new GameContainer(Methods.TurnFinished);
            //gc.setDominionSet(set); // think we don't need this...

            if (usersRoundPlayed.size() < (players.size()-1)) {
                usersRoundPlayed.add((int)client.getUser().getId());
            } else {
                usersRoundPlayed = new ArrayList<Integer>();
                round++;
                updateRound();
            }
            gameTurnFinishedCompletely(gc, checkGameAbortCondition());
        }else{
            ServerUtils.sendError(client, new Exception("This is not your turn!"));
        }
    }

    /**
     * this method gets called each time a player plays a card.
     * the card which was played will be broadcasted to each player
     * @param cardPlayedInfo
     */
    public void cardPlayed(CardPlayedInfo cardPlayedInfo){
        if(client.isActive()) {
            GameContainer gc = new GameContainer(Methods.CardPlayed);
            gc.setCardPlayedInfo(cardPlayedInfo);
            cardPlayedInfo.setClientId(client.getClientId());
            broadCast(gc);
        }else{
            ServerUtils.sendError(client, new Exception("This is not your turn!"));
        }
    }

    /**
     * this method gets called each time a player buys a card
     * the card which was buyed will be broadcasted to each player
     * @param gc
     */
    public void buyCard(GameContainer gc){
        if(client.isActive()) {
            CardPlayedInfo info = gc.getCardPlayedInfo();
            Card buyCard = info.getCard();

            Card card = getCardByName(buyCard.getName(), true);

            GameContainer bGc = new GameContainer(Methods.BuyCard);
            CardPlayedInfo buyInfo = new CardPlayedInfo();
            buyInfo.setClientId(client.getClientId());
            buyInfo.setCard(card);
            buyInfo.setUserId((int) users.get(client.getClientId()).getId());
            bGc.setCardPlayedInfo(buyInfo);
            //bGc.setUnusedCards(unusedCards);
            this.client.getDominionSet().getTrayStack().add(card);
            broadCast(bGc);
        }else{
           ServerUtils.sendError(client, new Exception("This is not your turn!"));
         }
    }

    /*--------------------------------
    PRIVATE MATHODS
     */
    private void broadCast(GameContainer gc){
        for(Client c : players){
            GameContainer newGc = gc.clone();
            ServerUtils.sendObject(c, newGc);
        }
    }

    /**
     * checks if the abort condition is fullfilled or not
     * @return if the abort condition is fullfilled or not.
     */
    private boolean checkGameAbortCondition(){
        boolean abort = false;
        if(gameSettings.getFinishAfterRounds() >= 1){
            abort = round > gameSettings.getFinishAfterRounds();
        }else if(gameSettings.isPointCards()){
            int provinzCounter = 0;
            for(Card c : unusedCards){
                if(c.getName().equals("Provinz")){
                    provinzCounter++;
                    break;
                }
            }
            ArrayList<String> actionCards = new ArrayList<String>();
            actionCards.add("Dorf");
            actionCards.add("Garten");
            actionCards.add("Geldverleiher");
            actionCards.add("Holzfäller");
            actionCards.add("Jahrmarkt");
            actionCards.add("Laboratorium");
            actionCards.add("Markt");
            actionCards.add("Schmiede");

            int emptyActionStacksCounter = 0;
            for(String cardName : actionCards){
                Card c = getCardByName(cardName, false);
                if(c == null){
                    emptyActionStacksCounter++;
                }
            }
            abort = provinzCounter < 1 || emptyActionStacksCounter >= 3;
        }
        return abort;
    }

    /**
     * updates the round and broadcasts it to each player in the game
     */
    private void updateRound(){
        GameContainer gc = new GameContainer(Methods.UpdateRound);
        gc.setRound(round);
        broadCast(gc);
    }

    /**
     * sends the winninginformation if the abortcondition is true
     * or if not, changes the active user and broadcasts this info to each player
     * @param container
     * @param abortGame
     */
    private void gameTurnFinishedCompletely(GameContainer container, boolean abortGame){
        if(abortGame){
           ArrayList<WinningInformation> winningInfos = new ArrayList<WinningInformation>();
            HighscoreService hs = serviceLocator.getHighscoreService();
            for(Client c : players){
               int points = c.getDominionSet().calculatePoints();
               winningInfos.add(new WinningInformation(c.getClientId(), (int)c.getUser().getId(), c.getUser().getUserName() ,points));
            }
            Collections.sort(winningInfos);
            for(WinningInformation info : winningInfos){
                info.setPosition(winningInfos.indexOf(info) + 1);

                Highscore highscore = new Highscore();
                LocalDateTime endTime = LocalDateTime.now();
                highscore.setDateOfHighscore(endTime);
                highscore.setUser(users.get(info.getClientId()));
                highscore.setPoints(info.getPoints());
                highscore.setDurationForHighscore(0);
                hs.saveHighscore(highscore);
            }
            container.setWinningInformation(winningInfos);
        }else {
            int nxtId = getNextTurnClientId();

            //container.getDominionSet().setUserId((int) client.getUser().getId());
            container.setUserIdHasTurn((int) users.get(nxtId).getId());
            for(Client c : players) {
                c.setActive(c.getClientId() == nxtId);
            }
        }
        broadCast(container);

        if(abortGame){
            GameManager.dispose();
        }
    }

    /**
     * gets the next active user
     * @return the next active users clientid
     */
    private int getNextTurnClientId(){
        int currentIdActive = client.getClientId();
        List<Integer> ids = new ArrayList<Integer>();

        for(Client c : players){
            ids.add(c.getClientId());
        }
        Collections.sort(ids);

        int currentIdx = ids.indexOf(currentIdActive);
        int nxtIdx = ++currentIdx;

        if(nxtIdx > (ids.size()-1)){
            nxtIdx = 0;
        }

        int nxtIdActive = ids.get(nxtIdx);
        return nxtIdActive;
    }

    /*-------------------------------------------------
    STATIC METHODS
     */

    /**
     * created the inital cardset
     * @return inital cardset
     */
    public static ArrayList<Card> createInitalCardSet(){
        ArrayList<Card> pullStack = new ArrayList<Card>();

        for(int i = 0; i < 7; i++){
            pullStack.add(getCardByMoneyType(MoneyType.Copper, false));
        }
        for(int i = 0; i < 3; i++){
            pullStack.add(getCardByPointCardType(PointCardType.Estate, false));
        }
        Collections.shuffle(pullStack);
        return pullStack;
    }


    /**
     * creates the whole dominionset with all cards specified
     * @param players player count
     */
    public static void createDominionSet(int players){

        unusedCards = new ArrayList<Card>();
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
            pc3.setCost(8);
            pc3.setValue(6);
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

    /**
     * gets a card by name from the unused cards
     * @param name of the card
     * @param remove - remove from unusedcardlist
     * @return returns the first card found in the list
     */
    private static Card getCardByName(String name, boolean remove){
        Card result = null;
        for(Card c : unusedCards){
            if(c.getName().equals(name)){
                result = c;
                break;
            }
        }
        if(remove && result != null) {
            unusedCards.remove(result);
        }
        return result;
    }

    /**
     * gets a card by its class
     * @param cls the class of the searched card
     * @param remove - remove from unusedcardlist
     * @return returns the first card found in the list
     */
    private static <T extends Card> T getCardByClass(Class<T> cls, boolean remove){
        T result = null;
        for(Card c : unusedCards){
            if(cls.isInstance(c)){
                result = (T)c;
                break;
            }
        }
        if(remove && result != null) {
            unusedCards.remove(result);
        }
        return result;
    }

    private static PointCard getCardByPointCardType(PointCardType type, boolean remove){
        PointCard result = null;
        for(Card c : unusedCards){
            if(c instanceof  PointCard){
                PointCard card = (PointCard)c;
                if(card.getPointCardType() == type) {
                    result = card;
                    break;
                }
            }
        }
        if(remove && result != null) {
            unusedCards.remove(result);
        }
        return result;
    }

    private  static MoneyCard getCardByMoneyType(MoneyType type, boolean remove){
        MoneyCard result = null;
        for(Card c : unusedCards){
            if(c instanceof  MoneyCard){
                MoneyCard card = (MoneyCard)c;
                if(card.getMoneyType() == type) {
                    result = card;
                    break;
                }
            }
        }
        if(remove && result != null) {
            unusedCards.remove(result);
        }
        return result;
    }

    /**
     * resets the gamemanager so that a new game can be started
     */
    public static void dispose(){
        for(Client c : players){
            c.setDominionSet(null);
            c.setActive(false);
        }
        gameSettings = null;
        players = null;
        users = null;
        gameRunning = false;
        unusedCards = null;
        usersRoundPlayed = new ArrayList<Integer>();
        round = 1;
    }

    private static int getNumberOfCards(int players, CardType cardType){
        // PointCards
       if(cardType == CardType.Anwesen
               || cardType == CardType.Herzogtum
               || cardType == CardType.Provinz){
           if(players <= 2) return 8;
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
            return 40; // auf Wunsch von Vanessa und Dozent Lukas Frey
           /* if(players <= 2) return 146;
            if(players == 3) return 139;
            if(players == 4) return 132;*/
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

    public static HashMap<Integer, User> getUsers() {
        return users;
    }

    public static void setUsers(HashMap<Integer, User> users) {
        GameManager.users = users;
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
