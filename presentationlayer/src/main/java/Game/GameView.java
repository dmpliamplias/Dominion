package Game;

import Controls.CardImageView;
import Controls.HandStackLayout;
import alert.DominionAlert;
import base.View;
import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.PlayerSet;
import com.weddingcrashers.model.User;
import com.weddingcrashers.service.ServiceLocator;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import util.PLServiceLocator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static javafx.stage.Modality.WINDOW_MODAL;
import static util.StyleSheetPath.GAME;


public class GameView extends View<GameModel> {


    protected TextField textFieldChat;
    protected TextField textFieldNameLogger;
    protected Button btnChatSend;
    protected Button btnLobby;
    protected Button btnRanking;
    protected Button btnSendText;
    protected GridPane chatPane;
    protected HBox hbChat;
    protected VBox chatContent;
    protected TextField txtNameChat;
    protected Stage stageDialog;
    protected GridPane gp;
    protected BorderPane root;
    protected VBox VBoxLogger;
    protected VBox VBoxDisplayWinner;
    protected ScrollPane scrollPaneLogger;
    protected VBox VBoxPointsandPlayer;
    protected VBox loggerContent;
    protected VBox setTop;
    protected Text endOptionRounds;
    protected Text endOptionPoints;
    protected Text endOption;
    protected Label labelShowStats;
    protected TextField txtFieldShowRound;
    protected Text txtShowRound;
    protected Label txtLogger;
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
    protected Label txtLogger2;
    protected Text txtLoggerIsYourTurn;
    protected Text txtLoggerTurnIsOver;


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


        // Creating 9 columns with different width
        ColumnConstraints column = new ColumnConstraints(85);
        ColumnConstraints column1 = new ColumnConstraints(110);
        ColumnConstraints column2 = new ColumnConstraints(70);
        ColumnConstraints column3 = new ColumnConstraints(100);
        ColumnConstraints column4 = new ColumnConstraints(100);
        ColumnConstraints column5 = new ColumnConstraints(100);
        ColumnConstraints column6 = new ColumnConstraints(100);
        ColumnConstraints column7 = new ColumnConstraints(70);
        ColumnConstraints column8 = new ColumnConstraints(50);
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

        // Creates Curly Brackets for PlayingArea
        Image imgBracketLeft = new Image(getClass().getResourceAsStream("curlyBracketLeft.png"));
        ImageView imgVBracketLeft = new ImageView(imgBracketLeft);
        imgVBracketLeft.setFitHeight(120);
        imgVBracketLeft.setPreserveRatio(true);
        gp.setConstraints(imgVBracketLeft, 1, 8);
        gp.setRowSpan(imgVBracketLeft, 4);
        gp.setValignment(imgVBracketLeft, VPos.TOP);
        gp.setHalignment(imgVBracketLeft, HPos.RIGHT);
        gp.getChildren().add(imgVBracketLeft);

        Image imgBracketRight = new Image(getClass().getResourceAsStream("curlyBracketRight.png"));
        ImageView imgVBracketRight = new ImageView(imgBracketRight);
        imgVBracketRight.setFitHeight(120);
        imgVBracketRight.setPreserveRatio(true);
        gp.setConstraints(imgVBracketRight, 8, 8);
        gp.setRowSpan(imgVBracketRight, 4);
        gp.setValignment(imgVBracketRight, VPos.TOP);
        gp.setHalignment(imgVBracketRight, HPos.LEFT);
        gp.getChildren().add(imgVBracketRight);


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

        this.btnLobby = new Button();
        this.btnRanking= new Button();

        this.txtLogger = new Label(  );
        this.txtLogger2 = new Label(  );

        this.txtLoggerIsYourTurn = new Text(  );
        this.txtLoggerTurnIsOver = new Text(  );

        //root.setBottom.
        root.setCenter(fpCenter);
        root.setRight(loggerAndChat());
        root.setTop(setTop());
        root.setLeft( setLeft());


        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setTitle("Dominion");


        this.stage.setResizable(true);
        return scene;
    }


    /**
     * author Manuel Wirz
     */

    // Method for title and showing game end option -> return a VBox

    private VBox setTop() {
        this.setTop = new VBox();
        this.setTop.setAlignment(Pos.CENTER);
        this.endOption = new Text();
        Text title = new Text("Dominion");
        title.getStyleClass().add("title");

        this.endOption.setTextAlignment(TextAlignment.CENTER);
        this.endOptionRounds = new Text();
        this.endOptionPoints = new Text();

        this.setTop.getChildren().addAll(menuBar, title, endOption);

        menuBar.getMenuSettings().getItems().remove(menuBar.getMenuLanguage());

        return setTop;
    }


    public VBox setLeft(){
        this.VBoxPointsandPlayer = new VBox(  );
        this.VBoxPointsandPlayer.setSpacing( 20 );
        this.txtFieldShowRound = new TextField(  );
        this.txtFieldShowRound.setEditable( false );
        this.txtFieldShowRound.setPrefSize(150, 20);
        this.txtShowRound = new Text(  );
        this.VBoxPointsandPlayer.setAlignment( Pos.CENTER );
        this.VBoxPointsandPlayer.getChildren().add( txtFieldShowRound );
        return VBoxPointsandPlayer;
    }



    /**
     *  author Manuel Wirz
     *  */

    // Method for creating logger


    public VBox loggerAndChat() {

        this.VBoxLogger = new VBox(10);
        this.VBoxLogger.setPrefSize( 300, 150 );
        this.VBoxLogger.setAlignment( Pos.CENTER_LEFT );
        this.VBoxLogger.setPadding( new Insets( 0, 10, 0 , 0 ));

        this.loggerContent = new VBox(  );
        this.textFieldNameLogger = new TextField(  );
        this.textFieldNameLogger.setPrefWidth( 400 );


        this.scrollPaneLogger = new ScrollPane(  );
        this.scrollPaneLogger.setPrefSize( 300, 200 );
        this.scrollPaneLogger.setContent( loggerContent );
        this.scrollPaneLogger.setHbarPolicy( ScrollPane.ScrollBarPolicy.NEVER );
        this.scrollPaneLogger.vvalueProperty().bind( loggerContent.heightProperty() );

        VBoxLogger.getChildren().addAll( textFieldNameLogger, scrollPaneLogger, addChatGridPane() );

        return VBoxLogger;

    }


    /**
     *  author Manuel Wirz
     *  */

    // same logic as in LobbyView

    protected void setLoggerContent(String msg, Color color){

        Label lbl = new Label(  );
        lbl.setText( msg );
        lbl.setTextFill( color );
        this.loggerContent.getChildren().add( lbl );

    }

    /**
     *  author Vanessa Cajochen
     *  */


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
        //btn.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 10; ");
        btn.getStyleClass().add("buttonGameView");

        gp.setConstraints(btn, 5, 13);

    }


    /**
     *  author Manuel Wirz
     *  */

    // GridPane for showing the chat and the buttons

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


    /** @Author Murat Kelleci
     *
     * @param userId
     * @param userName
     * @param set
     * @param activeUserId
     */
    public void setUserPoints(int userId, String userName, PlayerSet set, int activeUserId) {
        String elementId = "UserPoints_" + userId;
        String text = userName + ": " + Integer.toString(set.calculatePoints());
        String activeUser = "UserPoints_" + activeUserId;

        Node elm = VBoxPointsandPlayer.lookup("#" + elementId);
        Label lblUserNameAndPoints;

        if (elm == null) {
            // element does not exist yet
            lblUserNameAndPoints = new Label();
            lblUserNameAndPoints.getStyleClass().add("labelShowStats");
            lblUserNameAndPoints.setPrefSize(150, 20);
            lblUserNameAndPoints.setText(text);
            lblUserNameAndPoints.setId(elementId);
            VBoxPointsandPlayer.getChildren().addAll(lblUserNameAndPoints);

        } else {
            // element already exist
            lblUserNameAndPoints = (Label) elm;
            lblUserNameAndPoints.setText(text);
        }

        if (activeUser.equals(lblUserNameAndPoints.getId())) {
            lblUserNameAndPoints.setStyle("-fx-border-color: green; -fx-border-width: 3");
        } else {
            lblUserNameAndPoints.setStyle("-fx-border-color: black; -fx-border-width: 2");
        }


    }

   public void startWinnerStage(ObservableList<WinningUser> winningUsers, boolean isWinner) {
       displayWinnerDialog(isWinner);

       BorderPane root = new BorderPane();
        this.stageDialog = new Stage();
        this.stageDialog.setOnCloseRequest(evt -> {
            // prevent window from closing
            evt.consume();
        });
        stageDialog.initOwner(stage);
        stageDialog.initModality(WINDOW_MODAL);
        Scene scene = new Scene(root,400,300);
        this.stageDialog.setScene(scene);
        createVBox(winningUsers);
        root.setCenter(VBoxDisplayWinner);
        setTextDialog();
        stageDialog.show();
   }

   private void displayWinnerDialog(boolean isWinner) {

       //TODO Murat add sounds in folger sounds -> winner.wav and loser.wav

        if (isWinner) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            String winner = new String("winner");
            Image winnerPic = new Image(getClass().getResourceAsStream("winner.jpg"));
            ImageView imgVwinnerPic = new ImageView(winnerPic);
            alert1.setGraphic(imgVwinnerPic);
            alert1.setHeaderText("");
            alert1.setTitle("Winner Dialog");
            if(menuBar.getMenuItemSoundUnmute().isSelected()) {
                GameController.playSound(winner);
            }
            alert1.showAndWait();


        }
        else {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            String loser = new String("loser");
            Image loserPic = new Image(getClass().getResourceAsStream("loser.png"));
            ImageView imgVloserPic = new ImageView(loserPic);
            alert2.setGraphic(imgVloserPic);
            alert2.setHeaderText("");
            alert2.setTitle("Looser Dialog");
            if(menuBar.getMenuItemSoundUnmute().isSelected()) {
                GameController.playSound(loser);
            }
            alert2.showAndWait();

        }


   }

    protected void setTextDialog(){
        this.btnLobby.setText(getText("btn.Lobby"));
        this.btnRanking.setText(getText("btn.Ranking"));



    }


    public void createVBox(ObservableList<WinningUser> winningUsers) {
        this.btnLobby.setPrefSize(180, 80);
        this.btnRanking.setPrefSize(180, 80);
        this.VBoxDisplayWinner = new VBox();
        TableView<WinningUser> tableView = createWinningUserTableView(winningUsers);
        HBox hbox =new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(btnLobby,btnRanking);

        VBoxDisplayWinner.getChildren().addAll(tableView, hbox);
    }

    private TableView<WinningUser> createWinningUserTableView(final ObservableList<WinningUser> winningUsers) {
        TableView<WinningUser> tableView = new TableView<>();

        TableColumn<WinningUser, String> name = new TableColumn<>(getText("gameview.winningUsers.name"));
        TableColumn<WinningUser, String> points = new TableColumn<>(getText("gameview.winningUsers.points"));
        TableColumn<WinningUser, String> position = new TableColumn<>(getText("gameview.winningUsers.position"));

        tableView.getColumns().addAll(position,name, points);

        tableView.setItems(winningUsers);

        name.setCellValueFactory(new PropertyValueFactory<WinningUser, String>("userName"));
        points.setCellValueFactory(new PropertyValueFactory<WinningUser, String>("points"));
        position.setCellValueFactory(new PropertyValueFactory<WinningUser, String>("position"));

        return tableView;
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
        this.txtLogger.setText( getText( "gameview.loggerText" ) );
        this.txtLogger2.setText( getText( "gameview.loggerText2" ) );
        this.txtShowRound.setText( getText( "gameview.showRound" ) );
        this.txtLoggerTurnIsOver.setText( getText( "gameview.LoggerTurnIsOVer" ) );
        this.txtLoggerIsYourTurn.setText( getText( "gameview.LoggerIsYourTurn" ) );
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
             gp.setHalignment(imgVtrayStack, HPos.LEFT);
             gp.setConstraints(imgVtrayStack, 0, 15);
             gp.getChildren().add(imgVtrayStack);
         }

         // If the trashStack is empty, the trashStack shows the back of the card.
        public void setBackCardOfTrashStack(){
            imgVtrayStack = new ImageView(new Image(getClass().getResourceAsStream("back.jpg")));
            imgVtrayStack.setFitHeight(120);
            imgVtrayStack.setFitWidth(75);
            gp.setRowSpan(imgVtrayStack, 4);
            gp.setHalignment(imgVtrayStack, HPos.LEFT);
            gp.setConstraints(imgVtrayStack, 0, 15);
            gp.getChildren().add(imgVtrayStack);
        }

        // Grey img gets removed and Player sees that he can play now
        public void disableView(){
            gp.getChildren().removeAll(imgVGreyOutHandStack, imgVGreyOutButton);
        }

        // HandCards and Button gets greyedout. Player can no longer play cards and click Button
        public void enableView(){
            if(!gp.getChildren().contains(imgVGreyOutHandStack)) {
                gp.getChildren().addAll(imgVGreyOutHandStack, imgVGreyOutButton);
            }
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

    public Button getBtnLobby() {
        return btnLobby;
    }

    public Button getBtnRanking(){
            return btnRanking;
    }

    public Label getTxtLogger() {
        return txtLogger;
    }

    public Label getTxtLogger2() {
        return txtLogger2;
    }

    public TextField getTxtFieldShowRound() {
        return txtFieldShowRound;
    }

    public Text getTxtShowRound() {
        return txtShowRound;
    }

    public Text getTxtLoggerIsYourTurn() {
        return txtLoggerIsYourTurn;
    }

    public Text getTxtLoggerTurnIsOver() {
        return txtLoggerTurnIsOver;
    }


}
