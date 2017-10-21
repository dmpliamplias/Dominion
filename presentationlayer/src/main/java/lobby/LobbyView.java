package lobby;

import base.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

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
        root.setCenter(addHBoxCenter());
        root.setRight(addClientList());
        root.setTop(addMenu());
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
        // Test button
        btnGameView = new Button("GameView");

        hBox.getChildren().addAll( btnGameView );


        return hBox;
    }

    public VBox addClientList(){

        VBox vbox = new VBox(  );
        lvPlayers = new ListView<String>(observablePlayerList);
        lvPlayers.setMaxSize( 250,185 );
        vbox.setAlignment( Pos.CENTER );

        vbox.getChildren().addAll( lvPlayers );

        return  vbox;
    }


    public HBox addHBoxCenter(){

        HBox hbox = new HBox(  );
        hbox.setAlignment( Pos.BOTTOM_CENTER );
        hbox.setPadding( new Insets( 20, 20, 20, 20 ) );
        hbox.setSpacing(40);
        btnStart = new Button();
        btnLogout = new Button( );
        hbox.getChildren().addAll( btnStart, btnLogout );

        return  hbox;
    }

    public GridPane addGridPane() {

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize( 100, 400 );
        chatContent = new VBox();


         // Layout GridPane
        gridPane.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));


        // Chatview

        this.btnChatSend  = new Button();
        this.btnChatSend.setPrefSize(250, 50);

        this.textFieldChat= new TextField();
        this.textFieldChat.setPromptText( "Enter Text" );
        this.textFieldChat.setPrefSize( 450,50 );

        HBox hBox = new HBox(10);
        hBox.setAlignment( Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(textFieldChat);
        hBox.getChildren().add(btnChatSend);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent( chatContent );
        scroll.setMaxSize( 600, 250 );
        scroll.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER );
        scroll.vvalueProperty().bind( chatContent.heightProperty() );
        gridPane.add(scroll, 1, 0);
        gridPane.add( hBox, 1,3 );

        return gridPane;
}




    protected void setTexts() {

        this.stage.setTitle( getText( "lobbyview.title" ) );
        this.btnChatSend.setText( getText( "chat.send" ) );
        this.btnStart.setText( getText("lobbyview.startgame" ));
        this.btnLogout.setText( getText( "lobbyview.logout" ) );
    }

    private String getText(String key){
        return translator.getString(key);
    }


    public void start() {
        stage.show();
        this.stage.setOnCloseRequest(evt -> {
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

