package com.weddingcrashers.managers;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.ServerUtils;
import com.weddingcrashers.servermodels.GameContainer;
import com.weddingcrashers.servermodels.Methods;
import com.weddingcrashers.servermodels.ViewStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameManager extends Manager {

    public GameManager(Client client){
        super(client);
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
