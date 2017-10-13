package lobby;

import app.PLServiceLocator;
import base.View;
import com.weddingcrashers.service.ServiceLocator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *  author Michel Schlatter
 *  */

public class LobbyView extends View<LobbyModel> {

    protected Label lbl;
    protected ListView lvPlayers;
    protected Button btnStart;
    protected Button btnGameView;

    public LobbyView(Stage stage, LobbyModel model) {
        super(stage, model);
    }

    public Scene create_GUI(){

        stage.setTitle(ServiceLocator.getServiceLocator().getTranslator().getString("LobbyView_Title"));
        stage.setHeight(500);
        stage.setWidth(800);

        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();
        root.setCenter( gridPane );
        lbl = new Label();
        btnGameView = new Button("GameView");
        lvPlayers = new ListView();
        lvPlayers.setDisable(true);
        gridPane.add( btnGameView,1,1 );
        root.getChildren().addAll(lvPlayers, lbl);

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("lobby.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle( "Game");

        btnStart = new Button("Game View");
        return scene;
    }

    @Override
    protected void setTexts() {

    }


    protected void refresh(){
        lbl.setText("Sound is on: "+ model.getSettings().isSoundOn());
        //...conitinue...
    }

    public void start() {
        stage.show();
    }

    public void stop(){
        stage.hide();
    }

}

