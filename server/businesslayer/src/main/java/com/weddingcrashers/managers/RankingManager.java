package com.weddingcrashers.managers;

import com.weddingcrashers.model.Highscore;
import com.weddingcrashers.server.Client;
import com.weddingcrashers.server.ServerUtils;
import com.weddingcrashers.servermodels.RankingContainer;
import com.weddingcrashers.service.ServiceLocator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Michel Schlatter
 */
public class RankingManager extends Manager {

    public RankingManager(Client c){
        super(c);
    }

    public void sendRanking(){
        List<Highscore> highscores = ServiceLocator.getServiceLocator().getHighscoreService().getHighscores();

        RankingContainer rc = new RankingContainer();
        rc.setHighScores(highscores);

        ServerUtils.sendObject(client, rc);
    }
}