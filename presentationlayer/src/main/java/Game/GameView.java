package Game;

import Controls.CardImageView;
import Controls.HandStackLayout;
import base.View;
import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.MoneyCard;
import com.weddingcrashers.businessmodels.MoneyType;
import com.weddingcrashers.businessmodels.PlayerSet;
import com.weddingcrashers.model.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Tooltip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
     FlowPane fp;
    HandStackLayout hs;
    HandStackLayout cardPlayingArea;
    ArrayList<CardImageView> handStackList = new ArrayList<CardImageView>();
    ArrayList<CardImageView> cardPlayingAreaList = new ArrayList<CardImageView>();
    Button btnPlayMoneyCards;

    public GameView(Stage stage, GameModel model){
        super(stage,model);
    }

    /**
     *  author Vanessa Cajochen
     *  */

    public Scene create_GUI(){
        FlowPane root = new FlowPane();
        Scene scene = new Scene(root, 1000, 600);
        setStylesheet(scene, GAME);
        gp = new GridPane();
        VBox vb = new VBox();
        root.getChildren().add(gp);
        root.getChildren().add( addChatGridPane() );
        gp.add(vb, 2, 14);

        gp.setGridLinesVisible(false);


        ColumnConstraints column = new ColumnConstraints(115);
        ColumnConstraints column1 = new ColumnConstraints(115);
        ColumnConstraints column2 = new ColumnConstraints(70);
        ColumnConstraints column3 = new ColumnConstraints(100);
        ColumnConstraints column4 = new ColumnConstraints(100);
        ColumnConstraints column5 = new ColumnConstraints(100);
        ColumnConstraints column6 = new ColumnConstraints(100);
        ColumnConstraints column7 = new ColumnConstraints(70);
        ColumnConstraints column8 = new ColumnConstraints(115);
        ColumnConstraints column9 = new ColumnConstraints(115);
        gp.getColumnConstraints().addAll(column, column1, column2, column3, column4, column5, column6, column7, column8, column9);


        // Creating 30 rows
        for (int i = 0; i<20;i++){
            RowConstraints row = new RowConstraints(30);
            gp.getRowConstraints().add(row);
        }





        Image back = new Image(getClass().getResourceAsStream("back.jpg"));
        ImageView imgVback = new ImageView(back);
        imgVback.setFitHeight(120);
        imgVback.setFitWidth(75);
        gp.setRowSpan(imgVback, 4);
        gp.setConstraints(imgVback, 1, 15);

        gp.getChildren().add(imgVback);

        Label lblNachziehstapel = new Label();
        setLabelFormat(lblNachziehstapel);
        gp.setConstraints(lblNachziehstapel, 1, 15);
        lblNachziehstapel.setText("10");





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


        Button btnPlayMoneyCards = new Button("Play All Cards");
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

        Button btnEndTurn = new Button("Spielzug beenden");
        btnEndTurn.setPrefSize(100, 30);
        gp.setValignment(btnEndTurn, VPos.TOP);
        btnEndTurn.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 10;");


        Label lblInfo = new Label("1 Aktion, 1 Kauf, 0 Geld");
        lblInfo.setPrefHeight(30);
        gp.setColumnSpan(lblInfo, 2);
        gp.setValignment(lblInfo, VPos.TOP);
        lblInfo.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 14; -fx-background-color: #d3d1d1");
        gp.setConstraints(lblInfo, 2, 13);
        gp.getChildren().add(lblInfo);





        gp.getChildren().addAll(btnPlayMoneyCards,btnEndActionPhase);


        // -------------------------------------------------------------------------------------------
        // -------------------------------------------------------------------------------------------
        // TODO: Migi ich weiss ned wo ich de SetonActon chan in Controller tue :-S

        btnPlayMoneyCards.setOnAction( event -> {
            moveMoneyCardsToPlayArea();
        } );



        // -------------------------------------------------------------------------------------------
        // -------------------------------------------------------------------------------------------





        // root Layout
        root.getChildren().add(addChatGridPane());
        //TODO Venessa u can use for your GUI
       // root.setCenter();
       // root.setRight();
       // root.setTop();
       // root.setLeft();

        stage.setScene(scene);
        stage.setFullScreen( false );
        stage.setTitle( "Dominion" );

        setTexts();
        this.stage.setResizable(true);
        return scene;
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
        chatPane.setPrefSize( 100, 350 );
        chatContent = new VBox();

        //GridPane Layout

        chatPane.setAlignment(Pos.BOTTOM_RIGHT);
        chatPane.setHgap(10);
        chatPane.setVgap(10);
        chatPane.setPadding(new Insets(25, 25, 25, 25));


        // ----- Chatview -------


        this.btnChatSend  = new Button();
        this.btnChatSend.setPrefSize(300, 50);

        this.textFieldChat= new TextField();
        this.textFieldChat.setPromptText( "Enter Text" );
        this.textFieldChat.setPrefSize( 450,50 );

        this.txtNameChat = new TextField(  );
        this.txtNameChat.setEditable( false );
        this.txtNameChat.setMaxWidth( 900 );


        this.btnSendText = new Button();
        this.btnSendText.setPrefSize(300, 50);


        // Create HBox +
        hbChat = new HBox(10);
        hbChat.setMaxSize( 700,200 );
        hbChat.setAlignment(Pos.CENTER);
        hbChat.getChildren().add(textFieldChat);
        hbChat.getChildren().add(btnChatSend);
        hbChat.getChildren().add(btnSendText);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent( chatContent );
        scroll.setMaxSize( 800, 200);
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


}
