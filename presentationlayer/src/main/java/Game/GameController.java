package Game;


import Controls.CardImageView;
import com.weddingcrashers.businessmodels.*;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.*;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import lobby.LobbyController;
import lobby.LobbyModel;
import lobby.LobbyView;
import util.PLServiceLocator;
import util.ViewUtils;
import base.Controller;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.KingCard;

import java.io.IOException;
import java.util.*;

/**
 *  author Michel Schlatter
 *  */

public class GameController extends Controller<GameModel, GameView> {

    private PlayerSet myCardSet;
    ArrayList<Card> unusedCards;
    HashMap<Integer, User> usersAndClientIds; // Key = ClientId
    HashMap<Integer, User> users; // Key = UserId
    HashMap<Integer, PlayerSet> enemyCards; // Key = UserID
    GameSettings gameSettings;
    User myUser;
    int activeUserId;
    int numberOfActions;
    int numberOfBuys;
    int numberOfMoney;
    boolean actionPhaseOver = false;

    public GameController(GameModel model, GameView view, HashMap<Integer, User> usersAndClientIds,
                          GameSettings gameSettings) {
        super(model, view);
        serverConnectionService.setGameController(this);
        this.usersAndClientIds = usersAndClientIds;
        this.gameSettings = gameSettings;

        myUser = usersAndClientIds.get(serverConnectionService.getClientId());
        users = new HashMap<Integer, User>();

        Iterator iter = usersAndClientIds.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, User> pair = (Map.Entry<Integer, User>) iter.next();
            int key = (int) pair.getValue().getId();
            User user = pair.getValue();
            users.put(key, user);
        }

        initialize();
    }


    public void initialize() {
        try {
            serverConnectionService.updateViewStatus(ViewStatus.Game); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }


        resetActionBuyMoney();
        updateActionBuyMoney();

        view.btnPlayMoneyCards.setOnAction(event -> {
            for (int i = 0; i < view.handStackList.size(); i++) {
                if (view.handStackList.get(i).getCard().getName().equals("Kupfer")) {
                    numberOfMoney++;
                }
                if (view.handStackList.get(i).getCard().getName().equals("Silber")) {
                    numberOfMoney = numberOfMoney + 2;
                }
                if (view.handStackList.get(i).getCard().getName().equals("Gold")) {
                    numberOfMoney = numberOfMoney + 3;
                }
            }
            updateActionBuyMoney();
            view.moveMoneyCardsToPlayArea();
            view.gp.getChildren().remove(view.btnPlayMoneyCards);
            view.gp.getChildren().add(view.btnEndTurn);
        });


        view.btnEndTurn.setOnAction(event2 -> {
            endOfTurn();
        });

        view.btnEndActionPhase.setOnAction(event3 -> {
            view.gp.getChildren().remove(view.btnEndActionPhase);
            view.gp.getChildren().add(view.btnPlayMoneyCards);
            actionPhaseOver = true;

        });





        /**
         *  author Manuel Wirz
         *  */

        if (gameSettings.isPointCards() == true) {
            view.endOption.setText(view.endOptionPoints.getText());
        } else {
            view.endOption.setText(view.endOptionRounds.getText() + " " + gameSettings.getFinishAfterRounds());
        }


        view.getBtnChatSend().setOnAction(event -> {
            sendMessage();
        });


        view.getBtnSendText().setOnAction(event -> {
            sendButtonText();
        });

        view.getTextFieldChat().setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                sendMessage();
            }
        });

        view.labelShowRound.setText(Integer.toString(1));
    }

    /* ------------------------- receiving smth from Server ----------------------*/

    public void handleServerAnswer_receiveInitalPlayerSet(ArrayList<PlayerSet> sets, ArrayList<Card> unusedCards, int userIdHasTurn) {
        boolean firstCall = myCardSet == null;

        for (PlayerSet set : sets) {
            if (set.getUserId() == myUser.getId()) {
                this.myCardSet = set;
            } else {
                if (enemyCards == null) {
                    enemyCards = new HashMap<Integer, PlayerSet>();
                }
                this.enemyCards.put(set.getUserId(), set);
                System.out.println("got an enemycard from => userid: " + users.get(set.getUserId()).getUserName());
            }
            if (unusedCards != null && unusedCards.size() > 0) {
                this.unusedCards = unusedCards;
            }
        }
        activeUserId = userIdHasTurn;


        /**
         *  author Manuel Wirz
         *  */

        Platform.runLater(() -> {
            if (userIdHasTurn == myUser.getId()) {
                // TODO: drawHAndCards ist just for testing
                drawHandCards(5);
                drawHandCards(3);
            }
            updateUnusedCards(unusedCards);
            enableOrDisableView();

            for (PlayerSet playerSet : getAllSets()) {
                System.out.println("Drawing the calculated points on client: " + myUser.getUserName() + " playersetsize: " + getAllSets().size());
                if (playerSet != null) {
                    setPointsToView(playerSet);
                }
            }

        });


    }

    // a player finished his turn...
    public void handleServerAnswer_gameTurnFinished(GameContainer gc) {
        Platform.runLater(() -> {
            if (gc.getWinningInformations() != null && gc.getWinningInformations().size() > 0) {
                // TODO: 25.11.2017 Vane: eine Bedingung für das Ende des Spiels wurde erfüllt 
                // hier hast du die Informationen über den Rang der Spieler usw:
                for (WinningInformation wi : gc.getWinningInformations()) {
                    int rang = wi.getPosition();
                    User user = users.get(wi.getUserId());
                    int points = wi.getPoints();

                    // TODO: 25.11.2017 Vane: Display the data to each user
                }
            } else {
                activeUserId = gc.getUserIdHasTurn();
                enableOrDisableView();
            }
        });
    }

    //Author Murat Kelleci
    public void handleServerAnswer_updateRound(int round) {
        Platform.runLater(() -> {
            view.labelShowRound.setText(String.valueOf(round));
        });
    }

    public void handleServerAnswer_cardBuyed(GameContainer gc) {
        Platform.runLater(() -> {
            CardPlayedInfo buyedInfo = gc.getCardPlayedInfo();
            PlayerSet updatedSet;
            if (buyedInfo.getUserId() == myUser.getId()) {
                myCardSet.getTrayStack().add(buyedInfo.getCard());
                updatedSet = myCardSet;
            } else {
                enemyCards.get(buyedInfo.getUserId()).getTrayStack().add(buyedInfo.getCard());
                updatedSet = enemyCards.get(buyedInfo.getUserId());
            }
            unusedCards = gc.getUnusedCards();
            updateUnusedCards(unusedCards);
            setPointsToView(updatedSet);
            // TODO: 30.11.2017  MANUEL WIRZ: KARTEN NAMEN ÜBER TRANSLATOR HOLEN
            view.setLoggerContent(users.get(updatedSet.getUserId()).getUserName() + ": "
                    + buyedInfo.getCard().getName(), ViewUtils.getColorByClientId(buyedInfo.getClientId()));
        });
    }


    // Methode for to receive the chatContainer from the server and set new text in the chat
    public void handleServerAnswer_receiveMessage(ChatContainer chatContainer) {
        Platform.runLater(() -> {
            view.setChatMessage(chatContainer.getMsg(), ViewUtils.getColorByClientId(chatContainer.getClientId()));
        });

    }


    /*----------------Send to smth. to Server ------------- */

    public void cardPlayed(Card c) {
        GameContainer gc = new GameContainer(Methods.CardPlayed);
        CardPlayedInfo info = new CardPlayedInfo();
        info.setUserId((int) myUser.getId());
        info.setCard(c);

        try {
            serverConnectionService.sendObject(gc);
        } catch (IOException e) {
            view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Author Murat Kelleci 20.11.17 -
    private void buyCards(Card card) {
        if (numberOfBuys > 0 && card.getCost() <= numberOfMoney) {
            GameContainer gc = new GameContainer(Methods.BuyCard);
            CardPlayedInfo buyInfo = new CardPlayedInfo();
            buyInfo.setUserId((int) getUser().getId());
            buyInfo.setCard(card);
            gc.setCardPlayedInfo(buyInfo);


            numberOfBuys--;
            numberOfMoney = numberOfMoney - card.getCost();
            updateActionBuyMoney();


            try {
                PLServiceLocator.getPLServiceLocator().getServerConnectionService().sendObject(gc);
            } catch (IOException e) {
                view.alert(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    // Method gets called when Buy = 0 or User clicks on Button "End Buy"
    public void moveFinished() {
        // TODO: 25.11.2017 Vane: call this method when a user completed his turn...

        GameContainer gc = new GameContainer(Methods.TurnFinished);
        try {
            serverConnectionService.sendObject(gc);
        } catch (IOException e) {
            view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * author Manuel Wirz
     */

    public void handleServerAnswer_logCardPlayed(CardPlayedInfo cardPlayedInfo) {
        Platform.runLater(() -> {
            User user = users.get(cardPlayedInfo.getUserId());
            Card card = cardPlayedInfo.getCard();
            String logger = new String(user + "/n " + card);
            // TODO: 30.11.2017  MANUEL WIRZ: KARTEN NAMEN ÜBER TRANSLATOR HOLEN
            view.setLoggerContent(card.getName(), ViewUtils.getColorByClientId(cardPlayedInfo.getClientId()));
        });
    }


    // Method to send the ChatContainer to the server and display the message in the own screen

    public void sendMessage() {
        String message = plServiceLocator.getUser().getUserName() + ": " + view.getTextFieldChat().getText();
        ChatContainer cc = new ChatContainer();
        cc.setClientId(plServiceLocator.getServerConnectionService().getClientId());
        cc.setMsg(message);
        view.getTextFieldChat().clear();
        view.setChatMessage(message, ViewUtils.getColorByClientId(cc.getClientId()));

        try {
            serverConnectionService.sendObject(cc);
        } catch (IOException e) {
            view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    // Button to send predefined text to the server and display the message on the own screen

    public void sendButtonText() {

        String message = plServiceLocator.getUser().getUserName() + ": " + view.getBtnSendText().getText();
        ChatContainer cc = new ChatContainer();
        cc.setClientId(plServiceLocator.getServerConnectionService().getClientId());
        cc.setMsg(message);

        view.setChatMessage(message, ViewUtils.getColorByClientId(cc.getClientId()));

        try {
            serverConnectionService.sendObject(cc);
        } catch (IOException e) {
            view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    // Author Murat Kelleci 24.11.17
    private void setCardImageViewAction(CardImageView imgv) {
        imgv.setOnMouseClicked(e -> {
            runAction(imgv);
        });
    }

    // Author Murat Kelleci 24.11.17
    private void runAction(CardImageView imgv) {

        Card c = imgv.getCard();
        if (imgv.getCardSize() == CardImageView.CardSize.miniSize | imgv.getCardSize() == CardImageView.CardSize.miniMini) {
            buyCards(c);
        }

        if (c instanceof MoneyCard) {
            if (actionPhaseOver == true && view.handStackList.contains(imgv)) {
                if (c.getName().equals("Kupfer")) {
                    numberOfMoney++;
                } else if (c.getName().equals("Silber")) {
                    numberOfMoney = numberOfMoney + 2;
                } else if (c.getName().equals("Gold")) {
                    numberOfMoney = numberOfMoney + 3;
                }

                view.moveCardToPlayingArea(imgv);
                updateActionBuyMoney();

                // btn weg wenn alle MoneyCards gespielt sind
                boolean containsMoneyCard = false;
                for (int i = 0; i < view.handStackList.size() - 1; i++) {
                    if (view.handStackList.get(i).getCard().getName().equals("Kupfer") || view.handStackList.get(i).getCard().getName().equals("Silber") || view.handStackList.get(i).getCard().getName().equals("Gold")) {
                        containsMoneyCard = true;
                        break;
                    }
                }
                if (containsMoneyCard == false) {
                    view.gp.getChildren().remove(view.btnPlayMoneyCards);
                    view.gp.getChildren().add(view.btnEndTurn);
                }
            }


        } else if (c instanceof KingCard) {
            if (actionPhaseOver == false && view.handStackList.contains(imgv) && numberOfActions > 0) {
                if (c.getName().equals("Garten")) {

                } else if (c.getName().equals("Geldverleiher")) {
                    for (int i = 0; i < myCardSet.getHandStack().size() - 1; i++) {
                        if (myCardSet.getHandStack().get(i).getName().equals("Kupfer")) {
                            myCardSet.getHandStack().remove(i);
                            view.trashCopper();
                            numberOfMoney = numberOfMoney + 3;
                            view.moveCardToPlayingArea(imgv);
                            numberOfActions -= 1;
                            updateActionBuyMoney();
                            break;
                        }
                    }

                } else {
                    numberOfActions += ((KingCard) c).getActions();
                    numberOfBuys += ((KingCard) c).getBuys();
                    numberOfMoney += c.getValue();

                    if (((KingCard) c).getCards() > 0) {
                        drawHandCards(((KingCard) c).getCards());
                    }
                    view.moveCardToPlayingArea(imgv);
                    numberOfActions -= 1;
                    updateActionBuyMoney();
                }
            }
        }
    }


    // Author Murat Kelleci 20.11.17 -
    private User getUser() {
        return PLServiceLocator.getPLServiceLocator().getUser();
    }


    /**
     * author Vanessa Cajochen
     */

    private void enableOrDisableView() {
        // TODO: 01.12.2017  Vanessa, das ganze muss in die view...du musst vom controller nur aufrufen können
        // view.disableView(), view.enableView()
        if (myUser.getId() == activeUserId) {
            view.gp.getChildren().removeAll(view.imgVGreyOutButton, view.imgVGreyOutHandStack);
        } else {
            view.gp.getChildren().addAll(view.imgVGreyOutButton, view.imgVGreyOutHandStack);
        }
        // highlight user who has the turn, fuckers! log it or show on murats left pane...
    }

    public void updateUnusedCards(ArrayList<Card> unusedCards) {
        if (unusedCards == null || unusedCards.size() == 0) {
            return;
        }

        long tStart = System.currentTimeMillis();
        ArrayList<String> cardNames = new ArrayList<String>();
        cardNames.add("Kupfer");
        cardNames.add("Silber");
        cardNames.add("Gold");
        cardNames.add("Anwesen");
        cardNames.add("Provinz");
        cardNames.add("Herzogtum");
        cardNames.add("Dorf");
        cardNames.add("Garten");
        cardNames.add("Geldverleiher");
        cardNames.add("Holzfäller");
        cardNames.add("Jahrmarkt");
        cardNames.add("Laboratorium");
        cardNames.add("Schmiede");
        cardNames.add("Markt");


        ArrayList<int[]> indexes = new ArrayList<int[]>();
        indexes.add(new int[]{2, 1, 2});
        indexes.add(new int[]{2, 3, 2});
        indexes.add(new int[]{2, 5, 2});
        indexes.add(new int[]{7, 1, 2});
        indexes.add(new int[]{7, 5, 2});
        indexes.add(new int[]{7, 3, 2});
        indexes.add(new int[]{3, 4, 3});
        indexes.add(new int[]{5, 4, 3});
        indexes.add(new int[]{6, 4, 3});
        indexes.add(new int[]{4, 4, 3});
        indexes.add(new int[]{5, 1, 3});
        indexes.add(new int[]{4, 1, 3});
        indexes.add(new int[]{3, 1, 3});
        indexes.add(new int[]{6, 1, 3});

        ArrayList<CardImageView.CardSize> sizes = new ArrayList<CardImageView.CardSize>();
        sizes.add(CardImageView.CardSize.miniMini);
        sizes.add(CardImageView.CardSize.miniMini);
        sizes.add(CardImageView.CardSize.miniMini);
        sizes.add(CardImageView.CardSize.miniMini);
        sizes.add(CardImageView.CardSize.miniMini);
        sizes.add(CardImageView.CardSize.miniMini);
        sizes.add(CardImageView.CardSize.miniSize);
        sizes.add(CardImageView.CardSize.miniSize);
        sizes.add(CardImageView.CardSize.miniSize);
        sizes.add(CardImageView.CardSize.miniSize);
        sizes.add(CardImageView.CardSize.miniSize);
        sizes.add(CardImageView.CardSize.miniSize);
        sizes.add(CardImageView.CardSize.miniSize);
        sizes.add(CardImageView.CardSize.miniSize);


        for (int idx = 0; idx < cardNames.size(); idx++) {
            String cardName = cardNames.get(idx);
            int[] pos = indexes.get(idx);
            CardImageView imgView = view.setCardImageView(getCard(unusedCards, cardName), sizes.get(idx), pos[0], pos[1], pos[2],
                    countCards(unusedCards, cardName));
            setCardImageViewAction(imgView);
        }
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        double elapsedSeconds = tDelta / 1000.0;
        System.out.println(elapsedSeconds);
    }


    public int countCards(ArrayList<Card> list, String s) {
        int count = 0;
        for (int i = 0; i < (list.size()); i++) {
            if (list.get(i).getName().equals(s)) {
                count++;
            }
        }
        return count;
    }

    public Card getCard(ArrayList<Card> list, String name) {
        Card c = null;
        for (int i = 0; i < (list.size()); i++) {
            if (list.get(i).getName().equals(name)) {
                c = list.get(i);
                break;
            }
        }
        return c;
    }


    public void drawHandCards(int numberOfDrawnCards) {
        // TODO: 01.12.2017 vane has an error
       /* for (int i = 0; i < numberOfDrawnCards; i++) {
            if (myCardSet.getPullStack().size() == 0){
                movesCardsFromTrayStackToPullStack();
            }
            myCardSet.getHandStack().add(myCardSet.getPullStack().get(0));
            CardImageView cardImg = new CardImageView(myCardSet.getPullStack().get(0), CardImageView.CardSize.bigSize);
            Tooltip tooltip = new Tooltip();
            CardImageView imgForToolTip = new CardImageView(myCardSet.getPullStack().get(0), CardImageView.CardSize.tooltip);
            tooltip.setGraphic(imgForToolTip);
            Tooltip.install(cardImg, tooltip);

            setCardImageViewAction(cardImg);
            view.addCardToHandStackPane(cardImg);
            myCardSet.getPullStack().remove(myCardSet.getPullStack().get(0));
            updateLblPullStack();


        }*/
    }




    public void resetActionBuyMoney() {
        numberOfActions = 1;
        numberOfBuys = 1;
        numberOfMoney = 0;
        updateActionBuyMoney();
    }


    //Author Murat Kelleci

    public void setPointsToView(PlayerSet playerSet) {

        int userId = playerSet.getUserId();
        String userName = users.get(userId).getUserName();
        System.out.println(userId);
        System.out.println(userName);

        this.view.setUserPoints(userId, userName, playerSet);

    }



    /*
    Author Vanessa Cajochen
     */

    public void updateActionBuyMoney() {
        view.updatelblInfo(numberOfActions, numberOfBuys, numberOfMoney);
    }


    // If Buys is 0, the turn is automatically finished. Handstack is moved to TrayStack.
    public void endOfTurn() {
        view.setBackCardOfTrashStack(myCardSet.getHandStack().get((myCardSet.getHandStack().size() - 1)));
        for (int i = (myCardSet.getHandStack().size() - 1); i >= 0; i--) {
            myCardSet.getTrayStack().add(myCardSet.getHandStack().get(i));
            myCardSet.getHandStack().remove(i);
        }
        view.endOfTurn();
        resetActionBuyMoney();
        moveFinished();
        view.gp.getChildren().remove(view.btnEndTurn);
        view.gp.getChildren().add(view.btnEndActionPhase);
        actionPhaseOver = false;
        drawHandCards(5);
    }

    // All cards from TrayStack move to PullStack and PullStack shuffles
    public void movesCardsFromTrayStackToPullStack() {
        view.setBackCardOfTrashStack();

        for (int i = (myCardSet.getTrayStack().size() - 1); i >= 0; i--) {
            myCardSet.getPullStack().add(myCardSet.getTrayStack().get(i));
            myCardSet.getTrayStack().remove(i);
        }

        Collections.shuffle(myCardSet.getPullStack());
    }


    public void updateLblPullStack() {
        view.updateLblPullStack(myCardSet.getPullStack().size());
    }




    protected ArrayList<PlayerSet> getAllSets() {
        ArrayList<PlayerSet> sets = new ArrayList<PlayerSet>();
        sets.add(myCardSet);
        Iterator iter = users.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, User> pair = (Map.Entry<Integer, User>) iter.next();
            User user = pair.getValue();
            if (user.getId() != myUser.getId()) {
                PlayerSet set = enemyCards.get((int) user.getId());
                if (set != null) {
                    sets.add(set);
                }
            }
        }
        return sets;
    }


}

