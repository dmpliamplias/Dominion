package Game;

import Controls.CardImageView;
import Controls.HandStackLayout;
import base.View;
import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.PlayerSet;
import com.weddingcrashers.model.User;
import com.weddingcrashers.service.Language;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.scene.text.Text;
import util.PLServiceLocator;

import java.util.ArrayList;

import static com.weddingcrashers.model.Settings_.user;
import static com.weddingcrashers.model.User_.userName;
import static util.StyleSheetPath.GAME;


public class GameView extends View<GameModel> {


    protected TextField textFieldChat;
    protected Button btnChatSend;
    protected Button btnSendText;
    protected GridPane chatPane;
    protected HBox hbChat;
    protected VBox chatContent;
    protected TextField txtNameChat;
    protected GridPane gp;
    protected BorderPane root;
    protected VBox VBoxLogger;
    protected ScrollPane scrollPaneLogger;
    protected VBox VBoxPointsandPlayer;
    protected VBox loggerContent;
    protected VBox setTop;
    protected Text endOptionRounds;
    protected Text endOptionPoints;
    protected Text endOption;
    protected Label labelShowStats;
    protected Label labelShowRound;
    HandStackLayout hs;
    HandStackLayout cardPlayingArea;
    ArrayList<CardImageView> handStackList = new ArrayList<CardImageView>();
    ArrayList<CardImageView> cardPlayingAreaList = new ArrayList<CardImageView>();
    protected Button btnPlayMoneyCards;
    Label lblInfo;
    Label lblPullStack;
    Button btnEndTurn;
    ImageView imgVtrayStack;

    public GameView(Stage stage, GameModel model) {
        super(stage, model);
    }

    /**
     * author Vanessa Cajochen
     */

    public Scene create_GUI() {

        root = new BorderPane();
        FlowPane fpCenter = new FlowPane();
        fpCenter.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 800, 600);
        setStylesheet(scene, GAME);
        gp = new GridPane();
        VBox vb = new VBox();
        fpCenter.getChildren().add(gp);
        gp.add(vb, 2, 14);

        gp.setGridLinesVisible(false);


        ColumnConstraints column = new ColumnConstraints(15);
        ColumnConstraints column1 = new ColumnConstraints(115);
        ColumnConstraints column2 = new ColumnConstraints(70);
        ColumnConstraints column3 = new ColumnConstraints(100);
        ColumnConstraints column4 = new ColumnConstraints(100);
        ColumnConstraints column5 = new ColumnConstraints(100);
        ColumnConstraints column6 = new ColumnConstraints(100);
        ColumnConstraints column7 = new ColumnConstraints(70);
        ColumnConstraints column8 = new ColumnConstraints(115);
        ColumnConstraints column9 = new ColumnConstraints(15);
        gp.getColumnConstraints().addAll(column, column1, column2, column3, column4, column5, column6, column7, column8, column9);


        // Creating 30 rows
        for (int i = 0; i < 20; i++) {
            RowConstraints row = new RowConstraints(30);
            gp.getRowConstraints().add(row);
        }


        Image pullStack = new Image(getClass().getResourceAsStream("back.jpg"));
        ImageView imgVpullStack = new ImageView(pullStack);
        imgVpullStack.setFitHeight(120);
        imgVpullStack.setFitWidth(75);
        gp.setRowSpan(imgVpullStack, 4);
        gp.setConstraints(imgVpullStack, 1, 15);

        // Set backside of trashStack
        setBackCardOfTrashStack();




        gp.getChildren().addAll(imgVpullStack);


        lblPullStack = new Label();
        setLabelFormat(lblPullStack);
        gp.setConstraints(lblPullStack, 1, 15);


        // PaneLayout for Hand and PlayingArea
        hs = new HandStackLayout();
        gp.getChildren().add(hs);
        gp.setConstraints(hs, 2, 15);
        gp.setRowSpan(hs, 4);

        cardPlayingArea = new HandStackLayout();
        gp.getChildren().add(cardPlayingArea);
        gp.setConstraints(cardPlayingArea, 2, 8);
        gp.setRowSpan(cardPlayingArea, 4);


        // -------------------------------------------------------------------------------------------
        // -------------------------------------------------------------------------------------------

        // TEST


        btnPlayMoneyCards = new Button("Play All Cards");
        btnPlayMoneyCards.setPrefSize(100, 30);
        gp.setValignment(btnPlayMoneyCards, VPos.TOP);
        btnPlayMoneyCards.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 10;");
        gp.setConstraints(btnPlayMoneyCards, 1, 11);

        Button btnEndActionPhase = new Button("Aktionsrunde beenden");
        btnEndActionPhase.setPrefHeight(30);
        gp.setValignment(btnEndActionPhase, VPos.TOP);
        gp.setColumnSpan(btnEndActionPhase, 2);
        btnEndActionPhase.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 10;");
        gp.setConstraints(btnEndActionPhase, 5, 13);

        btnEndTurn = new Button("Spielzug beenden");
        btnEndTurn.setPrefHeight(30);
        gp.setValignment(btnEndTurn, VPos.TOP);
        gp.setColumnSpan(btnEndTurn, 2);
        btnEndTurn.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 10;");
        gp.setConstraints(btnEndTurn, 5, 13);

        lblInfo = new Label();
        lblInfo.setPrefHeight(30);
        gp.setColumnSpan(lblInfo, 2);
        gp.setValignment(lblInfo, VPos.TOP);
        lblInfo.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 14; -fx-background-color: #d3d1d1");
        gp.setConstraints(lblInfo, 2, 13);
        gp.getChildren().add(lblInfo);


        gp.getChildren().addAll(btnPlayMoneyCards, btnEndTurn);


        // root Layout

        //root.setBottom.
        root.setCenter(fpCenter);
        root.setRight(loggerAndChat());
        root.setTop(setTop());
        root.setLeft( setLeft());


        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("Dominion");

        setTexts();
        this.stage.setResizable(true);
        return scene;


    }

    /**
     * author Manuel Wirz
     */

    private VBox setTop() {

        this.setTop = new VBox();
        this.setTop.setAlignment(Pos.CENTER);
        this.endOption = new Text();
        Text title = new Text("Dominion");
        title.getStyleClass().add("title");

        this.endOption.setTextAlignment(TextAlignment.CENTER);
        this.endOptionRounds = new Text();
        this.endOptionPoints = new Text();


        this.setTop.getChildren().addAll(title, endOption);

        return setTop;

    }


    public VBox setLeft(){

        this.VBoxPointsandPlayer = new VBox(  );
        this.VBoxPointsandPlayer.setAlignment( Pos.CENTER );


        return VBoxPointsandPlayer;
    }

    // Author Murat Kelleci
    public void setUserPoints(int userId, String userName, PlayerSet set) {


        String elementId = "UserPoints " + userId;

        Node elm = scene.lookup("#" + elementId);

        if (elm == null) {
            // element does not exist yet
            Label lblUserName = new Label();
            lblUserName.getStyleClass().add("labelShowStats");
            lblUserName.setPrefSize(50,20 );
            Label lblUserPoints = new Label();
            lblUserPoints.getStyleClass().add("labelShowStats");
            lblUserPoints.setPrefSize(50,20 );

            lblUserName.setText(userName);
            lblUserPoints.setId(elementId);
            lblUserPoints.setText(Integer.toString(set.calculatePoints()));

            VBoxPointsandPlayer.getChildren().addAll( lblUserName, lblUserPoints );

        } else {

            // element already exist
            Label lblUserPoints = (Label) elm;
            lblUserPoints.setText(Integer.toString(set.calculatePoints()));

        }
        
    }



    /**
     *  author Manuel Wirz
     *  */

    public VBox loggerAndChat() {

        this.VBoxLogger = new VBox(  );
        this.VBoxLogger.setPrefSize( 500, 150 );
        this.VBoxLogger.setAlignment( Pos.CENTER_LEFT );
        this.VBoxLogger.setPadding( new Insets( 0, 80, 0 , 0 ));

        this.loggerContent = new VBox(  );


        this.scrollPaneLogger = new ScrollPane(  );
        this.scrollPaneLogger.setPrefSize( 400, 200 );
        this.scrollPaneLogger.setContent( loggerContent );
        this.scrollPaneLogger.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER );
        this.scrollPaneLogger.vvalueProperty().bind( loggerContent.heightProperty() );

        VBoxLogger.getChildren().addAll( scrollPaneLogger, addChatGridPane() );

        return VBoxLogger;

    }

    /**
     *  author Manuel Wirz
     *  */

    protected void setLoggerContent(String msg, Color color){

        Label lbl = new Label(  );
        lbl.setText( msg );
        lbl.setTextFill( color );
        this.loggerContent.getChildren().add( lbl );

    }

    private void setLabelFormat(Label lbl){
        gp.getChildren().add(lbl);
        gp.setValignment(lbl, VPos.TOP);
        lbl.getStyleClass().add("labelNumber");
        lbl.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 10;");
        lbl.setPrefSize(16, 15);
        lbl.setAlignment(Pos.CENTER);

    }




    /**
     *  author Manuel Wirz
     *  */

    public GridPane addChatGridPane(){

        chatPane = new GridPane();
        chatPane.setPrefSize( 800, 350 );
        chatContent = new VBox();

        //GridPane Layout

        chatPane.setAlignment(Pos.BOTTOM_RIGHT);
        chatPane.setHgap(10);
        chatPane.setVgap(10);
        chatPane.setPadding(new Insets(0, 0, 0, 0));


        // ----- Chatview -------


        this.btnChatSend  = new Button();
        this.btnChatSend.setPrefSize(330, 50);

        this.textFieldChat= new TextField();
        this.textFieldChat.setPromptText( "Enter Text" );
        this.textFieldChat.setPrefSize( 330,50 );

        this.txtNameChat = new TextField(  );
        this.txtNameChat.setEditable( false );
        this.txtNameChat.setMaxWidth( 900 );


        this.btnSendText = new Button();
        this.btnSendText.setPrefSize(330, 50);


        // Create HBox +
        hbChat = new HBox(10);
        hbChat.setMaxSize( 700,200 );
        hbChat.setAlignment(Pos.CENTER);
        hbChat.getChildren().add(textFieldChat);
        hbChat.getChildren().add(btnChatSend);
        hbChat.getChildren().add(btnSendText);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent( chatContent );
        scroll.setPrefSize( 800, 150);
        scroll.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER );
        scroll.vvalueProperty().bind( chatContent.heightProperty() );
        chatPane.add( txtNameChat,0,0 );
        chatPane.add(scroll,0,1);
        chatPane.add(hbChat, 0, 2);



        return chatPane;
    }




    protected void setTexts() {

        this.btnChatSend.setText( getText( "chat.send" ) );
        this.btnSendText.setText( getText( "chat.nice!" ) );
        this.txtNameChat.setText( getText( "chat.chat" ) );
        this.endOptionRounds.setText( getText( "gameview.endOptionRound" ) );
        this.endOptionPoints.setText( getText( "gameview.endOptionPoint" ) );
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


    public TextField getTextFieldChat() {
        return textFieldChat;
    }

    public Button getBtnChatSend() {
        return btnChatSend;
    }

    public Button getBtnSendText() {
        return btnSendText;
    }

    public GridPane getChatPane() {
        return chatPane;
    }

    public HBox getHbChat() {
        return hbChat;
    }

    public VBox getChatContent() {
        return chatContent;
    }

    public TextField getTxtNameChat() {
        return txtNameChat;
    }

    /**
     * Author Vanessa Cajochen
     */
 public CardImageView setCardImageView(Card card, CardImageView.CardSize size, int col,int row, int rowSpan, int cardCount){

     CardImageView imgView = new CardImageView(card, size);

     gp.setConstraints(imgView, col, row);
     gp.setRowSpan(imgView, rowSpan);
     gp.getChildren().add(imgView);

     Tooltip tooltip = new Tooltip();
     CardImageView imgForToolTip = new CardImageView(card, CardImageView.CardSize.tooltip);
     tooltip.setGraphic(imgForToolTip);
     Tooltip.install(imgView, tooltip);

     Label lbl = new Label();
     gp.setConstraints(lbl, col, row);
     setLabelFormat(lbl);
     lbl.setText(Integer.toString(cardCount));

     return imgView;
 }

    // Adds the card to the HandStackPane.
    public void addCardToHandStackPane(Card card){
        CardImageView cardImg = new CardImageView(card, CardImageView.CardSize.bigSize);
        handStackList.add(cardImg);
        System.out.println(handStackList.size());
        updateStackLayout();
        hs.getChildren().add(cardImg);
    }

    // Moves card from the HandStackPane to the cardPlayingArea.
    public void moveCardToPlayingArea (CardImageView cardImg) {
        handStackList.remove(cardImg);
        hs.getChildren().remove(cardImg);
        cardPlayingAreaList.add(cardImg);
        updateStackLayout();
        cardPlayingArea.getChildren().add(cardImg);
    }


    // Moves all MoneyCards with one click to the button
    public void moveMoneyCardsToPlayArea(){
        ArrayList<CardImageView> moveList = new ArrayList<CardImageView>();
        for(int i = 0; i < handStackList.size(); i++){
            if (handStackList.get(i).getCard().getName().equals("Kupfer") || handStackList.get(i).getCard().getName().equals("Silber") || handStackList.get(i).getCard().getName().equals("Gold")){
                moveList.add(handStackList.get(i));
            }
        }
        for(int i = 0; i < moveList.size(); i++) {
            moveCardToPlayingArea(moveList.get(i));
        }

    }

        // If there are more than 5 cards in the PlayingArea / HandCardArea the gap will between the cards gets smaller
        // 1 card has has a width of 75. 540 ist the maximum length of the Pane. 540 - 75 = 465.
        public void updateStackLayout(){
            if (handStackList.size() > 5) {hs.setCardInterval(465 / (handStackList.size()-1));
            } else { hs.setCardInterval(116.25);}

            if (cardPlayingAreaList.size() > 5) {cardPlayingArea.setCardInterval(465 / (cardPlayingAreaList.size()-1));
            } else {cardPlayingArea.setCardInterval(116.25);}
         }


         public void updatelblInfo(int action, int buys, int money){
            String sAction = getText("gameview.action");
            String sBuy = getText("gameview.buy");
            String sMoney = getText("gameview.money");

            lblInfo.setText(sAction + " " + action + ", " + sBuy + " " + buys + ", " + sMoney + " " + money);
         }


         // Clear HandStack and CardPlayingArea
         public void endOfTurn(){
             hs.getChildren().clear();
             cardPlayingArea.getChildren().clear();
             handStackList.clear();
             cardPlayingAreaList.clear();
         }

         public void updateLblPullStack(int numberOfPullStack){
             lblPullStack.setText(""+numberOfPullStack);
         }


         // When card "Geldverleiher" is played, a Copper from the handStack is trashed.
         public void trashCopper(){
             boolean found = false;
             int i = 0;
             while (!found){
                 if (handStackList.get(i).getCard().getName().equals("Kupfer")){
                     hs.getChildren().remove(handStackList.get(i));
                     handStackList.remove(i);
                     found = true;
                 }
                 i++;
            }
         }


         // The last played card from the handStack lies on the trashStack
         public void setBackCardOfTrashStack(Card card){
             imgVtrayStack = new CardImageView(card, CardImageView.CardSize.bigSize);
             imgVtrayStack.setFitHeight(120);
             imgVtrayStack.setFitWidth(75);
             gp.setRowSpan(imgVtrayStack, 4);
             gp.setHalignment(imgVtrayStack, HPos.RIGHT);
             gp.setConstraints(imgVtrayStack, 8, 15);
             gp.getChildren().add(imgVtrayStack);
         }


         // If the trashStack is empty, the trashStack shows the back of the card.
        public void setBackCardOfTrashStack(){
            imgVtrayStack = new ImageView(new Image(getClass().getResourceAsStream("back.jpg")));
            imgVtrayStack.setFitHeight(120);
            imgVtrayStack.setFitWidth(75);
            gp.setRowSpan(imgVtrayStack, 4);
            gp.setHalignment(imgVtrayStack, HPos.RIGHT);
            gp.setConstraints(imgVtrayStack, 8, 15);
            gp.getChildren().add(imgVtrayStack);
        }

/*
    public void createOtherPlayerBox(PlayerSet set, HashMap<Integer, User> users){
        int countEnemys = users.size()-1;
        String userName = users.get(set.getUserId()).getUserName();
        ArrayList<User> list = new ArrayList<User>(users.values());
        Collections.sort(list);
        int row = 4 + list.get()
            Label lblenemy1 = new Label();
            gp.setConstraints(lblenemy1, 1, 4);
            lblenemy1.setText(userName);
            gp.getChildren().add(lblenemy1);


    }
    */

    public GridPane getGp() {
        return gp;
    }

    public BorderPane getRoot() {
        return root;
    }

    public VBox getVBoxLogger() {
        return VBoxLogger;
    }

    public ScrollPane getScrollPaneLogger() {
        return scrollPaneLogger;
    }

    public VBox getVBoxPointsandPlayer() {
        return VBoxPointsandPlayer;
    }

    public VBox getLoggerContent() {
        return loggerContent;
    }

    public HandStackLayout getHs() {
        return hs;
    }

    public HandStackLayout getCardPlayingArea() {
        return cardPlayingArea;
    }

    public ArrayList<CardImageView> getHandStackList() {
        return handStackList;
    }

    public ArrayList<CardImageView> getCardPlayingAreaList() {
        return cardPlayingAreaList;
    }

    public Button getBtnPlayMoneyCards() {
        return btnPlayMoneyCards;
    }

    public Label getLblInfo() {
        return lblInfo;
    }

    public Label getLblPullStack() {
        return lblPullStack;
    }

    public Button getBtnEndTurn() {
        return btnEndTurn;
    }

    public VBox getSetTop() {
        return setTop;
    }

    public Text getEndOption() {
        return endOptionRounds;
    }

    public Text getEndOptionRounds() {
        return endOptionRounds;
    }

    public Text getEndOptionPoints() {
        return endOptionPoints;
    }

}
