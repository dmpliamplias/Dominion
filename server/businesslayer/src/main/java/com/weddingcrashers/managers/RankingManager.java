package com.weddingcrashers.managers;

import com.weddingcrashers.model.Highscore;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.util.businesslayer.ServerUtils;
import com.weddingcrashers.servermodels.RankingContainer;
import com.weddingcrashers.service.ServiceLocator;

import java.util.List;

/**
 * @author Michel Schlatter
 */
public class RankingManager extends Manager {

    public RankingManager(Client c){
        super(c);
    }

    /**
     * Sends the Highscore to the Client
     */
    public void sendRanking(){
        List<Highscore> highscores = ServiceLocator.getServiceLocator().getHighscoreService().getAllHighscores();

        RankingContainer rc = new RankingContainer();
        rc.setHighScores(highscores);

        ServerUtils.sendObject(client, rc);
    }
}
