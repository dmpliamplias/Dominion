package Game;

import base.View;
import com.weddingcrashers.service.ServiceLocator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.*;

public class GameView extends View<GameModel> {


    /**
     *  author Manuel Wirz
     *  */

     BorderPane chatPane;
     protected TextArea textAreaChat;
     protected TextField textFieldChat;
     protected Button btnChatSend;
     protected Button btnSendText;




    public GameView(Stage stage, GameModel model){
        super(stage,model);
    }

    public Scene create_GUI(){





        BorderPane root = new BorderPane();
        root.setRight( chatPane );
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("lobby.css").toExternalForm());
        stage.setScene(scene);



        /**
         *  author Manuel Wirz
         *  */

        // ----- Chatview ------- TODO@Vanessa BorderPane deiner Gameview hinzufügen

        this.chatPane = new BorderPane();
        this.textAreaChat = new TextArea();
        // Button soll send Message heissen
        this.btnChatSend  = new Button(translator.getString( "GameViewBtnSend"));
        this.textFieldChat= new TextField();
        // Button soll Good Play heissen
        this.btnSendText = new Button( translator.getString( "GameViewBtnSendText" ));

        // set the Aligment
        this.chatPane.setCenter( textAreaChat );
        this.chatPane.setBottom( textFieldChat );
        this.chatPane.setBottom( btnChatSend );

        // Add children to Borderpane
        this.chatPane.getChildren().add( textAreaChat );
        this.chatPane.getChildren().add( btnChatSend );
        this.chatPane.getChildren().add( textFieldChat );
        this.chatPane.getChildren().add( btnSendText );


        // Layout button as a cycle
        double circleSize =1.5;
        this.btnSendText.setShape( new Circle( circleSize ) );
        this.btnSendText.setMaxSize( 2*circleSize, 2*circleSize );
        this.btnSendText.setMinSize( 2*circleSize, 2*circleSize );




        return scene;
    }

    public void start() {
        stage.show();
    }

    public void stop(){
        stage.hide();
    }

}
