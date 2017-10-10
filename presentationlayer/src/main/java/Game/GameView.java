package Game;

import base.View;
import com.weddingcrashers.service.ServiceLocator;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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
        Scene scene = new Scene(root, 500, 500);
        //scene.getStylesheets().add(getClass().getResource("lobby.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle( "Dominion" );


        chatPane.setAlignment(Pos.CENTER);
        chatPane.setHgap(10);
        chatPane.setVgap(10);



        /**
         *  author Manuel Wirz
         *  */

        // ----- Chatview -------
        //
        // TODO@Vanessa BorderPane deiner Gameview hinzufügen

        this.textAreaChat = new TextArea();
        this.btnChatSend  = new Button("Send");
        this.btnChatSend.setPrefSize(150, 30);
        this.textFieldChat= new TextField();
        this.btnSendText = new Button("Send Good Play");

        // Layout Grid Pane

        // 4 columns
        for (int i = 0; i<5;i++){
            ColumnConstraints column = new ColumnConstraints(150);
             chatPane.getColumnConstraints().add(column);
        }

        // Creating 4 rows
        for (int i = 0; i<5;i++){
            RowConstraints row = new RowConstraints(30);
            chatPane.getRowConstraints().add(row);
        }

        // Asign column and row to FX-Layouts
        chatPane.setConstraints(textAreaChat, 1, 1);
        chatPane.setConstraints(textFieldChat, 1, 2);
        chatPane.setConstraints(btnChatSend, 1, 3);
        chatPane.setConstraints( btnSendText, 1,4 );

        // Add children to GridPane
        chatPane.getChildren().add( textAreaChat );
        chatPane.getChildren().add( btnChatSend );
        chatPane.getChildren().add( textFieldChat );
        chatPane.getChildren().add( btnSendText );


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
