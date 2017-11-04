package ranking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.weddingcrashers.model.User;
import javafx.scene.control.Alert;
import com.weddingcrashers.model.Highscore;
import com.weddingcrashers.servermodels.RankingContainer;
import com.weddingcrashers.servermodels.ViewStatus;
import base.Controller;
import javafx.stage.Stage;
import lobby.LobbyController;
import lobby.LobbyModel;
import lobby.LobbyView;
import javafx.event.ActionEvent;




public class RankingController extends Controller<RankingModel, RankingView> {


    //** @author Michel Schlatter


    public RankingController(RankingModel model, RankingView view) {
        super(model, view);
        serverConnectionService.setRankingController(this);
        initialize();
        getMockPersons();
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

        this.view.btnLobby.setOnAction((event) -> {
            this.goToLobbyView();
        });
    }


    ///** @author Murat Kelleci
    //
    public void handleServerAnswer(List<Highscore> highscoreList){
        model.setHighscores(highscoreList);
        view.bindModeltoView();
    }


    ///** @author Murat Kelleci
    //
    private void goToLobbyView() {
        LobbyModel model = new LobbyModel();
        Stage s = new Stage();
        LobbyView view = new LobbyView(s, model);
        new LobbyController(view, model);
        this.view.stop();
        view.start();
    }

    public List<Highscore> getMockPersons() {
        final ArrayList<Highscore> objects = new ArrayList<>();
        final User hans = new User().name("Hans").email("").password("");
        final User murat = new User().name("Murat").email("").password("");
        final User fritz = new User().name("Fritz").email("").password("");
        final User djoni = new User().name("Djoni").email("").password("");
        final User migi = new User().name("Migi").email("").password("");
        final User reto = new User().name("Reto").email("").password("");
        final User lola = new User().name("Lola").email("").password("");
        final Highscore highscoreHans = new Highscore();
        final Highscore highscoreFritz = new Highscore();
        final Highscore highscoreMigi = new Highscore();
        final Highscore highscoreDjoni = new Highscore();
        final Highscore highscoreReto = new Highscore();
        final Highscore highscoreLola = new Highscore();
        final Highscore highscoreMurat = new Highscore();
        highscoreHans.setPoints(1000);
        highscoreHans.setUser(hans);
        highscoreMurat.setPoints(2000);
        highscoreMurat.setUser(murat);
        highscoreFritz.setPoints(4000);
        highscoreFritz.setUser(fritz);
        highscoreDjoni.setPoints(6000);
        highscoreDjoni.setUser(djoni);
        highscoreMigi.setPoints(8000);
        highscoreMigi.setUser(migi);
        highscoreReto.setPoints(9000);
        highscoreReto.setUser(reto);
        highscoreLola.setPoints(9500);
        highscoreLola.setUser(lola);

        objects.add(highscoreHans);
        objects.add(highscoreMurat);
        objects.add(highscoreFritz);
        objects.add(highscoreDjoni);
        objects.add(highscoreMigi);
        objects.add(highscoreReto);
        objects.add(highscoreLola);

        return objects;
    }


    }
