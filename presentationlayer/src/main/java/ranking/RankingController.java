package ranking;

import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.model.Highscore;
import com.weddingcrashers.model.Highscore_;
import com.weddingcrashers.servermodels.RankingContainer;
import com.weddingcrashers.servermodels.ViewStatus;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.List;


public class RankingController extends Controller<RankingModel, RankingView> {


    //** @author Michel Schlatter

    public RankingController(RankingModel model, RankingView view){
        super(model,view);
        serverConnectionService.setRankingController(this);
        initialize();
    }

    public void initialize() {
        try {
            serverConnectionService.updateViewStatus(ViewStatus.Ranking); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }

        // get Ranking from Server
        RankingContainer rc = new RankingContainer();
        try {
            serverConnectionService.sendObject(rc);
        } catch (IOException e) {
            this.view.alert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    ///** @author Murat Kelleci
    //
    public void handleServerAnswer(List<Highscore> highscoreList){
        // TODO: 02.10.2017 Murat => Display Highscores.

        model.setHighscores(highscoreList);
        view.bindModeltoView();
    }


    }
