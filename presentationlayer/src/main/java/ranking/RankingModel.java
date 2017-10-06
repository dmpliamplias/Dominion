package ranking;

import base.Model;
import com.weddingcrashers.model.Highscore;

import java.util.List;

//** @author Michel Schlatter

public class RankingModel extends Model {
    List<Highscore> highscores;

    public List<Highscore> getHighscores() {
        return highscores;
    }

    public void setHighscores(List<Highscore> highscores) {
        this.highscores = highscores;
    }
}
