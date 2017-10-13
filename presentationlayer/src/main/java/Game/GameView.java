package Game;

import base.View;
import com.weddingcrashers.service.ServiceLocator;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.*;

public class GameView extends View<GameModel> {


    /**
     *  author Manuel Wirz
     *  */

     protected TextArea textAreaChat;
     protected TextField textFieldChat;
     protected Button btnChatSend;
     protected Button btnSendText;




    public GameView(Stage stage, GameModel model){
        super(stage,model);
    }

    public Scene create_GUI(){

        BorderPane root = new BorderPane();
        GridPane chatPane = new GridPane();
        root.setRight( chatPane );
        Scene scene = new Scene(root, 1500, 1500);
        scene.getStylesheets().addAll(this.getClass().getResource("/Game/GameView.css").toExternalForm());
        stage.setScene(scene);
        stage.setFullScreen( false );
        stage.setTitle( "Dominion" );
        stage.setWidth( 1000 );
        stage.setHeight( 1000 );


        chatPane.setAlignment(Pos.BOTTOM_RIGHT);
        chatPane.setHgap(10);
        chatPane.setVgap(10);
        chatPane.setPadding(new Insets(25, 25, 25, 25));



        /**
         *  author Manuel Wirz
         *  */

        // ----- Chatview -------
        //
        // TODO@Vanessa BorderPane deiner Gameview hinzufügen

        this.textAreaChat = new TextArea();
        this.textAreaChat.setEditable( false );
        this.textAreaChat.setPromptText( "Chat Room" );


        this.btnChatSend  = new Button();
        this.btnChatSend.setPrefSize(150, 30);

        this.textFieldChat= new TextField();
        this.textFieldChat.setPromptText( "Enter Text" );
        this.textFieldChat.setPrefSize( 350,50 );


        this.btnSendText = new Button();
        this.btnSendText.setPrefSize(150, 30);


        // Create HBox +
        HBox hbChat = new HBox(10);
        hbChat.setAlignment(Pos.CENTER);
        hbChat.getChildren().add(textFieldChat);
        hbChat.getChildren().add(btnChatSend);
        hbChat.getChildren().add(btnSendText);
        chatPane.add(hbChat, 1, 3);
        chatPane.add( textAreaChat, 1,2 );

        setTexts();


        return scene;
    }

    protected void setTexts() {

        this.btnChatSend.setText( translator.getString( "send" ) );
        this.btnSendText.setText( translator.getString( "nice!" ) );
    }


    private String getText(String key) {
        return translator.getString( key );
    }

    public void start() {
        stage.show();
    }

    public void stop(){
        stage.hide();
    }

}
