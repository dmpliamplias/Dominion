package connection;

import base.View;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConnectionView extends View<ConnectionModel> {

    public ConnectionView(Stage stage, ConnectionModel model){
        super(stage, model);
    }

    public Scene create_GUI(){
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        // create view...



        return scene;
    }



}
