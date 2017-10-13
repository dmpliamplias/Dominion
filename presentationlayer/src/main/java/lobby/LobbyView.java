package lobby;

import app.PLServiceLocator;
import base.View;
import com.weddingcrashers.service.ServiceLocator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *  author Michel Schlatter
 *  */

public class LobbyView extends View<LobbyModel> {

    protected Label lbl;
    protected ListView lvPlayers;
    protected Button btnStart;
    protected Button btnGameView;
    protected TextArea txtAreaChat;
    protected TextField textFieldChat;
    protected Button btnChatSend;

    public LobbyView(Stage stage, LobbyModel model) {
        super(stage, model);
    }

    public Scene create_GUI(){

        stage.setHeight(900);
        stage.setWidth(1500);

        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();
        root.setCenter( gridPane );
        lbl = new Label();
        btnGameView = new Button("GameView");
        lvPlayers = new ListView();
        lvPlayers.setDisable(true);
        gridPane.add( btnGameView,1,1 );
        root.getChildren().addAll(lvPlayers, lbl);

        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("/lobby/LobbyView.css").toExternalForm());
        stage.setScene(scene);


        gridPane.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));



        /**
         *  author Manuel Wirz
         *  */

        // Chatview

        this.txtAreaChat = new TextArea();
        this.txtAreaChat.setEditable( false );
        this.txtAreaChat.setPromptText( "Chat Room" );


        this.btnChatSend  = new Button();
        this.btnChatSend.setPrefSize(250, 50);

        this.textFieldChat= new TextField();
        this.textFieldChat.setPromptText( "Enter Text" );
        this.textFieldChat.setPrefSize( 450,50 );

        // create HBox for chat

        HBox hBox = new HBox(10);
        hBox.setAlignment( Pos.CENTER);
        hBox.getChildren().add(textFieldChat);
        hBox.getChildren().add(btnChatSend);
        gridPane.add(hBox, 1, 3);
        gridPane.add( txtAreaChat, 1,2 );

        setTexts();

        btnStart = new Button("Game View");


        return scene;
    }


    protected void setTexts() {

       this.stage.setTitle(getText("LobbyView_Title"));
       this.btnChatSend.setText( translator.getString( "send" ) );

    }

    private String getText(String key){
        return translator.getString(key);
    }


    protected void refresh(){
        lbl.setText("Sound is on: "+ model.getSettings().isSoundOn());
        //...conitinue...
    }

    public void start() {
        stage.show();
    }

    public void stop(){
        stage.hide();
    }

}

