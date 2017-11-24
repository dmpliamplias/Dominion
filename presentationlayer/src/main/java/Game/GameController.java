package Game;


import Controls.CardImageView;
import com.weddingcrashers.businessmodels.*;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.*;
import com.weddingcrashers.service.Language;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.scene.image.Image;
import org.h2.mvstore.DataUtils;
import util.PLServiceLocator;
import util.ViewUtils;
import base.Controller;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.KingCard;
import com.weddingcrashers.businessmodels.MoneyCard;
import com.weddingcrashers.businessmodels.PointCard;

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

        Platform.runLater(() ->{
            if(userIdHasTurn == serverConnectionService.getClientId()){
                // TODO: 12.11.2017  this is your turn... enable btns etc.
            }else{
                // TODO: 12.11.2017 this is not your turn.... disable btns etc.
            }
            // highlight the user who has the turn...
            view.updateUnusedCards(this.unusedCards);
        });


    }

    public void handleServerAnswer_updateRound(int round){
        Platform.runLater(() ->{
            // TODO: 12.11.2017 Vane update round here (show in view)
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
            // TODO: 20.11.2017  Vanessa: update unusedCards in View 
        });
    }
    
    public void handleServerAnswer_logCardPlayed(CardPlayedInfo cardPlayedInfo){
        Platform.runLater(() ->{
            User user = users.get(cardPlayedInfo.getUserId());
            Card card = cardPlayedInfo.getCard();
            // TODO: 12.11.2017 Vane log here the user and which card he played
        });
    }

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

    /**
     * author Manuel Wirz
     */
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

    // Methode for to receive the chatContainer from the server and set new text in the chat
    public void handleServerAnswer_receiveMessage(ChatContainer chatContainer) {
        Platform.runLater( () -> {
            view.setChatMessage(chatContainer.getMsg(), ViewUtils.getColorByClientId(chatContainer.getClientId()));
        } );

    }

    // Author Murat Kelleci

    private void setCardImageViewAction(CardImageView imgv){
        imgv.setOnMouseClicked(e -> {
            runAction(imgv);
        });
    }

    private void runAction(CardImageView imgv){
        Card c = imgv.getCard();
        if (imgv.getCardSize() == CardImageView.CardSize.miniSize | imgv.getCardSize() == CardImageView.CardSize.miniMini){
            buyCards(c);
        }else {
            return;
        }

        // ToDo If Card miniSize oder miniMini dann kaufen
        // ToDo für Kaufen Kauf nur Action
        // ToDo für Kaufen Kaufen und Geld

        if(c instanceof KingCard){
            KingCard kc = (KingCard)c;

        }else if(c instanceof MoneyCard){
            MoneyCard mc= (MoneyCard)c;

        }else if(c instanceof PointCard){
            PointCard pc= (PointCard)c;

        }
    }

    private void buyCards(Card card){
        GameContainer gc = new GameContainer(Methods.BuyCard);
        CardPlayedInfo buyInfo = new CardPlayedInfo();
        buyInfo.setUserId((int)getUser().getId());
        buyInfo.setCard(card);

        try {
            PLServiceLocator.getPLServiceLocator().getServerConnectionService().sendObject(gc);
        } catch (IOException e) {
            view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private User getUser(){
        return PLServiceLocator.getPLServiceLocator().getUser();
    }





}