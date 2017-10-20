package Game;

import base.View;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GameView extends View<GameModel> {


     protected TextField textFieldChat;
     protected Button btnChatSend;
     protected Button btnSendText;
     protected GridPane chatPane;
     protected HBox hbChat;
     protected VBox chatContent;

    public GameView(Stage stage, GameModel model){
        super(stage,model);
    }

    /**
     *  author Manuel Wirz
     *  */

    public Scene create_GUI(){


        // root settings

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1500, 1500);
        scene.getStylesheets().addAll(this.getClass().getResource("/Game/GameView.css").toExternalForm());
        stage.setScene(scene);
        stage.setFullScreen( false );
        stage.setTitle( "Dominion" );



        // root Layout
        root.setBottom(addChatGridPane());
        //TODO Venessa u can use for your GUI
       // root.setCenter();
       // root.setRight();
       // root.setTop();
       // root.setLeft();


        setTexts();
        return scene;
    }



    /**
     *  author Manuel Wirz
     *  */

    public GridPane addChatGridPane(){

        chatPane = new GridPane();
        chatContent = new VBox();

        //GridPane Layout

        chatPane.setAlignment(Pos.BOTTOM_RIGHT);
        chatPane.setHgap(10);
        chatPane.setVgap(10);
        chatPane.setPadding(new Insets(25, 25, 25, 25));


        // ----- Chatview -------


        this.btnChatSend  = new Button();
        this.btnChatSend.setPrefSize(150, 50);

        this.textFieldChat= new TextField();
        this.textFieldChat.setPromptText( "Enter Text" );
        this.textFieldChat.setPrefSize( 350,50 );


        this.btnSendText = new Button();
        this.btnSendText.setPrefSize(150, 50);


        // Create HBox +
        hbChat = new HBox(10);
        hbChat.setAlignment(Pos.CENTER);
        hbChat.getChildren().add(textFieldChat);
        hbChat.getChildren().add(btnChatSend);
        hbChat.getChildren().add(btnSendText);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent( chatContent );
        scroll.setMaxSize( 600, 200);
        chatPane.add(scroll,1,0);
        chatPane.add(hbChat, 1, 3);


        return chatPane;
    }


    protected void setTexts() {

        this.btnChatSend.setText( translator.getString( "chat.send" ) );
        this.btnSendText.setText( translator.getString( "chat.nice!" ) );
    }


    private String getText(String key) {
        return translator.getString( key );
    }

    public void start() {
        stage.show();
        stage.setOnCloseRequest(evt -> {
            // prevent window from closing
            evt.consume();
        });
    }

    public void stop(){
        stage.hide();
    }

    /**
     * Author Michel Schlatter
     * @param msg
     * @param color
     */
    protected  void setChatMessage(String msg, Color color){
        Label lbl = new Label();
        lbl.setText(msg);
        lbl.setTextFill(color);
        this.chatContent.getChildren().add(lbl);
    }



}
