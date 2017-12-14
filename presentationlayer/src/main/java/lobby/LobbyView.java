package lobby;

import base.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static util.StyleSheetPath.LOBBY;

/**
 *  author Michel Schlatter + Manuel Wirz
 *  */

public class LobbyView extends View<LobbyModel> {

    protected ListView<String> lvPlayers;
    protected Button btnStart;
    protected TextField textFieldChat;
    protected Button btnChatSend;
    protected VBox chatContent;
    protected Button btnLogout;
    protected TextField lblPlayer;
    protected TextField txtChat;
    protected Text textFieldGameSettings;
    protected TextField textFieldRound;
    protected ChoiceBox choiceBox;
    protected Tooltip tp;
    protected Tooltip tpOption2;
    protected TextField txtOption1;
    protected TextField txtOption2;
    protected TextField txtOption2Statement;
    protected Button btnRanking;
    protected CheckBox cbFinishPointCards;
    protected Button btnHelp;
    protected HBox hBoxOption2;
    protected HBox hBoxOption1;
    protected VBox vBoxGameSettings;
    protected TextField txtWait;
    protected VBox vBoxAddMenu;
    protected VBox hBoxOptionClient;
    protected BorderPane root;


    protected ObservableList<String> observablePlayerList = FXCollections.observableArrayList();


    /**
     *  author Manuel Wirz
     *  */

    public LobbyView(Stage stage, LobbyModel model) {
        super(stage, model);
    }

    public Scene create_GUI(){

        // root settings
        this.root = new BorderPane();



        //Scene and stage settings
        Scene scene = new Scene(root, 1150, 600);
        setStylesheet(scene, LOBBY);
        stage.setScene(scene);
        stage.setResizable( false );


        // root Layout

        root.setCenter(addVBoxGameSettings());
        root.setBottom(addGridPane());
        root.setRight(addClientList());
        root.setTop( addMenu());
        root.setLeft(addGameSettings());

        return scene;
    }

    public VBox addGameSettings(){

        VBox vBox = new VBox();
        return vBox;
    }


    // Method for title and menubar returns a VBox

    public VBox addMenu(){

        this.vBoxAddMenu = new VBox(  );
        vBoxAddMenu.setAlignment( Pos.TOP_CENTER );



        this.textFieldGameSettings = new Text(  );
        this.textFieldGameSettings.getStyleClass().add( "title" );


        vBoxAddMenu.getChildren().addAll(menuBar, textFieldGameSettings );

        menuBar.getMenuSettings().getItems().remove(menuBar.getMenuSound());

        return vBoxAddMenu;
    }

    // Method for client list return a VBox

    public VBox addClientList(){

        VBox vbox = new VBox();
        vbox.setSpacing( 15 );
        this.lblPlayer = new TextField(  );
        this.lblPlayer.setEditable( false );
        this.lblPlayer.setMaxWidth( 250 );
        lvPlayers = new ListView<String>(observablePlayerList);
        lvPlayers.setMouseTransparent( true );
        lvPlayers.setFocusTraversable( false );
        lvPlayers.setMaxSize( 250,155);
        vbox.setAlignment( Pos.CENTER );

        vbox.getChildren().addAll( lblPlayer,lvPlayers );

        return  vbox;
    }


    // Method for game settings return a VBox

    public VBox addVBoxGameSettings(){

       this.vBoxGameSettings = new VBox(  );
        vBoxGameSettings.setSpacing( 30 );
        Insets insets = new Insets( 10 ) ;
        vBoxGameSettings.setPadding( insets );
        vBoxGameSettings.setAlignment( Pos.CENTER );


        // Option 1

        this.hBoxOption1 = new HBox(  );
        hBoxOption1.setPrefWidth( 500 );
        hBoxOption1.setSpacing( 70 );
        hBoxOption1.setAlignment( Pos.CENTER );


        this.txtOption1 = new TextField(  );
        this.txtOption1.setEditable( false );
        this.txtOption1.setPrefWidth( 500 );
        this.txtOption1.setPrefHeight( 50 );

        //TODO Manuel 2 only for testing
        this.choiceBox = new ChoiceBox( FXCollections.observableArrayList(
            "",2,10,11,12,13,14,15,16,17,18,19,20) );
        this.choiceBox.setPrefSize( 20,40 );


        hBoxOption1.getChildren().addAll( txtOption1,/*textFieldRound,*/choiceBox );

        // Option 2

        this.hBoxOption2 = new HBox(  );
        hBoxOption2.setPrefWidth( 500 );
        hBoxOption2.setSpacing( 70 );
        hBoxOption2.setAlignment( Pos.CENTER );

        this.txtOption2 = new TextField(  );
        this.txtOption2.setEditable( false );
        this.txtOption2.setPrefWidth( 500 );
        this.txtOption2.setPrefHeight( 50 );
        this.tpOption2 = new Tooltip(  );
        this.cbFinishPointCards = new CheckBox(  );
        this.cbFinishPointCards.setPrefSize( 50,70 );



        hBoxOption2.getChildren().addAll( txtOption2/*, txtOption2Statement*/, cbFinishPointCards);



        // Buttons for GameLobby

        HBox hBoxButtons = new HBox(  );
        hBoxButtons.setPrefWidth( 500 );
        hBoxButtons.setAlignment( Pos.CENTER );
        hBoxButtons.setSpacing(20);
        btnRanking = new Button(  );
        btnRanking.setPrefWidth( 190 );
        btnStart = new Button();
        btnStart.setPrefWidth( 190 );
        this.tp = new Tooltip(  );
        btnStart.setTooltip(tp);
        btnLogout = new Button( );
        btnLogout.setPrefWidth( 190 );
        btnHelp = new Button(  );
        btnHelp.setPrefWidth( 190 );
        hBoxButtons.getChildren().addAll( btnStart, btnRanking, btnHelp /*btnLogout */ );


        vBoxGameSettings.getChildren().addAll(hBoxOption1,hBoxOption2, hBoxButtons );

        return  vBoxGameSettings;
    }

    // Method for Chat returns a GridPane

    public GridPane addGridPane() {

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize( 100, 300 );
        chatContent = new VBox();


         // Layout GridPane

        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(3, 3, 3, 3));


        // Chatview

        this.btnChatSend  = new Button();
        this.btnChatSend.setPrefSize(250, 50);

        this.textFieldChat= new TextField();
        this.textFieldChat.setPromptText( "Enter Text" );
        this.textFieldChat.setPrefSize( 600,50 );

        this.txtChat = new TextField(  );
        this.txtChat.setEditable( false );
        this.txtChat.setMaxWidth( 900 );

        HBox hBox = new HBox(10);
        hBox.setAlignment( Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(textFieldChat);
        hBox.getChildren().add(btnChatSend);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent( chatContent );
        scroll.setPrefSize( 860,200 );
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER );
        scroll.vvalueProperty().bind( chatContent.heightProperty() );
        scroll.setFitToHeight( true );
        scroll.setFitToWidth( true );
        gridPane.add(scroll, 0, 1);
        gridPane.add( hBox, 0,2 );
        gridPane.add( txtChat, 0,0 );

        return gridPane;
}


    // Method for multilanguage

    protected void setTexts() {

        this.txtChat.setText( getText( "lobbyview.textChat" ) );
        this.stage.setTitle( getText( "lobbyview.title" ) );
        this.btnChatSend.setText( getText( "chat.send" ) );
        this.btnStart.setText( getText("lobbyview.startgame" ));
        this.btnLogout.setText( getText( "lobbyview.logout" ) );
        this.lblPlayer.setText( getText( "lobbyview.lblPlayer" ) );
        this.textFieldGameSettings.setText( getText( "lobbyview.GameSettings" ) );
        this.tp.setText( getText( "lobbyview.tooltip" ) );
        this.txtOption1.setText( getText( "lobbyview.txtOption2" ) );
        this.txtOption2.setText( getText( "lobbyview.txtOption1" ) );
        this.btnRanking.setText( getText( "lobbyview.ranking" ) );
        this.tpOption2.setText( getText( "lobbyview.tpOption2" ) );
        this.btnHelp.setText( getText( "lobbyview.btnHelp" ) );

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

    /**
     *  author Manuel Wirz
     *  */

    // Method for client view who is not hoster

    public void  setWaitText(){

        this.hBoxOptionClient = new VBox(  );
        Insets insets = new Insets( 15 );
        this.hBoxOptionClient.setPadding(insets );
        this.hBoxOptionClient.setAlignment( Pos.CENTER );
        this.hBoxOptionClient.setPrefWidth( 600 );
        this.hBoxOptionClient.setPrefHeight( 350 );
        Image wait = new Image(getClass().getResourceAsStream("wait.png"));
        ImageView imgWait = new ImageView(wait);
        HBox hBoxButtons = new HBox(  );
        hBoxButtons.setPadding( insets );
        hBoxButtons.setPrefWidth( 450 );
        hBoxButtons.setAlignment( Pos.CENTER );
        hBoxButtons.setSpacing(20);
        btnRanking = new Button(  );
        btnRanking.setPrefWidth( 190 );
        btnHelp = new Button(  );
        btnHelp.setPrefWidth( 190 );


        hBoxButtons.getChildren().addAll( btnRanking, btnHelp );

        this.hBoxOptionClient.getChildren().addAll( imgWait, hBoxButtons );
        this.root.setCenter(hBoxOptionClient);
    }

    // Method for stageDialog

    // Getters and Setters for LobbyView
    public ImageView getImgViewDeFlag() {
        return menuBar.getImgViewDeFlag();
    }

    public VBox gethBoxOptionClient() {
        return hBoxOptionClient;
    }

    public HBox gethBoxOption2() {
        return hBoxOption2;
    }

    public HBox gethBoxOption1() {
        return hBoxOption1;
    }

    public VBox getvBoxGameSettings() {
        return vBoxGameSettings;
    }

    public VBox getvBoxAddMenu() {
        return vBoxAddMenu;
    }

    public ListView<String> getLvPlayers() {
        return lvPlayers;
    }

    public Button getBtnStart() {
        return btnStart;
    }

    public TextField getTextFieldChat() {
        return textFieldChat;
    }

    public Button getBtnChatSend() {
        return btnChatSend;
    }

    public VBox getChatContent() {
        return chatContent;
    }

    public Button getBtnLogout() {
        return btnLogout;
    }

    public TextField getLblPlayer() {
        return lblPlayer;
    }

    public TextField getTxtChat() {
        return txtChat;
    }

    public Text getTextFieldGameSettings() {
        return textFieldGameSettings;
    }

    public TextField getTextFieldRound() {
        return textFieldRound;
    }

    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }

    public Tooltip getTp() {
        return tp;
    }

    public Tooltip getTpOption2() {
        return tpOption2;
    }

    public TextField getTxtOption1() {
        return txtOption1;
    }

    public TextField getTxtOption2() {
        return txtOption2;
    }

    public TextField getTxtOption2Statement() {
        return txtOption2Statement;
    }

    public Button getBtnRanking() {
        return btnRanking;
    }

    public CheckBox getCbFinishPointCards() {
        return cbFinishPointCards;
    }

    public Button getBtnHelp() {
        return btnHelp;
    }

    public TextField getTxtWait() {
        return txtWait;
    }

    public ObservableList<String> getObservablePlayerList() {
        return observablePlayerList;
    }

    public BorderPane getRoot() {
        return root;
    }

}

