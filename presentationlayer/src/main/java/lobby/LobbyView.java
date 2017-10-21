package lobby;

import base.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *  author Michel Schlatter
 *  */

public class LobbyView extends View<LobbyModel> {

    protected ListView<String> lvPlayers;
    protected Button btnStart;
    protected Button btnGameView;
    protected TextField textFieldChat;
    protected Button btnChatSend;
    protected VBox chatContent;
    protected Button btnLogout;
    protected TextField lblPlayer;
    protected TextField txtChat;
    protected TextField textFieldGameSettings;
    protected TextField textFieldRound;
    protected ChoiceBox choiceBox;

    protected ObservableList<String> observablePlayerList = FXCollections.observableArrayList();


    /**
     *  author Manuel Wirz
     *  */

    public LobbyView(Stage stage, LobbyModel model) {
        super(stage, model);
    }

    public Scene create_GUI(){

        // root settings
        BorderPane root = new BorderPane();
        stage.setMaximized( true );



        //Scene and stage settings
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("/lobby/LobbyView.css").toExternalForm());
        stage.setScene(scene);


        // root Layout
        root.setBottom(addGridPane());
        root.setCenter(addMenu());
        root.setRight(addClientList());
        root.setTop( addVBoxGameSettings());
        root.setLeft(addGameSettings());

        // For multi language
        setTexts();

        return scene;
    }

    public VBox addGameSettings(){

        VBox vBox = new VBox();


        return vBox;
    }

    public HBox addMenu(){

        HBox hBox = new HBox(  );
        hBox.setAlignment( Pos.CENTER );
        // Test button
        btnGameView = new Button("GameView");

        hBox.getChildren().addAll( btnGameView );


        return hBox;
    }

    public VBox addClientList(){

        VBox vbox = new VBox();
        vbox.setSpacing( 15 );
        this.lblPlayer = new TextField(  );
        this.lblPlayer.setEditable( false );
        this.lblPlayer.setMaxWidth( 250 );
        lvPlayers = new ListView<String>(observablePlayerList);
        lvPlayers.setMaxSize( 250,280);
        vbox.setAlignment( Pos.CENTER );

        vbox.getChildren().addAll( lblPlayer,lvPlayers );

        return  vbox;
    }


    public VBox addVBoxGameSettings(){

        VBox vBox = new VBox(  );
        HBox hBox = new HBox(  );

        vBox.setSpacing( 50 );

        // GamesRules

        this.textFieldGameSettings = new TextField(  );
        this.textFieldGameSettings.setEditable( false );
        this.textFieldGameSettings.setMaxWidth( 300 );

        HBox hboxRounds = new HBox(  );
        hboxRounds.setSpacing( 50 );
        hboxRounds.setAlignment( Pos.CENTER_LEFT );


        this.textFieldRound = new TextField(  );
        this.textFieldRound.setEditable( false );
        this.textFieldRound.setMaxWidth( 300 );

        this.choiceBox = new ChoiceBox( FXCollections.observableArrayList(
            10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30
        ) );
        this.choiceBox.setMaxWidth( 300 );

        hboxRounds.getChildren().addAll( textFieldRound,choiceBox );


        vBox.getChildren().addAll( textFieldGameSettings, hboxRounds);


        // Buttons for GameLobby

        hBox.setAlignment( Pos.CENTER_LEFT );
        hBox.setSpacing(100);
        btnStart = new Button();
        btnStart.setTooltip(new Tooltip("You can only start the game, if your are the server :)"));
        btnLogout = new Button( );
        hBox.getChildren().addAll( btnStart, btnLogout );

        vBox.getChildren().addAll( hBox );
        vBox.setAlignment( Pos.CENTER_LEFT );

        return  vBox;
    }

    public GridPane addGridPane() {

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize( 100, 350 );
        chatContent = new VBox();


         // Layout GridPane
        gridPane.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(3, 3, 3, 3));


        // Chatview

        this.btnChatSend  = new Button();
        this.btnChatSend.setPrefSize(250, 50);

        this.textFieldChat= new TextField();
        this.textFieldChat.setPromptText( "Enter Text" );
        this.textFieldChat.setPrefSize( 450,50 );

        this.txtChat = new TextField(  );
        this.txtChat.setEditable( false );
        this.txtChat.setMaxWidth( 900 );

        HBox hBox = new HBox(10);
        hBox.setAlignment( Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(textFieldChat);
        hBox.getChildren().add(btnChatSend);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent( chatContent );
        scroll.setMaxSize( 900, 210 );
        scroll.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER );
        scroll.vvalueProperty().bind( chatContent.heightProperty() );
        gridPane.add(scroll, 0, 1);
        gridPane.add( hBox, 0,2 );
        gridPane.add( txtChat, 0,0 );

        return gridPane;
}




    protected void setTexts() {

        this.txtChat.setText( getText( "lobbyview.textChat" ) );
        this.stage.setTitle( getText( "lobbyview.title" ) );
        this.btnChatSend.setText( getText( "chat.send" ) );
        this.btnStart.setText( getText("lobbyview.startgame" ));
        this.btnLogout.setText( getText( "lobbyview.logout" ) );
        this.lblPlayer.setText( getText( "lobbyview.lblPlayer" ) );
        this.textFieldGameSettings.setText( getText( "lobbyview.GameSettings" ) );
        this.textFieldRound.setText( getText( "lobbyview.rounds" ) );
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

