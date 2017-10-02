package ranking;

import base.View;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RankingView extends View<RankingModel> {

    public RankingView(Stage stage, RankingModel model){
        super(stage,model);
    }

    @Override
    protected Scene create_GUI() {
        return null;
    }
}
