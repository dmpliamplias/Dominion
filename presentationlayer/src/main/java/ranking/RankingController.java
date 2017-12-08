package ranking;

import base.Controller;
import com.weddingcrashers.model.Highscore;
import com.weddingcrashers.servermodels.RankingContainer;
import com.weddingcrashers.servermodels.ViewStatus;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lobby.LobbyController;
import lobby.LobbyModel;
import lobby.LobbyView;

import java.io.IOException;
import java.util.Collections;
import java.util.List;




public class RankingController extends Controller<RankingModel, RankingView> {


    //** @author Michel Schlatter


    public RankingController(RankingModel model, RankingView view) {
        super(model, view);
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

        this.view.btnLobby.setOnAction((event) -> {
            this.goToLobbyView();
        });
    }

    ///** @author Murat Kelleci

    public void handleServerAnswer(List<Highscore> highscoreList){
        Platform.runLater(() ->{
            Collections.sort(highscoreList);
            model.setHighscores(highscoreList);
            view.bindModelToView();
        });
    }

    ///** @author Murat Kelleci

    private void goToLobbyView() {
        LobbyModel model = new LobbyModel();
        Stage s = new Stage();
        LobbyView view = new LobbyView(s, model);
        new LobbyController(view, model);
        this.view.stop();
        plServiceLocator.audioClip.stop();
        if(view != null && view.menuBar.getMenuItemMusicMute() != null) {
            if (view.menuBar.getMenuItemMusicMute().isSelected()) {
                plServiceLocator.soundIsOn = false;
            }
        }
        view.start();
    }


}
