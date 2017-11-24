package Game;

import Controls.CardImageView;
import base.View;
import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.MoneyCard;
import com.weddingcrashers.businessmodels.MoneyType;
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
        gp.setRowSpan(imgVback, 5);

        gp.getChildren().add(imgVback);


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
 public void setCardImageView(CardImageView cv, int colIdx, int rowIdx){
     int val = 5;
     if(cv.getCardSize() == CardImageView.CardSize.miniMini){
         val = 2;
     }else if(cv.getCardSize() == CardImageView.CardSize.miniSize){
         val = 3;
     }
     gp.setConstraints(cv, colIdx, rowIdx);
     gp.setRowSpan(cv, val);
     gp.getChildren().add(imgKupfer);

     Tooltip ttKupfer = new Tooltip();
     CardImageView imgttKupfer = new CardImageView((getCard(list,"Kupfer")), CardImageView.CardSize.tooltip, this);
     ttKupfer.setGraphic(imgttKupfer);
     Tooltip.install(imgKupfer, ttKupfer);
 }

    public void updateUnusedCards(ArrayList<Card> list)
    {

        imgKupfer = new CardImageView((getCard(list,"Kupfer")), CardImageView.CardSize.miniMini, this);


        Tooltip ttKupfer = new Tooltip();
        CardImageView imgttKupfer = new CardImageView((getCard(list,"Kupfer")), CardImageView.CardSize.tooltip, this);
        ttKupfer.setGraphic(imgttKupfer);
        Tooltip.install(imgKupfer, ttKupfer);

        lblKupfer = new Label();
        gp.setConstraints(lblKupfer, 2, 2);
        setLabelFormat(lblKupfer);
        lblKupfer.setText(Integer.toString(countCards(list, "Kupfer")));


        imgSilber = new CardImageView((getCard(list,"Silber")), CardImageView.CardSize.miniMini, this);
        gp.setConstraints(imgSilber, 2, 4);
        gp.setRowSpan(imgSilber, 2);
        gp.getChildren().add(imgSilber);

        Tooltip ttSilber = new Tooltip();
        CardImageView imgttSilber = new CardImageView((getCard(list,"Silber")), CardImageView.CardSize.tooltip, this);
        ttSilber.setGraphic(imgttSilber);
        Tooltip.install(imgSilber, ttSilber);

        lblSilber = new Label();
        gp.setConstraints(lblSilber, 2, 4);
        setLabelFormat(lblSilber);
        lblSilber.setText(Integer.toString(countCards(list, "Silber")));


        imgGold = new CardImageView((getCard(list,"Gold")), CardImageView.CardSize.miniMini, this);
        gp.setConstraints(imgGold, 2, 6);
        gp.setRowSpan(imgGold, 2);
        gp.getChildren().add(imgGold);

        Tooltip ttGold = new Tooltip();
        CardImageView imgttGold = new CardImageView((getCard(list,"Gold")), CardImageView.CardSize.tooltip, this);
        ttGold.setGraphic(imgttGold);
        Tooltip.install(imgGold, ttGold);

        lblGold = new Label();
        gp.setConstraints(lblGold, 2, 6);
        setLabelFormat(lblGold);
        lblGold.setText(Integer.toString(countCards(list, "Gold")));


        imgAnwesen = new CardImageView((getCard(list,"Anwesen")), CardImageView.CardSize.miniMini, this);
        gp.setConstraints(imgAnwesen, 7, 2);
        gp.setRowSpan(imgAnwesen, 2);
        gp.getChildren().add(imgAnwesen);

        Tooltip ttAnwesen = new Tooltip();
        CardImageView imgttAnwesen = new CardImageView((getCard(list,"Anwesen")), CardImageView.CardSize.tooltip, this);
        ttAnwesen.setGraphic(imgttAnwesen);
        Tooltip.install(imgAnwesen, ttAnwesen);

        lblAnwesen = new Label();
        gp.setConstraints(lblAnwesen, 7, 2);
        setLabelFormat(lblAnwesen);
        lblAnwesen.setText(Integer.toString(countCards(list, "Anwesen")));


        imgProvinz = new CardImageView((getCard(list,"Provinz")), CardImageView.CardSize.miniMini, this);
        gp.setConstraints(imgProvinz, 7, 6);
        gp.setRowSpan(imgProvinz, 2);
        gp.getChildren().add(imgProvinz);

        Tooltip ttProvinz = new Tooltip();
        CardImageView imgttProvinz = new CardImageView((getCard(list,"Provinz")), CardImageView.CardSize.tooltip, this);
        ttProvinz.setGraphic(imgttProvinz);
        Tooltip.install(imgProvinz, ttProvinz);

        lblProvinz = new Label();
        gp.setConstraints(lblProvinz, 7, 6);
        setLabelFormat(lblProvinz);
        lblProvinz.setText(Integer.toString(countCards(list, "Provinz")));


        imgHerzogtum = new CardImageView((getCard(list,"Herzogtum")), CardImageView.CardSize.miniMini, this);
        gp.setConstraints(imgHerzogtum, 7, 4);
        gp.setRowSpan(imgHerzogtum, 2);
        gp.getChildren().add(imgHerzogtum);

        Tooltip ttHerzogtum = new Tooltip();
        CardImageView imgttHerzogtum = new CardImageView((getCard(list,"Herzogtum")), CardImageView.CardSize.tooltip, this);
        ttHerzogtum.setGraphic(imgttHerzogtum);
        Tooltip.install(imgHerzogtum, ttHerzogtum);

        lblHerzogtum = new Label();
        gp.setConstraints(lblHerzogtum, 7, 4);
        setLabelFormat(lblHerzogtum);
        lblHerzogtum.setText(Integer.toString(countCards(list, "Herzogtum")));


        imgDorf = new CardImageView((getCard(list,"Dorf")), CardImageView.CardSize.miniSize, this);
        gp.setConstraints(imgDorf, 3, 5);
        gp.setRowSpan(imgDorf, 3);
        gp.getChildren().add(imgDorf);

        Tooltip ttDorf = new Tooltip();
        CardImageView imgttDorf = new CardImageView((getCard(list,"Dorf")), CardImageView.CardSize.tooltip, this);
        ttDorf.setGraphic(imgttDorf);
        Tooltip.install(imgDorf, ttDorf);

        lblDorf = new Label();
        gp.setConstraints(lblDorf, 3, 5);
        setLabelFormat(lblDorf);
        lblDorf.setText(Integer.toString(countCards(list, "Dorf")));


        imgGarten = new CardImageView((getCard(list,"Garten")), CardImageView.CardSize.miniSize, this);
        gp.setConstraints(imgGarten, 5, 5);
        gp.setRowSpan(imgGarten, 3);
        gp.getChildren().add(imgGarten);

        Tooltip ttGarten = new Tooltip();
        CardImageView imgttGarten = new CardImageView((getCard(list,"Garten")), CardImageView.CardSize.tooltip, this);
        ttGarten.setGraphic(imgttGarten);
        Tooltip.install(imgGarten, ttGarten);

        lblGarten = new Label();
        gp.setConstraints(lblGarten, 5, 5);
        setLabelFormat(lblGarten);
        lblGarten.setText(Integer.toString(countCards(list, "Garten")));


        imgGeldverleiher = new CardImageView((getCard(list,"Geldverleiher")), CardImageView.CardSize.miniSize, this);
        gp.setConstraints(imgGeldverleiher, 6, 5);
        gp.setRowSpan(imgGeldverleiher, 3);
        gp.getChildren().add(imgGeldverleiher);

        Tooltip ttGeldverleiher = new Tooltip();
        CardImageView imgttGeldverleiher = new CardImageView((getCard(list,"Geldverleiher")), CardImageView.CardSize.tooltip, this);
        ttGeldverleiher.setGraphic(imgttGeldverleiher);
        Tooltip.install(imgGeldverleiher, ttGeldverleiher);

        lblGeldverleiher = new Label();
        gp.setConstraints(lblGeldverleiher, 6, 5);
        setLabelFormat(lblGeldverleiher);
        lblGeldverleiher.setText(Integer.toString(countCards(list, "Geldverleiher")));


        imgHolzfäller = new CardImageView((getCard(list,"Holzfäller")), CardImageView.CardSize.miniSize, this);
        gp.setConstraints(imgHolzfäller, 4, 5);
        gp.setRowSpan(imgHolzfäller, 3);
        gp.getChildren().add(imgHolzfäller);

        Tooltip ttHolzfäller = new Tooltip();
        CardImageView imgttHolzfäller = new CardImageView((getCard(list,"Holzfäller")), CardImageView.CardSize.tooltip, this);
        ttHolzfäller.setGraphic(imgttHolzfäller);
        Tooltip.install(imgHolzfäller, ttHolzfäller);

        lblHolzfäller = new Label();
        gp.setConstraints(lblHolzfäller, 4, 5);
        setLabelFormat(lblHolzfäller);
        lblHolzfäller.setText(Integer.toString(countCards(list, "Holzfäller")));


        imgJahrmarkt = new CardImageView((getCard(list,"Jahrmarkt")), CardImageView.CardSize.miniSize, this);
        gp.setConstraints(imgJahrmarkt, 5, 2);
        gp.setRowSpan(imgJahrmarkt, 3);
        gp.getChildren().add(imgJahrmarkt);

        Tooltip ttJahrmarkt = new Tooltip();
        CardImageView imgttJahrmarkt = new CardImageView((getCard(list,"Jahrmarkt")), CardImageView.CardSize.tooltip, this);
        ttJahrmarkt.setGraphic(imgttJahrmarkt);
        Tooltip.install(imgJahrmarkt, ttJahrmarkt);

        lblJahrmarkt = new Label();
        gp.setConstraints(lblJahrmarkt, 5, 2);
        setLabelFormat(lblJahrmarkt);
        lblJahrmarkt.setText(Integer.toString(countCards(list, "Jahrmarkt")));


        imgLaboratorium = new CardImageView((getCard(list,"Laboratorium")), CardImageView.CardSize.miniSize, this);
        gp.setConstraints(imgLaboratorium, 4, 2);
        gp.setRowSpan(imgLaboratorium, 3);
        gp.getChildren().add(imgLaboratorium);

        Tooltip ttLaboratorium = new Tooltip();
        CardImageView imgttLaboratorium = new CardImageView((getCard(list,"Laboratorium")), CardImageView.CardSize.tooltip, this);
        ttLaboratorium.setGraphic(imgttLaboratorium);
        Tooltip.install(imgLaboratorium, ttLaboratorium);

        lblLaboratorium = new Label();
        gp.setConstraints(lblLaboratorium, 4, 2);
        setLabelFormat(lblLaboratorium);
        lblLaboratorium.setText(Integer.toString(countCards(list, "Laboratorium")));


        imgSchmiede = new CardImageView((getCard(list,"Schmiede")), CardImageView.CardSize.miniSize, this);
        gp.setConstraints(imgSchmiede, 3, 2);
        gp.setRowSpan(imgSchmiede, 3);
        gp.getChildren().add(imgSchmiede);

        Tooltip ttSchmiede = new Tooltip();
        CardImageView imgttSchmiede = new CardImageView((getCard(list,"Schmiede")), CardImageView.CardSize.tooltip, this);
        ttSchmiede.setGraphic(imgttSchmiede);
        Tooltip.install(imgSchmiede, ttSchmiede);

        lblSchmiede = new Label();
        gp.setConstraints(lblSchmiede, 3, 2);
        setLabelFormat(lblSchmiede);
        lblSchmiede.setText(Integer.toString(countCards(list, "Schmiede")));


        imgMarkt = new CardImageView((getCard(list,"Markt")), CardImageView.CardSize.miniSize, this);
        gp.setConstraints(imgMarkt, 6, 2);
        gp.setRowSpan(imgMarkt, 3);
        gp.getChildren().add(imgMarkt);

        Tooltip ttMarkt = new Tooltip();
        CardImageView imgttMarkt = new CardImageView((getCard(list,"Markt")), CardImageView.CardSize.tooltip, this);
        ttMarkt.setGraphic(imgttMarkt);
        Tooltip.install(imgMarkt, ttMarkt);

        lblMarkt = new Label();
        gp.setConstraints(lblMarkt, 6, 2);
        setLabelFormat(lblMarkt);
        lblMarkt.setText(Integer.toString(countCards(list, "Markt")));




    }


    public int countCards(ArrayList<Card> list, String s) {
        int count = 0;
        for (int i = 0; i < (list.size());i++ ){
            if (list.get(i).getName().equals(s)){
                count++;
            }
        }
        return count;
    }

    public Card getCard(ArrayList<Card> list, String name) {
        Card c = null;
        for (int i = 0; i < (list.size());i++ ) {
            if (list.get(i).getName().equals(name)) {
               c = list.get(i);
               break;
            }
        }
        return c;
    }



}
