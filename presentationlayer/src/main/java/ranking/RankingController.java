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
        view.bindModelToView();
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


}
