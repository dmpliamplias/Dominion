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
     Label lblKupfer;
     Label lblSilber;
     Label lblGold;
     Label lblAnwesen;
     Label lblDorf;
     Label lblGarten;
     Label lblGeldverleiher;
     Label lblHerzogtum;
     Label lblHolzfäller;
     Label lblJahrmarkt;
     Label lblMarkt;
     Label lblLaboratorium;
     Label lblProvinz;
     Label lblSchmiede;
     CardImageView imgKupfer;
     CardImageView imgSilber;
     CardImageView imgGold;
     CardImageView imgAnwesen;
     CardImageView imgDorf;
     CardImageView imgGarten;
     CardImageView imgGeldverleiher;
     CardImageView imgHerzogtum;
     CardImageView imgHolzfäller;
     CardImageView imgJahrmarkt;
     CardImageView imgMarkt;
     CardImageView imgLaboratorium;
     CardImageView imgProvinz;
     CardImageView imgSchmiede;
     FlowPane fp;
    HandStackLayout hs;
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
        imgVback.setFitHeight(150);
        imgVback.setFitWidth(100);
        gp.setConstraints(imgVback, 1, 14);
        gp.setRowSpan(imgVback, 4);

        gp.getChildren().add(imgVback);



        hs = new HandStackLayout();
        gp.getChildren().add(hs);
        gp.setConstraints(hs, 3, 14);
        gp.setRowSpan(hs, 5);



        Label lblNachziehstapel = new Label();
        gp.setConstraints(lblNachziehstapel, 1, 14);
        setLabelFormat(lblNachziehstapel);



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


    public void setHandStackView(ArrayList<CardImageView> handStack){

        for (int i = 0; i<5;i++){
            hs.getChildren().add(handStack.get(i));
        }
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
