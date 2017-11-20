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
     Label lblGaerten;
     Label lblGeldverleiher;
     Label lblHerzogtum;
     Label lblHolzfaeller;
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
     CardImageView imgGaerten;
     CardImageView imgGeldverleiher;
     CardImageView imgHerzogtum;
     CardImageView imgHolzfaeller;
     CardImageView imgJahrmarkt;
     CardImageView imgMarkt;
     CardImageView imgLaboratorium;
     CardImageView imgProvinz;
     CardImageView imgSchmiede;
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



        Image gold = new Image(getClass().getResourceAsStream("/game/mini/gold_DE.png"));
        ImageView imgVgold = new ImageView(gold);
        imgVgold.setFitHeight(60);
        imgVgold.setFitWidth(70);
        gp.setConstraints(imgVgold, 2, 6);
        gp.setRowSpan(imgVgold, 2);

        Image schmiede = new Image(getClass().getResourceAsStream("/game/mini/schmiede_DE.png"));
        ImageView imgVschmiede = new ImageView(schmiede);
        imgVschmiede.setFitHeight(90);
        imgVschmiede.setFitWidth(100);
        gp.setConstraints(imgVschmiede, 3, 2);
        gp.setRowSpan(imgVschmiede, 3);

        Image laboratorium = new Image(getClass().getResourceAsStream("/game/mini/laboratorium_DE.png"));
        ImageView imgVlaboratorium = new ImageView(laboratorium);
        imgVlaboratorium.setFitHeight(90);
        imgVlaboratorium.setFitWidth(100);
        gp.setConstraints(imgVlaboratorium, 4, 2);
        gp.setRowSpan(imgVlaboratorium, 3);

        Image jahrmarkt = new Image(getClass().getResourceAsStream("/game/mini/jahrmarkt_DE.png"));
        ImageView imgVjahrmarkt = new ImageView(jahrmarkt);
        imgVjahrmarkt.setFitHeight(90);
        imgVjahrmarkt.setFitWidth(100);
        gp.setConstraints(imgVjahrmarkt, 5, 2);
        gp.setRowSpan(imgVjahrmarkt, 3);

        Image markt = new Image(getClass().getResourceAsStream("/game/mini/markt_DE.png"));
        ImageView imgVmarkt = new ImageView(markt);
        imgVmarkt.setFitHeight(90);
        imgVmarkt.setFitWidth(100);
        gp.setConstraints(imgVmarkt, 6, 2);
        gp.setRowSpan(imgVmarkt, 3);

        Image dorf = new Image(getClass().getResourceAsStream("/game/mini/dorf_DE.png"));
        ImageView imgVdorf = new ImageView(dorf);
        imgVdorf.setFitHeight(90);
        imgVdorf.setFitWidth(100);
        gp.setConstraints(imgVdorf, 3, 5);
        gp.setRowSpan(imgVdorf, 3);

        Image holzfaeller = new Image(getClass().getResourceAsStream("/game/mini/holzfaeller_DE.png"));
        ImageView imgVholzfaeller = new ImageView(holzfaeller);
        imgVholzfaeller.setFitHeight(90);
        imgVholzfaeller.setFitWidth(100);
        gp.setConstraints(imgVholzfaeller, 4, 5);
        gp.setRowSpan(imgVholzfaeller, 3);

        Image gaerten = new Image(getClass().getResourceAsStream("/game/mini/gaerten_DE.png"));
        ImageView imgVgaerten = new ImageView(gaerten);
        imgVgaerten.setFitHeight(90);
        imgVgaerten.setFitWidth(100);
        gp.setConstraints(imgVgaerten, 5, 5);
        gp.setRowSpan(imgVgaerten, 3);

        Image geldverleiher = new Image(getClass().getResourceAsStream("/game/mini/geldverleiher_DE.png"));
        ImageView imgVgeldverleiher = new ImageView(geldverleiher);
        imgVgeldverleiher.setFitHeight(90);
        imgVgeldverleiher.setFitWidth(100);
        gp.setConstraints(imgVgeldverleiher, 6, 5);
        gp.setRowSpan(imgVgeldverleiher, 3);

        Image anwesen = new Image(getClass().getResourceAsStream("/game/mini/anwesen_DE.png"));
        ImageView imgVanwesen = new ImageView(anwesen);
        imgVanwesen.setFitHeight(60);
        imgVanwesen.setFitWidth(70);
        gp.setConstraints(imgVanwesen, 7, 2);
        gp.setRowSpan(imgVanwesen, 2);

        Image herzogtum = new Image(getClass().getResourceAsStream("/game/mini/herzogtum_DE.png"));
        ImageView imgVherzogtum = new ImageView(herzogtum);
        imgVherzogtum.setFitHeight(60);
        imgVherzogtum.setFitWidth(70);
        gp.setConstraints(imgVherzogtum, 7, 4);
        gp.setRowSpan(imgVherzogtum, 2);

        Image provinz = new Image(getClass().getResourceAsStream("/game/mini/provinz_DE.png"));
        ImageView imgVprovinz = new ImageView(provinz);
        imgVprovinz.setFitHeight(60);
        imgVprovinz.setFitWidth(70);
        gp.setConstraints(imgVprovinz, 7, 6);
        gp.setRowSpan(imgVprovinz, 2);

        gp.getChildren().addAll(imgVgold, imgVdorf, imgVschmiede, imgVlaboratorium, imgVjahrmarkt, imgVmarkt, imgVgeldverleiher, imgVgaerten, imgVholzfaeller, imgVanwesen, imgVherzogtum, imgVprovinz);


        Image back = new Image(getClass().getResourceAsStream("back.jpg"));
        ImageView imgVback = new ImageView(back);
        imgVback.setFitHeight(150);
        imgVback.setFitWidth(100);
        gp.setConstraints(imgVback, 1, 14);
        gp.setRowSpan(imgVback, 5);

        gp.getChildren().add(imgVback);




        // Create Tooltip
        // ---------------------------------------------------------

        /**
        Image kupferB = new Image(getClass().getResourceAsStream("/game/big/kupfer_DE.png"));
        ImageView imgVkupferB = new ImageView(kupferB);
        imgVkupferB.setFitHeight(300);
        imgVkupferB.setFitWidth(200);
        Tooltip ttKupfer = new Tooltip();
        ttKupfer.setGraphic(imgVkupferB);
        Tooltip.install(imgKupfer, ttKupfer);


        Image silberB = new Image(getClass().getResourceAsStream("/game/big/silber_DE.png"));
        ImageView imgVsilberB = new ImageView(silberB);
        imgVsilberB.setFitHeight(300);
        imgVsilberB.setFitWidth(200);
        Tooltip ttSilber = new Tooltip();
        ttSilber.setGraphic(imgVsilberB);
        Tooltip.install(imgVsilber, ttSilber);
    */

        Tooltip ttGold = new Tooltip();
        Tooltip ttAnwesen = new Tooltip();
        Tooltip ttHerzogtum = new Tooltip();
        Tooltip ttProvinz = new Tooltip();
        Tooltip ttDorf = new Tooltip();
        Tooltip ttGaerten = new Tooltip();
        Tooltip ttGeldverleiher = new Tooltip();
        Tooltip ttHolzfaeller = new Tooltip();
        Tooltip ttJahrmarkt = new Tooltip();
        Tooltip ttLaboratorium = new Tooltip();
        Tooltip ttMarkt = new Tooltip();
        Tooltip ttSchmiede = new Tooltip();


        // ---------------------------------------------------------


        // Label Number of available cards to buy
        // ---------------------------------------------------------

        lblKupfer = new Label();
        gp.setConstraints(lblKupfer, 2, 2);
        setLabelFormat(lblKupfer);

        lblSilber = new Label();
        gp.setConstraints(lblSilber, 2, 4);
        setLabelFormat(lblSilber);

        lblGold = new Label();
        gp.setConstraints(lblGold, 2, 6);
        setLabelFormat(lblGold);

        lblAnwesen = new Label();
        gp.setConstraints(lblAnwesen, 7, 2);
        setLabelFormat(lblAnwesen);

        lblDorf = new Label();
        gp.setConstraints(lblDorf, 3, 5);
        setLabelFormat(lblDorf);

        lblGaerten = new Label();
        gp.setConstraints(lblGaerten, 5, 5);
        setLabelFormat(lblGaerten);

        lblGeldverleiher = new Label();
        gp.setConstraints(lblGeldverleiher, 6, 5);
        setLabelFormat(lblGeldverleiher);

        lblHerzogtum = new Label();
        gp.setConstraints(lblHerzogtum, 7, 4);
        setLabelFormat(lblHerzogtum);

        lblHolzfaeller = new Label();
        gp.setConstraints(lblHolzfaeller, 4, 5);
        setLabelFormat(lblHolzfaeller);

        lblJahrmarkt = new Label();
        gp.setConstraints(lblJahrmarkt, 5, 2);
        setLabelFormat(lblJahrmarkt);

        lblMarkt = new Label();
        gp.setConstraints(lblMarkt, 6, 2);
        setLabelFormat(lblMarkt);

        lblLaboratorium = new Label();
        gp.setConstraints(lblLaboratorium, 4, 2);
        setLabelFormat(lblLaboratorium);

        lblProvinz = new Label();
        gp.setConstraints(lblProvinz, 7, 6);
        setLabelFormat(lblProvinz);

        lblSchmiede = new Label();
        gp.setConstraints(lblSchmiede, 3, 2);
        setLabelFormat(lblSchmiede);

        Label lblNachziehstapel = new Label();
        gp.setConstraints(lblNachziehstapel, 1, 14);
        setLabelFormat(lblNachziehstapel);



        // ---------------------------------------------------------





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

    public void updateUnusedCards(ArrayList<Card> list)
    {
        /*
        imgKupfer = new CardImageView((getCard(list,"Kupfer")), CardImageView.CardSize.miniMini);
        gp.setConstraints(imgKupfer, 2, 2);
        gp.setRowSpan(imgKupfer, 2);
        gp.getChildren().add(imgKupfer);

        lblKupfer = new Label();
        gp.setConstraints(lblKupfer, 2, 2);
        setLabelFormat(lblKupfer);
        lblKupfer.setText(Integer.toString(countCards(list, "Kupfer")));

        */

        imgSilber = new CardImageView((getCard(list,"Silber")), CardImageView.CardSize.miniMini, this);
        gp.setConstraints(imgSilber, 2, 4);
        gp.setRowSpan(imgSilber, 2);
        gp.getChildren().add(imgSilber);

        lblSilber.setText(Integer.toString(countCards(list, "Silber")));

                /*

        imgGold = new CardImageView((getCard(list,"Gold")), CardImageView.CardSize.miniMini);
        gp.setConstraints(imgGold, 2, 6);
        gp.setRowSpan(imgGold, 2);
        gp.getChildren().add(imgGold);

        lblGold.setText(Integer.toString(countCards(list, "Gold")));

        imgAnwesen = new CardImageView((getCard(list,"Anwesen")), CardImageView.CardSize.miniSize);
        gp.setConstraints(imgAnwesen, 7, 2);
        gp.setRowSpan(imgAnwesen, 2);
        gp.getChildren().add(imgAnwesen);

        lblAnwesen.setText(Integer.toString(countCards(list, "Anwesen")));


        lblDorf = new Label();
        gp.setConstraints(lblDorf, 3, 5);
        setLabelFormat(lblDorf);

        lblGaerten = new Label();
        gp.setConstraints(lblGaerten, 5, 5);
        setLabelFormat(lblGaerten);

        lblGeldverleiher = new Label();
        gp.setConstraints(lblGeldverleiher, 6, 5);
        setLabelFormat(lblGeldverleiher);

        lblHerzogtum = new Label();
        gp.setConstraints(lblHerzogtum, 7, 4);
        setLabelFormat(lblHerzogtum);

        lblHolzfaeller = new Label();
        gp.setConstraints(lblHolzfaeller, 4, 5);
        setLabelFormat(lblHolzfaeller);

        lblJahrmarkt = new Label();
        gp.setConstraints(lblJahrmarkt, 5, 2);
        setLabelFormat(lblJahrmarkt);

        lblMarkt = new Label();
        gp.setConstraints(lblMarkt, 6, 2);
        setLabelFormat(lblMarkt);

        lblLaboratorium = new Label();
        gp.setConstraints(lblLaboratorium, 4, 2);
        setLabelFormat(lblLaboratorium);

        lblProvinz = new Label();
        gp.setConstraints(lblProvinz, 7, 6);
        setLabelFormat(lblProvinz);

        lblSchmiede = new Label();
        gp.setConstraints(lblSchmiede, 3, 2);
        setLabelFormat(lblSchmiede);
 */

    }






    public int countCards(ArrayList<Card> list, String s) {
        int count = 0;
        for (int i = 0; i < (list.size()-1);i++ ){
            if (list.get(i).getName().equals(s)){
                count++;
            }
        }
        return count;
    }

    public Card getCard(ArrayList<Card> list, String s) {
        int index = -1;
        for (int i = 0; i < (list.size()-1);i++ ) {
            if (list.get(i).getName().equals(s)) {
                index = i;
            }
        }
        System.out.println(index);
            return list.get(index);

    }



}
