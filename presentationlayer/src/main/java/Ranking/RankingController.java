package Ranking;

import app.PLServiceLocator;
import app.ServerConnectionService;
import base.Controller;
import com.weddingcrashers.servermodels.ViewStatus;

import java.io.IOException;

public class RankingController extends Controller<RankingModel, RankingView> {

    private final ServerConnectionService _connection;

    public RankingController(RankingModel model, RankingView view){
        super(model,view);
        _connection = PLServiceLocator.getPLServiceLocator().getServerConnectionService();

        initialize();
    }

    public void initialize() {
        try {
            _connection.updateViewStatus(ViewStatus.Ranking); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }
    }
}
