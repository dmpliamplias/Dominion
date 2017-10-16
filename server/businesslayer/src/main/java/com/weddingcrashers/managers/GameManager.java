package com.weddingcrashers.managers;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.util.businesslayer.ServerUtils;
import com.weddingcrashers.servermodels.GameContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameManager extends Manager {
    
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


}
