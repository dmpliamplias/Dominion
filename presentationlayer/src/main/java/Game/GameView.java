package Game;

import base.View;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class GameView extends View<GameModel> {

     BorderPane chatPane;
     TextArea textAreaChat;
     TextField textFieldChat;
     Button btnChatSend;


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

        // ----- Chatview ------- TODO@Vanessa BorderPane deiner Gameview hinzufügen

        this.chatPane = new BorderPane();
        this.textAreaChat = new TextArea();
        this.btnChatSend  = new Button("send");
        this.textFieldChat= new TextField();

        // Add children to Borderpane
        chatPane.getChildren().add( textAreaChat );
        chatPane.getChildren().add( btnChatSend );
        chatPane.getChildren().add( textFieldChat );


        // set the Aligment
        chatPane.setCenter( textAreaChat );
        chatPane.setBottom( textFieldChat );
        chatPane.setBottom( btnChatSend );

        // Layout





        return scene;
    }

}
