package Game;

import base.View;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameView extends View<GameModel> {

    BorderPane chatPane;
    TextArea messages;
    Button sendButton;


    public GameView(Stage stage, GameModel model){
        super(stage,model);
    }

    public Scene create_GUI(){





        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("lobby.css").toExternalForm());
        stage.setScene(scene);



        /**
         *  author Manuel Wirz
         *  */

        // ----- Chatview ------- TODO@Vanessa HBox deiner Gameview hinzufügen

        this.chatPane = new BorderPane();
        this.messages = new TextArea();
        this.sendButton  = new Button("send");

        // Add children to Borderpane
        chatPane.getChildren().add( messages );
        chatPane.getChildren().add( sendButton );

        // set the Aligment
        chatPane.setCenter( messages );
        chatPane.setBottom( sendButton );






        return scene;
    }

}
