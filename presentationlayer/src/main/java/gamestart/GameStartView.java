package gamestart;

import base.View;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *  author Manuel Wirz
 *  */

public class GameStartView extends View<GameStartModel> {

    protected Label lbl;

    public GameStartView(Stage stage, GameStartModel model) {

        super(stage, model);

    }

    public Scene create_GUI(){

        stage.setTitle("StartUp.Dominion Start");
        stage.setHeight(500);
        stage.setWidth(800);

        BorderPane root = new BorderPane();
        lbl = new Label();
        root.setCenter(lbl);

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("GameStart.css").toExternalForm());
        stage.setScene(scene);


        return scene;
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

