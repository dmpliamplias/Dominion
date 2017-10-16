package Game;

import base.View;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

        stage.setWidth( 1000 );
        stage.setHeight( 1000 );
        BorderPane root = new BorderPane();
        chatPane = new GridPane();
        root.setRight( chatPane );
        Scene scene = new Scene(root, 1500, 1500);
        scene.getStylesheets().addAll(this.getClass().getResource("/Game/GameView.css").toExternalForm());
        stage.setScene(scene);
        stage.setFullScreen( false );
        stage.setTitle( "Dominion" );



        chatPane.setAlignment(Pos.BOTTOM_RIGHT);
        chatPane.setHgap(10);
        chatPane.setVgap(10);
        chatPane.setPadding(new Insets(25, 25, 25, 25));





        // ----- Chatview -------
        //
        // TODO@Vanessa BorderPane deiner Gameview hinzufügen


        this.btnChatSend  = new Button();
        this.btnChatSend.setPrefSize(150, 50);

        this.textFieldChat= new TextField();
        this.textFieldChat.setPromptText( "Enter Text" );
        this.textFieldChat.setPrefSize( 350,50 );


        this.btnSendText = new Button();
        this.btnSendText.setPrefSize(150, 50);

        String cssLayout =
                "-fx-border-color: red;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n";

        chatContent = new VBox();
        chatContent.setStyle(cssLayout);


        // Create HBox +
        hbChat = new HBox(10);
        hbChat.setAlignment(Pos.CENTER);
        hbChat.getChildren().add(textFieldChat);
        hbChat.getChildren().add(btnChatSend);
        hbChat.getChildren().add(btnSendText);
        chatPane.add(chatContent,1,0);
        chatPane.add(hbChat, 1, 3);
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
