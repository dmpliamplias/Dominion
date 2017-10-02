package com.weddingcrashers.managers;

import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.ServerUtils;
import com.weddingcrashers.servermodels.GameContainer;
import com.weddingcrashers.servermodels.Methods;
import com.weddingcrashers.servermodels.ViewStatus;

import java.util.Collections;
import java.util.List;


public class GameManager extends Manager {

    public GameManager(Client client){
        super(client);
    }

    
    public void broadCastChatMessageToAllClients(String msg){
        // TODO: 02.10.2017 Manuel => broadcast you can use... 
        GameContainer gc = new GameContainer(Methods.Chat);
        gc.setChatMsg(msg);

        for(Client c : client.getOtherClients()){
            if(c.getViewStatus() == ViewStatus.Game) {
                ServerUtils.sendObject(c, gc);
            }
        }

    }
    /**
     * @author Murat Kelleci
     */
    public static void shuffle(List<Card> cardDeck){
        Collections.shuffle(cardDeck);
    }

}
