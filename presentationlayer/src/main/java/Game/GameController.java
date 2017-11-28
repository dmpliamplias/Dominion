package Game;


import Controls.CardImageView;
import com.weddingcrashers.businessmodels.*;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.*;
import util.PLServiceLocator;
import util.ViewUtils;
import base.Controller;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.KingCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  author Michel Schlatter
 *  */

public class GameController extends Controller<GameModel, GameView> {

    private  PlayerSet myCardSet;
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

    public GameController(GameModel model, GameView view, HashMap<Integer, User> usersAndClientIds,
                          GameSettings gameSettings) {
        super( model, view );
        serverConnectionService.setGameController( this );
        this.usersAndClientIds = usersAndClientIds;
        this.gameSettings = gameSettings;
        initialize();

        myUser = usersAndClientIds.get(serverConnectionService.getClientId());
        users = new HashMap<Integer, User>();

        Iterator iter = usersAndClientIds.entrySet().iterator();
        while(iter.hasNext()){
          Map.Entry<Integer, User> pair = (Map.Entry<Integer, User>) iter.next();
          int key = (int)pair.getValue().getId();
          User user = pair.getValue();
          users.put(key, user);
        }
    }


    public void initialize() {
        try {
            serverConnectionService.updateViewStatus( ViewStatus.Game ); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }


        // TODO: 12.11.2017 Vane => show GameSettings



        if(gameSettings.isPointCards() == true){
            view.endOption.setText( view.endOptionPoints.getText() );
        }

        if(gameSettings.getFinishAfterRounds() > 1){
            view.endOption.setText( view.endOptionRounds.getText() + " " + gameSettings.getFinishAfterRounds() );
        }


        resetActionBuyMoney();
        updateActionBuyMoney();

        view.btnPlayMoneyCards.setOnAction( event -> {
            for(int i = 0; i < view.handStackList.size(); i++){
                if (view.handStackList.get(i).getCard().getName().equals("Kupfer")){
                    numberOfMoney++;}
                if (view.handStackList.get(i).getCard().getName().equals("Silber")){
                    numberOfMoney = numberOfMoney + 2;}
                if (view.handStackList.get(i).getCard().getName().equals("Gold")){
                    numberOfMoney = numberOfMoney + 3;}
             }
            updateActionBuyMoney();
            view.moveMoneyCardsToPlayArea();
        } );


        view.btnEndTurn.setOnAction( event2 -> {
            endOfTurn();
        } );



        /**
         *  author Manuel Wirz
         *  */



        view.getBtnChatSend().setOnAction( event -> {
            sendMessage();
        } );

        view.getBtnSendText().setOnAction( event -> {
            sendButtonText();
        } );

        view.getTextFieldChat().setOnKeyPressed(event -> {
            if (event.getCode().equals( KeyCode.ENTER)){
                sendMessage();
            }  });


    }

    /* ------------------------- receiving smth from Server ----------------------*/

    // after initalizing the gameview and after each complete turn the player makes, this methods gets called
    public void handleServerAnswer_receivePlayerSet(PlayerSet set, ArrayList<Card> unusedCards, int userIdHasTurn){
      if(set.getUserId() == myUser.getId()){
          this.myCardSet = set;
      }else{
          this.enemyCards.put(set.getUserId(), set);
      }
      if(unusedCards != null && unusedCards.size() > 0){
          this.unusedCards = unusedCards;
          for(Card uc : unusedCards){
             if(uc.getName().equals("Anwesen")){
                 boolean found = true;
             }
          }
      }

      activeUserId = userIdHasTurn;

      /**
       *  author Manuel Wirz
       *  */

        Platform.runLater(() ->{
            if(userIdHasTurn == serverConnectionService.getClientId()){
                // TODO: 12.11.2017  this is your turn... enable btns etc.

            }else{
                // TODO: 12.11.2017 this is not your turn.... disable btns etc.
            }
            // highlight the user who has the turn...
            updateUnusedCards(unusedCards);
            System.out.println(myCardSet.getHandStack());
            drawHandCards(5);
            drawHandCards(3);


        });


    }

    // a player finished his turn...
    public void handleServerAnswer_gameTurnFinished(GameContainer gc){
        Platform.runLater(()-> {
            if(gc.getWinningInformations() != null & gc.getWinningInformations().size() > 0){
                // TODO: 25.11.2017 Vane: eine Bedingung für das Ende des Spiels wurde erfüllt 
                // hier hast du die Informationen über den Rang der Spieler usw:
                for(WinningInformation wi : gc.getWinningInformations()){
                   int rang = wi.getPosition();
                   User user = users.get(wi.getUserId());
                   int points = wi.getPoints();

                    // TODO: 25.11.2017 Vane: Display the data to each user
                }
            }else{
               activeUserId =  gc.getUserIdHasTurn();
               if(myUser.getId() == activeUserId){
                   // TODO: 25.11.2017 Vane enable this user, it's his turn now... 
               }else{
                   // TODO: 25.11.2017 Vane disable this user, it's not his turn now...
               }
            }
        });
    }

    public void handleServerAnswer_updateRound(int round){
        Platform.runLater(() ->{
            // TODO: Murat 12.11.2017 update round here (show in view)
        });
    }

    public void handleServerAnswer_cardBuyed(GameContainer gc){
        Platform.runLater(() ->{
            CardPlayedInfo buyedInfo = gc.getCardPlayedInfo();
            if(buyedInfo.getUserId() == myUser.getId()){
                myCardSet.getTrayStack().add(buyedInfo.getCard());
                // TODO: 20.11.2017  update View ....
            }else{
                // TODO: 20.11.2017  another Player has one Card more... maybe update smth in view... 
            }
            unusedCards = gc.getUnusedCards();
            updateUnusedCards(unusedCards);

        });
    }
    


    // Methode for to receive the chatContainer from the server and set new text in the chat
    public void handleServerAnswer_receiveMessage(ChatContainer chatContainer) {
        Platform.runLater( () -> {
            view.setChatMessage(chatContainer.getMsg(), ViewUtils.getColorByClientId(chatContainer.getClientId()));
        } );

    }






    /*----------------Send to smth. to Server ------------- */

    public void cardPlayed(Card c){
        GameContainer gc = new GameContainer(Methods.CardPlayed);
        CardPlayedInfo info = new CardPlayedInfo();
        info.setUserId((int)myUser.getId());
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

            myCardSet.getTrayStack().add(card);
            numberOfBuys--;
            numberOfMoney = numberOfMoney - card.getCost();
            updateActionBuyMoney();

            if (numberOfBuys == 0) {
                endOfTurn();
            }

            try {
                PLServiceLocator.getPLServiceLocator().getServerConnectionService().sendObject(gc);
            } catch (IOException e) {
                view.alert(e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    public void moveFinished(){
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

    public void handleServerAnswer_logCardPlayed(CardPlayedInfo cardPlayedInfo){
        Platform.runLater(() ->{
            User user = users.get(cardPlayedInfo.getUserId());
            Card card = cardPlayedInfo.getCard();
            String logger = new String( user + "/n " + card );
            view.setLoggerContent(logger, ViewUtils.getColorByClientId( cardPlayedInfo.getClientId() ) );
        });
    }


    // Method to send the ChatContainer to the server and display the message in the own screen

    public void sendMessage() {
        String message = plServiceLocator.getUser().getUserName() + ": " + view.getTextFieldChat().getText();
        ChatContainer cc = new ChatContainer();
        cc.setClientId(plServiceLocator.getServerConnectionService().getClientId());
        cc.setMsg( message);
        view.getTextFieldChat().clear();
        view.setChatMessage(message, ViewUtils.getColorByClientId(cc.getClientId()));

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }


    // Button to send predefined text to the server and display the message on the own screen

    public void sendButtonText() {

        String message = plServiceLocator.getUser().getUserName() + ": " + view.getBtnSendText().getText();
        ChatContainer cc = new ChatContainer();
        cc.setClientId(plServiceLocator.getServerConnectionService().getClientId());
        cc.setMsg( message );

        view.setChatMessage(message, ViewUtils.getColorByClientId(cc.getClientId()));

        try {
            serverConnectionService.sendObject( cc );
        } catch (IOException e) {
            view.alert( e.getMessage(), Alert.AlertType.ERROR );
        }
    }



    // Author Murat Kelleci 24.11.17

    private void setCardImageViewAction(CardImageView imgv){
        imgv.setOnMouseClicked(e -> {
            runAction(imgv);
        });
    }

    // Author Murat Kelleci 24.11.17

    private void runAction(CardImageView imgv){
        Card c = imgv.getCard();
        if (imgv.getCardSize() == CardImageView.CardSize.miniSize | imgv.getCardSize() == CardImageView.CardSize.miniMini){
            buyCards(c);
        }


        if(c instanceof KingCard) {
            if (view.handStackList.contains(imgv)) {
                if (c.getName().equals("Garten") || c.getName().equals("Geldverleiher")) {
                    // TODO: Vanessa add Method for Geldverleiher

                } else {
                    numberOfActions += ((KingCard) c).getActions();
                    numberOfBuys += ((KingCard) c).getBuys();
                    numberOfMoney += c.getValue();

                    if (((KingCard) c).getCards() > 0) {
                        drawHandCards(((KingCard) c).getCards());
                    }
                    view.moveCardToPlayingArea(imgv);
                    updateActionBuyMoney();
                }
            }
        }
    }

    // Author Murat Kelleci 20.11.17 - 

    private User getUser(){
        return PLServiceLocator.getPLServiceLocator().getUser();
    }


    /**
     *  author Vanessa Cajochen
     *  */


    public void updateUnusedCards(ArrayList<Card> unusedCards)
    {
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
        indexes.add(new int[]{2,1,2});
        indexes.add(new int[]{2,3,2});
        indexes.add(new int[]{2,5,2});
        indexes.add(new int[]{7,1,2});
        indexes.add(new int[]{7,5,2});
        indexes.add(new int[]{7,3,2});
        indexes.add(new int[]{3,4,3});
        indexes.add(new int[]{5,4,3});
        indexes.add(new int[]{6,4,3});
        indexes.add(new int[]{4,4,3});
        indexes.add(new int[]{5,1,3});
        indexes.add(new int[]{4,1,3});
        indexes.add(new int[]{3,1,3});
        indexes.add(new int[]{6,1,3});

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


        for(int idx =0; idx < cardNames.size(); idx++){
            String cardName = cardNames.get(idx);
            int[] pos = indexes.get(idx);
            CardImageView imgView = view.setCardImageView(getCard(unusedCards, cardName), sizes.get(idx), pos[0], pos[1], pos[2],
                   countCards(unusedCards, cardName));
            setCardImageViewAction(imgView);
        }
    }


    public int countCards(ArrayList<Card> list, String s) {
        int count = 0;
        for (int i = 0; i < (list.size());i++ ){
            if (list.get(i).getName().equals(s)){
                count++;
            }
        }
        return count;
    }

    public Card getCard(ArrayList<Card> list, String name) {
        Card c = null;
        for (int i = 0; i < (list.size());i++ ) {
            if (list.get(i).getName().equals(name)) {
                c = list.get(i);
                break;
            }
        }
        return c;
    }




    public void drawHandCards(int numberOfDrawnCards) {
        for (int i = 0; i < numberOfDrawnCards; i++) {

            myCardSet.getHandStack().add(myCardSet.getPullStack().get(0));
            view.addCardToHandStackPane(myCardSet.getPullStack().get(0));
            myCardSet.getPullStack().remove(myCardSet.getPullStack().get(0));
            updateLblPullStack();
        }
    }


    public void resetActionBuyMoney(){
        numberOfActions = 1;
        numberOfBuys = 1;
        numberOfMoney = 0;
        updateActionBuyMoney();
    }


    public void updateActionBuyMoney(){
        view.updatelblInfo(numberOfActions, numberOfBuys, numberOfMoney);
    }


    // If Buys is 0, the turn is automatically finished. Handstack is moved to TrayStack.
    public void endOfTurn(){
        for (int i = (myCardSet.getHandStack().size()-1); i >= 0; i--) {
            myCardSet.getTrayStack().add(myCardSet.getHandStack().get(i));
            myCardSet.getHandStack().remove(i);
        }
            view.endOfTurn();
            resetActionBuyMoney();
            moveFinished();

    }


    public void updateLblPullStack(){
        view.updateLblPullStack(myCardSet.getPullStack().size());
    }





    }