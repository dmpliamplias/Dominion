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


        //Scene and stage settings
        Scene scene = new Scene(root, 1500, 1000);
        scene.getStylesheets().addAll(this.getClass().getResource("/lobby/LobbyView.css").toExternalForm());
        stage.setScene(scene);


        // root Layout
        root.setBottom(addGridPane());
        root.setCenter(addVBox());
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
        vbox.setAlignment( Pos.CENTER );

        vbox.getChildren().addAll( lvPlayers );

        return  vbox;
    }


    public VBox addVBox(){

        VBox vbox = new VBox(  );
        vbox.setAlignment( Pos.CENTER );
        btnStart = new Button("Start Game");
        vbox.getChildren().addAll( btnStart );

        return  vbox;
    }

    public GridPane addGridPane() {

        GridPane gridPane = new GridPane();
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
        scroll.setMaxSize( 600, 200 );
        gridPane.add(scroll, 1, 0);
        gridPane.add( hBox, 1,3 );

        return gridPane;
}




    protected void setTexts() {

        this.stage.setTitle( getText( "LobbyView_Title" ) );
        this.btnChatSend.setText( getText( "send" ) );
    }

    private String getText(String key){
        return translator.getString(key);
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

