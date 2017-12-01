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
    protected TextField textFieldNameLogger;
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
    Label lblInfo;
    Label lblPullStack;
    protected Button btnPlayMoneyCards;
    protected Button btnEndTurn;
    protected Button btnEndActionPhase;
    ImageView imgVGreyOutButton;
    ImageView imgVGreyOutHandStack;


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

        labelShowRound = new Label();

        // Creating 9 columns with different width
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


        // Creating 20 rows
        for (int i = 0; i < 20; i++) {
            RowConstraints row = new RowConstraints(30);
            gp.getRowConstraints().add(row);
        }


        // create backside card and label for pullStack
        Image pullStack = new Image(getClass().getResourceAsStream("back.jpg"));
        ImageView imgVpullStack = new ImageView(pullStack);
        imgVpullStack.setFitHeight(120);
        imgVpullStack.setFitWidth(75);
        gp.setRowSpan(imgVpullStack, 4);
        gp.setConstraints(imgVpullStack, 1, 15);
        gp.getChildren().add(imgVpullStack);
        lblPullStack = new Label();
        setLabelFormat(lblPullStack);
        gp.setConstraints(lblPullStack, 1, 15);


        // Set backside of trashStack
        setBackCardOfTrashStack();


        // PaneLayout for Hand and PlayingArea
        hs = new HandStackLayout();
        gp.getChildren().add(hs);
        gp.setConstraints(hs, 2, 15);
        gp.setRowSpan(hs, 4);

        cardPlayingArea = new HandStackLayout();
        gp.getChildren().add(cardPlayingArea);
        gp.setConstraints(cardPlayingArea, 2, 8);
        gp.setRowSpan(cardPlayingArea, 4);


        // Creates 3 buttons. But only 1 will be shown at the same time
        btnEndActionPhase = new Button("Aktionsrunde beenden");
        setStyleOfButtons(btnEndActionPhase);

        btnPlayMoneyCards = new Button("Geldkarten spielen");
        setStyleOfButtons(btnPlayMoneyCards);

        btnEndTurn = new Button("Spielzug beenden");
        setStyleOfButtons(btnEndTurn);

        gp.getChildren().add(btnEndActionPhase);



        // Images for greyOut. If it is not the users'turn, the handcards and button are grayed out.

        Image imgGreyOut = new Image(getClass().getResourceAsStream("grey.png"));

        imgVGreyOutHandStack = new ImageView(imgGreyOut);
        imgVGreyOutHandStack.setFitHeight(120);
        imgVGreyOutHandStack.setFitWidth(540);
        gp.setRowSpan(imgVGreyOutHandStack, 6);
        gp.setColumnSpan(imgVGreyOutHandStack, 4);
        gp.setValignment(imgVGreyOutHandStack, VPos.TOP);
        imgVGreyOutHandStack.setOpacity(0.5);

        imgVGreyOutButton = new ImageView(imgGreyOut);
        imgVGreyOutButton.setFitHeight(30);
        imgVGreyOutButton.setFitWidth(150);
        gp.setColumnSpan(imgVGreyOutButton, 2);
        gp.setValignment(imgVGreyOutButton, VPos.TOP);
        imgVGreyOutButton.setOpacity(0.5);

        gp.setConstraints(imgVGreyOutHandStack, 2, 15);
        gp.setConstraints(imgVGreyOutButton, 5, 13);


        lblInfo = new Label();
        lblInfo.setPrefSize(170, 30);
        gp.setColumnSpan(lblInfo, 2);
        gp.setValignment(lblInfo, VPos.TOP);
        lblInfo.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 14; -fx-background-color: #d3d1d1");
        gp.setConstraints(lblInfo, 2, 13);
        gp.getChildren().add(lblInfo);


        // root Layout

        //root.setBottom.
        root.setCenter(fpCenter);
        root.setRight(loggerAndChat());
        root.setTop(setTop());
        root.setLeft( setLeft());


        stage.setScene(scene);
        stage.setFullScreen(false);
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



    /**
     *  author Manuel Wirz
     *  */

    public VBox loggerAndChat() {

        this.VBoxLogger = new VBox(10);
        this.VBoxLogger.setPrefSize( 500, 150 );
        this.VBoxLogger.setAlignment( Pos.CENTER_LEFT );
        this.VBoxLogger.setPadding( new Insets( 0, 80, 0 , 0 ));

        this.loggerContent = new VBox(  );
        this.textFieldNameLogger = new TextField(  );
        this.textFieldNameLogger.setPrefWidth( 400 );


        this.scrollPaneLogger = new ScrollPane(  );
        this.scrollPaneLogger.setPrefSize( 400, 200 );
        this.scrollPaneLogger.setContent( loggerContent );
        this.scrollPaneLogger.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER );
        this.scrollPaneLogger.vvalueProperty().bind( loggerContent.heightProperty() );

        VBoxLogger.getChildren().addAll( textFieldNameLogger, scrollPaneLogger, addChatGridPane() );

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


    private void setStyleOfButtons(Button btn){
        btn.setPrefSize(150, 30);
        gp.setValignment(btn, VPos.TOP);
        gp.setColumnSpan(btn, 2);
        btn.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 10;");
        gp.setConstraints(btn, 5, 13);

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
        this.btnChatSend.getStyleClass().add("buttonChat");

        this.textFieldChat= new TextField();
        this.textFieldChat.setPromptText( "Enter Text" );
        this.textFieldChat.setPrefSize( 330,50 );

        this.txtNameChat = new TextField(  );
        this.txtNameChat.setEditable( false );
        this.txtNameChat.setMaxWidth( 900 );


        this.btnSendText = new Button();
        this.btnSendText.setPrefSize(330, 50);
        this.btnSendText.getStyleClass().add("buttonChat");


        // Create HBox +
        hbChat = new HBox(10);
        hbChat.setMaxSize( 700,200 );
        hbChat.setAlignment(Pos.CENTER);
        hbChat.getChildren().add(btnChatSend);
        hbChat.getChildren().add(btnSendText);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent( chatContent );
        scroll.setPrefSize( 800, 150);
        scroll.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER );
        scroll.vvalueProperty().bind( chatContent.heightProperty() );
        chatPane.add( txtNameChat,0,0 );
        chatPane.add(scroll,0,1);
        chatPane.add(hbChat, 0, 3);
        chatPane.add( textFieldChat, 0, 2 );

        return chatPane;
    }


    // Author Murat Kelleci
    public void setUserPoints(int userId, String userName, PlayerSet set) {

        String elementId = "UserPoints_" + userId;
        String text = userName +": "+ Integer.toString(set.calculatePoints());

        Node elm = VBoxPointsandPlayer.lookup("#" + elementId);

        if (elm == null) {
            // element does not exist yet
            Label lblUserNameAndPoints = new Label();
            lblUserNameAndPoints.getStyleClass().add("labelShowStats");
            lblUserNameAndPoints.setPrefSize(150,20 );
            lblUserNameAndPoints.setText(text);
            lblUserNameAndPoints.setId(elementId);

            VBoxPointsandPlayer.getChildren().addAll( lblUserNameAndPoints );

        } else {

            // element already exist
            Label lblUserNameAndPoints = (Label) elm;
            lblUserNameAndPoints.setText(text);

        }
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
     * Author Vanessa Cajochen
     */


    protected void setTexts() {

        this.btnChatSend.setText( getText( "chat.send" ) );
        this.btnSendText.setText( getText( "chat.nice!" ) );
        this.txtNameChat.setText( getText( "chat.chat" ) );
        this.endOptionRounds.setText( getText( "gameview.endOptionRound" ) );
        this.endOptionPoints.setText( getText( "gameview.endOptionPoint" ) );
        this.textFieldNameLogger.setText( getText( "gameview.logger" ) );
    }

    public void start() {
        stage.show();
    }

    public void stop(){
        stage.hide();
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
    public void addCardToHandStackPane(CardImageView cardImg){
        handStackList.add(cardImg);
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


    public BorderPane getRoot() {
        return root;
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


}
