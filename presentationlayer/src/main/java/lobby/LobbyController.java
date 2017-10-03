package lobby;

import Game.GameController;
import Game.GameModel;
import Game.GameView;
import app.PLServiceLocator;
import base.Controller;
import com.weddingcrashers.model.User;
import com.weddingcrashers.servermodels.GameContainer;
import com.weddingcrashers.servermodels.LobbyContainer;
import com.weddingcrashers.servermodels.Methods;
import com.weddingcrashers.servermodels.ViewStatus;
import app.ServerConnectionService;
import com.weddingcrashers.service.ServiceLocator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *  author Michel Schlatter
 *  */

public class LobbyController extends Controller <LobbyModel, LobbyView> {

    private final ServerConnectionService _serverConnection;
    private final User _user;
    private ObservableList<String> list = FXCollections.observableArrayList();
    private HashMap<Integer, User> players;

    public LobbyController(LobbyView view, LobbyModel model, User user){
        super(model,view);
        _user = user; // I think you need id here for set ranking when game is over...
        _serverConnection = PLServiceLocator.getPLServiceLocator().getServerConnectionService();
        PLServiceLocator.getPLServiceLocator().getServerConnectionService().setLobbyController(this);

        initialize();
    }

    public void initialize() {
        try {
            _serverConnection.updateViewStatus(ViewStatus.Lobby); // set ViewStatus for Server
        } catch (IOException e) {
            this.view.alert(e.getMessage());
        }

        view.btnStart.setDisable(!PLServiceLocator.getPLServiceLocator().getServerConnectionService().isHoster()); // only hoster can start game.
        view.btnStart.setOnAction((event) -> {
           startGame();
        });
    }

    public void handleServerAnswer_newPlayer(LobbyContainer lc){
        Platform.runLater(() -> {
            String res = "";
            players = lc.getPlayers();
            Iterator iter = lc.getPlayers().entrySet().iterator();

            while(iter.hasNext()){
                Map.Entry<Integer, User> item = (Map.Entry)iter.next();
                list.add(item.getKey() + ": " + item.getValue().getUserName());
            }

            view.lvPlayers.setItems(list);
            view.lvPlayers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        });
    }

    public void handleServerAnswer_startGame(boolean myTurn) {
        GameModel gameModel = new GameModel();
        GameView gameView = new GameView(this.view.getStage(), gameModel);
        GameController gameController = new GameController(gameModel, gameView, myTurn);

        this.view.stop();
        gameView.start();
    }

        private void startGame(){
            ObservableList<String> names = view.lvPlayers.getSelectionModel().getSelectedItems();
            if(names.size() < 2){
                // TODO: 02.10.2017 vanessa: text erstellen => Nicht genügend Spieler selektiert um zu spielen.
                this.view.alert(ServiceLocator.getServiceLocator().getTranslator().getString("LobbyView_NotEnoughPlayers"));
            }else {
                ArrayList<Integer> clientIds = new ArrayList<Integer>();

                for (String name : names) {
                    String[] array = name.split(":");
                    int clientId = Integer.parseInt(array[0]);
                    clientIds.add(clientId);
                }

                LobbyContainer lc = new LobbyContainer(Methods.StartGame);
                lc.setClientIds_startGame(clientIds);

                try {
                    _serverConnection.sendObject(lc);
                } catch (IOException e) {
                    view.alert(e.getMessage());
                }
            }
    }


}
