package Game;

import Controls.CardImageView;
import base.View;
import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.MoneyCard;
import com.weddingcrashers.businessmodels.MoneyType;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import java.io.File;




public class GameView extends View<GameModel> {


     protected TextField textFieldChat;
     protected Button btnChatSend;
     protected Button btnSendText;
     protected GridPane chatPane;
     protected HBox hbChat;
     protected VBox chatContent;
     protected TextField txtNameChat;

    public GameView(Stage stage, GameModel model){
        super(stage,model);
    }

    /**
     *  author Vanessa Cajochen
     *  */

    public Scene create_GUI(){
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1000, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("/Game/GameView.css").toExternalForm());
        GridPane gp = new GridPane();
        root.setCenter(gp);


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

        MoneyCard mc1 = new MoneyCard();
        mc1.setMoneyType(MoneyType.Copper);
        mc1.setValue(1);
        mc1.setCost(0);
        mc1.setName("Kupfer");
        mc1.setFilePath("kupfer_{0}.png");

        CardImageView imgKupfer=new CardImageView(mc1, CardImageView.CardSize.miniSize);
        gp.setConstraints(imgKupfer, 2, 2);
        gp.setRowSpan(imgKupfer, 2);
        /**Image kupfer = new Image(getClass().getResourceAsStream("\\mini\\kupfer_DE.png"));
        ImageView imgVkupfer = new ImageView(kupfer);
        imgVkupfer.setFitHeight(60);
        imgVkupfer.setFitWidth(70);
        gp.setConstraints(imgVkupfer, 2, 2);
        gp.setRowSpan(imgVkupfer, 2);*/

        Image silber  = new Image(getClass().getResourceAsStream("\\mini\\silber_DE.png"));
        ImageView imgVsilber = new ImageView(silber);
        imgVsilber.setFitHeight(60);
        imgVsilber.setFitWidth(70);
        gp.setConstraints(imgVsilber, 2, 4);
        gp.setRowSpan(imgVsilber, 2);

        Image gold = new Image(getClass().getResourceAsStream("\\mini\\gold_DE.png"));
        ImageView imgVgold = new ImageView(gold);
        imgVgold.setFitHeight(60);
        imgVgold.setFitWidth(70);
        gp.setConstraints(imgVgold, 2, 6);
        gp.setRowSpan(imgVgold, 2);

        Image schmiede = new Image(getClass().getResourceAsStream("\\mini\\schmiede_DE.png"));
        ImageView imgVschmiede = new ImageView(schmiede);
        imgVschmiede.setFitHeight(90);
        imgVschmiede.setFitWidth(100);
        gp.setConstraints(imgVschmiede, 3, 2);
        gp.setRowSpan(imgVschmiede, 3);

        Image laboratorium = new Image(getClass().getResourceAsStream("\\mini\\laboratorium_DE.png"));
        ImageView imgVlaboratorium = new ImageView(laboratorium);
        imgVlaboratorium.setFitHeight(90);
        imgVlaboratorium.setFitWidth(100);
        gp.setConstraints(imgVlaboratorium, 4, 2);
        gp.setRowSpan(imgVlaboratorium, 3);

        Image jahrmarkt = new Image(getClass().getResourceAsStream("\\mini\\jahrmarkt_DE.png"));
        ImageView imgVjahrmarkt = new ImageView(jahrmarkt);
        imgVjahrmarkt.setFitHeight(90);
        imgVjahrmarkt.setFitWidth(100);
        gp.setConstraints(imgVjahrmarkt, 5, 2);
        gp.setRowSpan(imgVjahrmarkt, 3);

        Image markt = new Image(getClass().getResourceAsStream("\\mini\\markt_DE.png"));
        ImageView imgVmarkt = new ImageView(markt);
        imgVmarkt.setFitHeight(90);
        imgVmarkt.setFitWidth(100);
        gp.setConstraints(imgVmarkt, 6, 2);
        gp.setRowSpan(imgVmarkt, 3);

        Image dorf = new Image(getClass().getResourceAsStream("\\mini\\dorf_DE.png"));
        ImageView imgVdorf = new ImageView(dorf);
        imgVdorf.setFitHeight(90);
        imgVdorf.setFitWidth(100);
        gp.setConstraints(imgVdorf, 3, 5);
        gp.setRowSpan(imgVdorf, 3);

        Image holzfaeller = new Image(getClass().getResourceAsStream("\\mini\\holzfaeller_DE.png"));
        ImageView imgVholzfaeller = new ImageView(holzfaeller);
        imgVholzfaeller.setFitHeight(90);
        imgVholzfaeller.setFitWidth(100);
        gp.setConstraints(imgVholzfaeller, 4, 5);
        gp.setRowSpan(imgVholzfaeller, 3);

        Image gaerten = new Image(getClass().getResourceAsStream("\\mini\\gaerten_DE.png"));
        ImageView imgVgaerten = new ImageView(gaerten);
        imgVgaerten.setFitHeight(90);
        imgVgaerten.setFitWidth(100);
        gp.setConstraints(imgVgaerten, 5, 5);
        gp.setRowSpan(imgVgaerten, 3);

        Image geldverleiher = new Image(getClass().getResourceAsStream("\\mini\\geldverleiher_DE.png"));
        ImageView imgVgeldverleiher = new ImageView(geldverleiher);
        imgVgeldverleiher.setFitHeight(90);
        imgVgeldverleiher.setFitWidth(100);
        gp.setConstraints(imgVgeldverleiher, 6, 5);
        gp.setRowSpan(imgVgeldverleiher, 3);

        Image anwesen = new Image(getClass().getResourceAsStream("\\mini\\anwesen_DE.png"));
        ImageView imgVanwesen = new ImageView(anwesen);
        imgVanwesen.setFitHeight(60);
        imgVanwesen.setFitWidth(70);
        gp.setConstraints(imgVanwesen, 7, 2);
        gp.setRowSpan(imgVanwesen, 2);

        Image herzogtum = new Image(getClass().getResourceAsStream("\\mini\\herzogtum_DE.png"));
        ImageView imgVherzogtum = new ImageView(herzogtum);
        imgVherzogtum.setFitHeight(60);
        imgVherzogtum.setFitWidth(70);
        gp.setConstraints(imgVherzogtum, 7, 4);
        gp.setRowSpan(imgVherzogtum, 2);

        Image provinz = new Image(getClass().getResourceAsStream("\\mini\\provinz_DE.png"));
        ImageView imgVprovinz = new ImageView(provinz);
        imgVprovinz.setFitHeight(60);
        imgVprovinz.setFitWidth(70);
        gp.setConstraints(imgVprovinz, 7, 6);
        gp.setRowSpan(imgVprovinz, 2);

        gp.getChildren().addAll(imgKupfer, imgVsilber, imgVgold, imgVdorf, imgVschmiede, imgVlaboratorium, imgVjahrmarkt, imgVmarkt, imgVgeldverleiher, imgVgaerten, imgVholzfaeller, imgVanwesen, imgVherzogtum, imgVprovinz);


        // root Layout
        root.setBottom(addChatGridPane());
        //TODO Venessa u can use for your GUI
       // root.setCenter();
       // root.setRight();
       // root.setTop();
       // root.setLeft();

        stage.setScene(scene);
        stage.setFullScreen( false );
        stage.setTitle( "Dominion" );

        setTexts();
        return scene;
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
}
