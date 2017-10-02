package com.weddingcrashers.servermodels;

import com.weddingcrashers.model.Highscore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RankingContainer extends  Container implements Serializable {
    List<Highscore> highScores;

    public RankingContainer(){
        super(Methods.Rankings);
    }

    public List<Highscore> getHighScores() {
        return highScores;
    }

    public void setHighScores(List<Highscore> highScores) {
        this.highScores = highScores;
    }

}
