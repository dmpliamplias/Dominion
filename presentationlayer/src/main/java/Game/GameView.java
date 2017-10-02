package Game;

import base.View;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameView extends View<GameModel> {

    public GameView(Stage stage, GameModel model){
        super(stage,model);
    }

    public Scene create_GUI(){
        return null;
    }
}
