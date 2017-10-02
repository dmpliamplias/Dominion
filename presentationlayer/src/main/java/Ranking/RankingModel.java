package Ranking;

import base.Model;
import com.weddingcrashers.model.Highscore;

import java.util.ArrayList;

public class RankingModel extends Model {
    ArrayList<Highscore> highscores;

    public ArrayList<Highscore> getHighscores() {
        return highscores;
    }

    public void setHighscores(ArrayList<Highscore> highscores) {
        this.highscores = highscores;
    }
}
