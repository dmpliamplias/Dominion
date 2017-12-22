package lobby;

import base.View;
import com.weddingcrashers.service.Language;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
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
 *  author Manuel Wirz
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
    protected Image imgWaitCH;
    protected Image imgWaitENG;
    protected Image imgWaitDE;
    protected ImageView clientImg;


    protected ObservableList<String> observablePlayerList = FXCollections.observableArrayList();


    /**
     *  author Manuel Wirz
     *  */

    public LobbyView(Stage stage, LobbyModel model) {
        super(stage, model);
    }

    /**
     *  author Manuel Wirz
     *  Method for creating stage, scene and set the Root
     *  */

    public Scene create_GUI(){

        /**
         *  Root settings and layout
         *  */

        this.root = new BorderPane();
        root.setCenter(addVBoxGameSettings());
        root.setBottom(addGridPane());
        root.setRight(addClientList());
        root.setTop( addMenu());
        root.setLeft(addGameSettings());

        /**
         *  Scene and stage settings
         *  create clientImgView
         *  */

        Scene scene = new Scene(root, 1150, 600);
        setStylesheet(scene, LOBBY);
        stage.setScene(scene);

        this.clientImg = new ImageView(  );


        return scene;
    }

    public VBox addGameSettings(){

        VBox vBox = new VBox();
        return vBox;
    }

    /**
     *  author Manuel Wirz
     *  Method for creating menubar and title
     *  Returns a VBox
     *  */

    public VBox addMenu(){

        this.vBoxAddMenu = new VBox(  );
        this.vBoxAddMenu.setAlignment( Pos.TOP_CENTER );
        this.textFieldGameSettings = new Text(  );
        this.textFieldGameSettings.getStyleClass().add( "title" );

        vBoxAddMenu.getChildren().addAll(menuBar, textFieldGameSettings );

        menuBar.getMenuSettings().getItems().remove(menuBar.getMenuSound());

        return vBoxAddMenu;
    }

    /**
     *  author Manuel Wirz
     *  Method for showing PlayerList
     *  Returns a VBox
     *  */

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


    /**
     *  author Manuel Wirz
     *  Method for showing game settings / end options
     *  Returns a VBox
     *  */

    public VBox addVBoxGameSettings(){

       this.vBoxGameSettings = new VBox(  );
        vBoxGameSettings.setSpacing( 30 );
        Insets insets = new Insets( 10 ) ;
        vBoxGameSettings.setPadding( insets );
        vBoxGameSettings.setAlignment( Pos.CENTER );


        /**
         *  Creating option 1
         *  */

        this.hBoxOption1 = new HBox(  );
        hBoxOption1.setPrefWidth( 500 );
        hBoxOption1.setSpacing( 70 );
        hBoxOption1.setAlignment( Pos.CENTER );


        this.txtOption1 = new TextField(  );
        this.txtOption1.setEditable( false );
        this.txtOption1.setPrefWidth( 500 );
        this.txtOption1.setPrefHeight( 50 );
        this.choiceBox = new ChoiceBox( FXCollections.observableArrayList(
            "",2,10,11,12,13,14,15,16,17,18,19,20) );
        this.choiceBox.setPrefSize( 20,40 );


        hBoxOption1.getChildren().addAll( txtOption1,choiceBox );

        /**
         *  Creating option 2
         *  */

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



        hBoxOption2.getChildren().addAll( txtOption2, cbFinishPointCards);



        /**
         *  Creating buttons for lobby
         *  */

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

    /**
     *  author Manuel Wirz
     *  Method for showing chat
     *  Returns a GridPane
     *  */

    public GridPane addGridPane() {

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize( 100, 300 );
        chatContent = new VBox();


        /**
         *  Layout GridPane
         *  */

        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(3, 3, 3, 3));


        /**
         *  Chatview
         *  */

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


    /**
     *  author Manuel Wirz
     *  Method for multi language
     *  */

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

        loadImg();
        setImg();

    }

    /**
     *  author Manuel Wirz
     *  Method create Img in different language
     *  */

    private void loadImg() {
        imgWaitENG = new Image(getClass().getResourceAsStream( "/lobby/Wait_ENG.png" ) );
        imgWaitCH = new Image(getClass().getResourceAsStream( "/lobby/Wait_CH.png" ));
        imgWaitDE = new Image( getClass().getResourceAsStream( "/lobby/Wait_DE.png" ) );
    }

    /**
     *  author Manuel Wirz
     *  Shows stage
     *  */
    public void start() {
        stage.show();
    }

    /**
     *  author Manuel Wirz
     *  sets the correct img in the ImageView (which language is selected)
     *  */

    public void setImg() {

        if (translator.getCurrentLanguage() == Language.ENGLISH) {
            clientImg.setImage(imgWaitENG);
        } else if (translator.getCurrentLanguage() == Language.SWISS_GERMAN) {
            clientImg.setImage(imgWaitCH);
        } else {
            clientImg.setImage(imgWaitDE);
        }
    }

    /**
     *  author Manuel Wirz
     *  close stage
     *  */
    public void stop(){
        stage.hide();
    }

    /**
     * author Michel Schlatter + Manuel Wirz
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
     *  Method for creating client view who is not the hoster
     *  */


    public void  setWaitText(){

        this.hBoxOptionClient = new VBox(  );
        Insets insets = new Insets( 15 );
        this.hBoxOptionClient.setPadding(insets );
        this.hBoxOptionClient.setAlignment( Pos.CENTER );
        this.hBoxOptionClient.setPrefWidth( 600 );
        this.hBoxOptionClient.setPrefHeight( 350 );


        ImageView imgHourglass = new ImageView(new Image(getClass().getResourceAsStream("/lobby/sanduhr.png")));
        imgHourglass.setPreserveRatio(true);
        imgHourglass.setFitHeight(80);


        clientImg.setPreserveRatio(true);
        clientImg.setFitHeight(50);


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

        this.hBoxOptionClient.getChildren().addAll(imgHourglass, clientImg, hBoxButtons );
        this.root.setCenter(hBoxOptionClient);
    }

    /**
     *  author Manuel Wirz
     *   Getters and Setters for LobbyView
     *  */

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

