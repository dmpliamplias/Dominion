package game;

import com.weddingcrashers.service.Language;
import controls.CardImageView;
import controls.HandStackLayout;
import base.View;
import com.weddingcrashers.businessmodels.Card;
import com.weddingcrashers.businessmodels.PlayerSet;
import com.weddingcrashers.servermodels.WinningInformation;
import javafx.collections.FXCollections;
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
import org.apache.commons.collections.map.LinkedMap;

import java.util.ArrayList;
import java.util.Map;

import static javafx.stage.Modality.WINDOW_MODAL;
import static util.StyleSheetPath.GAME;


public class GameView extends View<GameModel> {

    private static final String BASE_PATH = "/game";

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
    protected HandStackLayout hs;
    protected HandStackLayout cardPlayingArea;
    protected ArrayList<CardImageView> handStackList = new ArrayList<CardImageView>();
    protected ArrayList<CardImageView> cardPlayingAreaList = new ArrayList<CardImageView>();
    protected ArrayList<String> EmptyCard = new ArrayList<String>();
    protected Label lblPullStack;
    protected Button btnPlayMoneyCards;
    protected Button btnEndTurn;
    protected Button btnEndActionPhase;
    protected ImageView imgVGreyOutButton;
    protected ImageView imgVGreyOutHandStack;
    protected Label txtLogger2;
    protected Text txtLoggerIsYourTurn;
    protected Text txtLoggerTurnIsOver;
    protected Label lblAction;
    protected Label lblBuy;
    protected Label lblTreasure;
    protected Text txtAction;
    protected Text txtBuy;
    protected Text txtTreasure;
    protected Image imgGreyOut;
    protected String points;
    protected ImageView imgVtrayStack;

public GameView() {

}
    public GameView(Stage stage, GameModel model) {
        super(stage, model);
    }

    /**
     * @author Vanessa Cajochen
     */
    public Scene create_GUI() {

        root = new BorderPane();
        FlowPane fpCenter = new FlowPane();
        fpCenter.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 1300, 650);
        setStylesheet(scene, GAME);
        gp = new GridPane();
        VBox vb = new VBox();
        fpCenter.getChildren().add(gp);
        gp.add(vb, 2, 14);


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
        Image pullStack = new Image(getClass().getResourceAsStream(BASE_PATH + "/back.jpg"));
        ImageView imgVpullStack = new ImageView(pullStack);
        imgVpullStack.setFitHeight(120);
        imgVpullStack.setFitWidth(75);
        GridPane.setRowSpan(imgVpullStack, 4);
        GridPane.setConstraints(imgVpullStack, 1, 15);
        gp.getChildren().add(imgVpullStack);
        lblPullStack = new Label();
        setLabelFormat(lblPullStack);
        GridPane.setConstraints(lblPullStack, 1, 15);


        // Set backside of trashStack
        setBackCardOfTrashStack();

        // Creates Curly Brackets for PlayingArea
        Image imgBracketLeft = new Image(getClass().getResourceAsStream(BASE_PATH + "/curlyBracketLeft.png"));
        ImageView imgVBracketLeft = new ImageView(imgBracketLeft);
        imgVBracketLeft.setFitHeight(120);
        imgVBracketLeft.setPreserveRatio(true);
        GridPane.setConstraints(imgVBracketLeft, 1, 8);
        GridPane.setRowSpan(imgVBracketLeft, 4);
        GridPane.setValignment(imgVBracketLeft, VPos.TOP);
        GridPane.setHalignment(imgVBracketLeft, HPos.RIGHT);
        gp.getChildren().add(imgVBracketLeft);

        Image imgBracketRight = new Image(getClass().getResourceAsStream(BASE_PATH + "/curlyBracketRight.png"));
        ImageView imgVBracketRight = new ImageView(imgBracketRight);
        imgVBracketRight.setFitHeight(120);
        imgVBracketRight.setPreserveRatio(true);
        GridPane.setConstraints(imgVBracketRight, 8, 8);
        GridPane.setRowSpan(imgVBracketRight, 4);
        GridPane.setValignment(imgVBracketRight, VPos.TOP);
        GridPane.setHalignment(imgVBracketRight, HPos.LEFT);
        gp.getChildren().add(imgVBracketRight);


        // PaneLayout for Hand and PlayingArea
        hs = new HandStackLayout();
        gp.getChildren().add(hs);
        GridPane.setConstraints(hs, 2, 15);
        GridPane.setRowSpan(hs, 4);

        cardPlayingArea = new HandStackLayout();
        gp.getChildren().add(cardPlayingArea);
        GridPane.setConstraints(cardPlayingArea, 2, 8);
        GridPane.setRowSpan(cardPlayingArea, 4);


        // Creates 3 buttons. But only 1 will be shown at the same time
        btnEndActionPhase = new Button();
        setStyleOfButtons(btnEndActionPhase);

        btnPlayMoneyCards = new Button();
        setStyleOfButtons(btnPlayMoneyCards);

        btnEndTurn = new Button();
        setStyleOfButtons(btnEndTurn);

        gp.getChildren().add(btnEndActionPhase);


        // Creates 3 Labels and Text to show number of Actions, Buys and Treasure

        lblAction = new Label();
        setLabelStyle(lblAction);
        GridPane.setConstraints(lblAction, 1, 8);

        lblBuy = new Label();
        setLabelStyle(lblBuy);
        GridPane.setConstraints(lblBuy, 1, 10);

        lblTreasure = new Label();
        setLabelStyle(lblTreasure);
        GridPane.setConstraints(lblTreasure, 1, 12);

        txtAction = new Text();
        setTextStyle(txtAction);
        GridPane.setConstraints(txtAction, 1, 7);

        txtBuy = new Text();
        setTextStyle(txtBuy);
        GridPane.setConstraints(txtBuy, 1, 9);

        txtTreasure = new Text();
        setTextStyle(txtTreasure);
        GridPane.setConstraints(txtTreasure, 1, 11);


        // Images for greyOut. If it is not the users'turn, the handcards and button are grayed out.

        imgGreyOut = new Image(getClass().getResourceAsStream(BASE_PATH + "/grey.png"));

        imgVGreyOutHandStack = new ImageView(imgGreyOut);
        imgVGreyOutHandStack.setFitHeight(120);
        imgVGreyOutHandStack.setFitWidth(540);
        GridPane.setRowSpan(imgVGreyOutHandStack, 6);
        GridPane.setColumnSpan(imgVGreyOutHandStack, 4);
        GridPane.setValignment(imgVGreyOutHandStack, VPos.TOP);
        imgVGreyOutHandStack.setOpacity(0.5);

        imgVGreyOutButton = new ImageView(imgGreyOut);
        imgVGreyOutButton.setFitHeight(30);
        imgVGreyOutButton.setFitWidth(200);
        GridPane.setColumnSpan(imgVGreyOutButton, 2);
        GridPane.setHalignment(imgVGreyOutButton, HPos.LEFT);
        imgVGreyOutButton.setOpacity(0.5);

        GridPane.setConstraints(imgVGreyOutHandStack, 2, 15);
        GridPane.setConstraints(imgVGreyOutButton, 4, 13);


        this.btnLobby = new Button();
        this.btnRanking = new Button();

        this.txtLogger = new Label();
        this.txtLogger2 = new Label();

        this.txtLoggerIsYourTurn = new Text();
        this.txtLoggerTurnIsOver = new Text();

        //root.setBottom.
        root.setCenter(fpCenter);
        root.setRight(loggerAndChat());
        root.setTop(setTop());
        root.setLeft(setLeft());


        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setTitle("Dominion");


        this.stage.setResizable(true);
        return scene;
    }


    /**
     * author Manuel Wirz
     * Method for creating title and showing game end option
     * Return a VBox
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

        this.setTop.getChildren().addAll(menuBar, title, endOption);

        menuBar.getMenuSettings().getItems().remove(menuBar.getMenuLanguage());

        return setTop;
    }


    /**
     *  author Manuel Wirz
     *  Method for creating showing actual round and the players with the points
     *  Return a VBox
     *  */

    public VBox setLeft() {
        this.VBoxPointsandPlayer = new VBox();
        this.VBoxPointsandPlayer.setSpacing(20);
        this.txtFieldShowRound = new TextField();
        this.txtFieldShowRound.setEditable(false);
        this.txtFieldShowRound.setPrefSize(150, 20);
        this.txtShowRound = new Text();
        this.VBoxPointsandPlayer.setAlignment(Pos.CENTER);
        this.VBoxPointsandPlayer.getChildren().add(txtFieldShowRound);
        return VBoxPointsandPlayer;
    }


    /**
     * author Manuel Wirz
     * Method for creating Logger and Chat Area
     * Returns a VBox
     */

    public VBox loggerAndChat() {

        this.VBoxLogger = new VBox(10);
        this.VBoxLogger.setPrefSize(300, 150);
        this.VBoxLogger.setAlignment(Pos.CENTER_LEFT);
        this.VBoxLogger.setPadding(new Insets(0, 10, 0, 0));

        this.loggerContent = new VBox();
        this.textFieldNameLogger = new TextField();
        this.textFieldNameLogger.setPrefWidth(400);


        this.scrollPaneLogger = new ScrollPane();
        this.scrollPaneLogger.setPrefSize(300, 200);
        this.scrollPaneLogger.setContent(loggerContent);
        this.scrollPaneLogger.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPaneLogger.vvalueProperty().bind(loggerContent.heightProperty());

        VBoxLogger.getChildren().addAll(textFieldNameLogger, scrollPaneLogger, addChatGridPane());

        return VBoxLogger;

    }


    /**
     * author Manuel Wirz
     * Set LoggerText into a Label to add color for each Client
     */

    protected void setLoggerContent(String msg, Color color) {

        Label lbl = new Label();
        lbl.setText(msg);
        lbl.setTextFill(color);
        this.loggerContent.getChildren().add(lbl);

    }

    /**
     * @author Vanessa Cajochen
     */


    private void setLabelFormat(Label lbl) {
        gp.getChildren().add(lbl);
        GridPane.setValignment(lbl, VPos.TOP);
        lbl.getStyleClass().add("labelNumber");
        lbl.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-border-color: black; -fx-font-size: 10;");
        lbl.setPrefSize(16, 15);
        lbl.setAlignment(Pos.CENTER);
    }

    private void setLabelStyle(Label lbl) {
        lbl.setPrefSize(30, 30);
        lbl.getStyleClass().add("labelInfo");
        lbl.setAlignment(Pos.CENTER);
        gp.getChildren().addAll(lbl);
        GridPane.setHalignment(lbl, HPos.CENTER);
    }

    private void setTextStyle(Text txt) {
        gp.getChildren().addAll(txt);
        txt.getStyleClass().add("textInfo");
        GridPane.setHalignment(txt, HPos.CENTER);
    }

    private void setStyleOfButtons(Button btn) {
        btn.setPrefSize(200, 30);
        GridPane.setColumnSpan(btn, 3);
        btn.getStyleClass().add("buttonGameView");
        GridPane.setConstraints(btn, 4, 13);
        GridPane.setHalignment(btn, HPos.LEFT);
    }


    /**
     * author Manuel Wirz
     * Method for creating chat area and buttons
     * Returns a GridPane
     */

    public GridPane addChatGridPane() {

        chatPane = new GridPane();
        chatPane.setPrefSize(800, 350);
        chatContent = new VBox();

        //GridPane Layout

        chatPane.setAlignment(Pos.BOTTOM_RIGHT);
        chatPane.setHgap(10);
        chatPane.setVgap(10);
        chatPane.setPadding(new Insets(0, 0, 0, 0));


        // ----- Chatview -------

        this.btnChatSend = new Button();
        this.btnChatSend.setPrefSize(330, 50);
        this.btnChatSend.getStyleClass().add("buttonChat");

        this.textFieldChat = new TextField();
        this.textFieldChat.setPromptText("Enter Text");
        this.textFieldChat.setPrefSize(330, 50);

        this.txtNameChat = new TextField();
        this.txtNameChat.setEditable(false);
        this.txtNameChat.setMaxWidth(900);


        this.btnSendText = new Button();
        this.btnSendText.setPrefSize(330, 50);
        this.btnSendText.getStyleClass().add("buttonChat");


        // Create HBox

        hbChat = new HBox(10);
        hbChat.setMaxSize(700, 200);
        hbChat.setAlignment(Pos.CENTER);
        hbChat.getChildren().add(btnChatSend);
        hbChat.getChildren().add(btnSendText);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(chatContent);
        scroll.setPrefSize(800, 150);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.vvalueProperty().bind(chatContent.heightProperty());
        chatPane.add(txtNameChat, 0, 0);
        chatPane.add(scroll, 0, 1);
        chatPane.add(hbChat, 0, 3);
        chatPane.add(textFieldChat, 0, 2);

        return chatPane;
    }


    /**
     * @param userId
     * @param userName
     * @param set
     * @param activeUserId here you see the points in the view.
     * @author Murat Kelleci
     *  here are the buttons created depending on player. two buttons if 2 player etc.
     *  the player who has the turn his buttons is marked greend and border is bold
     */
    public void setUserPoints(int userId, String userName, PlayerSet set, int activeUserId) {
        String elementId = "UserPoints_" + userId;
        String text = userName + ": \n" + Integer.toString(set.calculatePoints()) + " " + points;
        String activeUser = "UserPoints_" + activeUserId;

        Node elm = VBoxPointsandPlayer.lookup("#" + elementId);
        Label lblUserNameAndPoints;

        if (elm == null) {
            // element does not exist yet
            lblUserNameAndPoints = new Label();
            lblUserNameAndPoints.getStyleClass().add("labelShowStats");
            lblUserNameAndPoints.setPrefSize(150, 50);
            lblUserNameAndPoints.setText(text);
            lblUserNameAndPoints.setAlignment(Pos.CENTER_LEFT);
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
            lblUserNameAndPoints.setStyle("-fx-border-color: black; -fx-border-width: 1");
        }


    }

    /**
     *
     * @author Murat Kelleci
     * @param gameResult
     * here is the winnerstage
     */

    public void startWinnerStage(LinkedMap gameResult, Map.Entry<WinningInformation, GameResult> myResult) {
        displayWinnerDialog(myResult);

        BorderPane root = new BorderPane();
        this.stageDialog = new Stage();
        this.stageDialog.setOnCloseRequest(evt -> {
            // prevent window from closing
            evt.consume();
        });
        stageDialog.initOwner(stage);
        stageDialog.initModality(WINDOW_MODAL);
        Scene scene = new Scene(root, 400, 300);
        this.stageDialog.setScene(scene);
        final ObservableList<WinningInformation> winningInformations = FXCollections.observableArrayList();
        winningInformations.addAll(gameResult.keySet());
        createVBox(winningInformations);
        root.setCenter(VBoxDisplayWinner);
        setTextDialog();
        stageDialog.show();
    }

    protected void setTextDialog() {
        this.btnLobby.setText(getText("btn.Lobby"));
        this.btnRanking.setText(getText("btn.Ranking"));
    }

    /**
     *
     * @author Murat Kelleci
     * @param gameResult
     * here is shown the display for each player. the winner will get the winner pic, draw the draw pic and dialog and the loser the loser pic and dialog.
     * here is also the pic defined depending on win, draw or lose and the same for the sound which is also individual.
     */

    private void displayWinnerDialog(Map.Entry<WinningInformation, GameResult> gameResult) {
        StackPane stPa = new StackPane();
        ImageView imgVbackground = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/dialogBackground.jpg")));
        imgVbackground.setPreserveRatio(true);
        imgVbackground.setFitHeight(250);

        switch (gameResult.getValue()) {

            case WIN:
                Alert winnerAlert = new Alert(Alert.AlertType.INFORMATION);
                String winner = "winner";
                ImageView imgVconfetti = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/confetti.gif")));
                ImageView imgVwinnerPic;
                if (translator.getCurrentLanguage() == Language.ENGLISH) {
                    imgVwinnerPic = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/winner_EN.PNG")));
                } else if (translator.getCurrentLanguage() == Language.SWISS_GERMAN){
                    imgVwinnerPic = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/winner_CH.PNG")));
                } else {
                    imgVwinnerPic = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/winner_DE.PNG")));
                }
                imgVwinnerPic.setPreserveRatio(true);
                imgVwinnerPic.setFitHeight(60);
                stPa.getChildren().addAll(imgVconfetti, imgVwinnerPic);
                winnerAlert.getDialogPane().setContent(stPa);
                winnerAlert.setGraphic(null);
                winnerAlert.setHeaderText("");
                winnerAlert.setTitle("Winner Dialog");
                if (menuBar.getMenuItemSoundUnmute().isSelected()) {
                    GameController.playSound(winner);
                }
                winnerAlert.showAndWait();
                break;
            case DRAW:
                Alert drawAlert = new Alert(Alert.AlertType.INFORMATION);
                String draw = "draw";
                ImageView imgVdrawPic;
                if (translator.getCurrentLanguage() == Language.ENGLISH) {
                    imgVdrawPic = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/draw_EN.PNG")));
                } else if (translator.getCurrentLanguage() == Language.SWISS_GERMAN){
                    imgVdrawPic = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/draw_CH.PNG")));
                } else {
                    imgVdrawPic = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/draw_DE.PNG")));
                }
                imgVdrawPic.setPreserveRatio(true);
                imgVdrawPic.setFitHeight(50);
                stPa.getChildren().addAll(imgVbackground, imgVdrawPic);
                drawAlert.getDialogPane().setContent(stPa);
                drawAlert.setGraphic(null);
                drawAlert.setHeaderText("");
                drawAlert.setTitle("Draw Dialog");
                if (menuBar.getMenuItemSoundUnmute().isSelected()) {
                    GameController.playSound(draw);
                }
                drawAlert.showAndWait();
                break;
            case LOSE:
                Alert loserAlert = new Alert(Alert.AlertType.INFORMATION);
                String loser = "loser";
                ImageView imgVloserPic;
                if (translator.getCurrentLanguage() == Language.ENGLISH) {
                    imgVloserPic = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/loser_EN.PNG")));
                } else if (translator.getCurrentLanguage() == Language.SWISS_GERMAN){
                    imgVloserPic = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/loser_CH.PNG")));
                } else {
                    imgVloserPic = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/loser_DE.PNG")));
                }
                stPa.getChildren().addAll(imgVbackground, imgVloserPic);
                imgVloserPic.setPreserveRatio(true);
                imgVloserPic.setFitHeight(50);
                loserAlert.getDialogPane().setContent(stPa);
                loserAlert.setGraphic(null);
                loserAlert.setHeaderText("");
                loserAlert.setTitle("Loser Dialog");

                if (menuBar.getMenuItemSoundUnmute().isSelected()) {
                    GameController.playSound(loser);
                }
                loserAlert.showAndWait();
        }
    }


    /**
     * @param winningInformations
     * @author Murat Kelleci
     * here is the Vbox and Tableview for WinningUser created.
     */
    public void createVBox(ObservableList<WinningInformation> winningInformations) {
        this.btnLobby.setPrefSize(180, 80);
        this.btnRanking.setPrefSize(180, 80);
        this.VBoxDisplayWinner = new VBox();
        TableView<WinningInformation> tableView = createWinningUserTableView(winningInformations);
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(btnLobby, btnRanking);

        VBoxDisplayWinner.getChildren().addAll(tableView, hbox);
    }

    private TableView<WinningInformation> createWinningUserTableView(ObservableList<WinningInformation> winningInformations) {
        TableView<WinningInformation> tableView = new TableView<>();

        TableColumn<WinningInformation, String> position = new TableColumn<>(getText("gameview.winningInformations.position"));
        TableColumn<WinningInformation, String> name = new TableColumn<>(getText("gameview.winningInformations.name"));
        TableColumn<WinningInformation, String> points = new TableColumn<>(getText("gameview.winningInformations.points"));

        tableView.getColumns().addAll(position, name, points);

        tableView.setItems(winningInformations);

        name.setCellValueFactory(new PropertyValueFactory<>("username"));
        points.setCellValueFactory(new PropertyValueFactory<>("points"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));

        return tableView;
    }

    /**
     * @author Michel Schlatter + Manuel Wirz
     * @param msg
     * @param color
     */
    protected void setChatMessage(String msg, Color color) {
        Label lbl = new Label();
        lbl.setText(msg);
        lbl.setTextFill(color);
        this.chatContent.getChildren().add(lbl);
    }

    /**
     * @author Vanessa Cajochen + Manuel Wirz
     */

    protected void setTexts() {
        this.btnChatSend.setText(getText("chat.send"));
        this.btnSendText.setText(getText("chat.nice!"));
        this.txtNameChat.setText(getText("chat.chat"));
        this.endOptionRounds.setText(getText("gameview.endOptionRound"));
        this.endOptionPoints.setText(getText("gameview.endOptionPoint"));
        this.textFieldNameLogger.setText(getText("gameview.logger"));
        this.txtLogger.setText(getText("gameview.loggerText"));
        this.txtLogger2.setText(getText("gameview.loggerText2"));
        this.txtShowRound.setText(getText("gameview.showRound"));
        this.txtLoggerTurnIsOver.setText(getText("gameview.LoggerTurnIsOVer"));
        this.txtLoggerIsYourTurn.setText(getText("gameview.LoggerIsYourTurn"));
        this.txtAction.setText(getText("gameview.action"));
        this.txtBuy.setText(getText("gameview.buy"));
        this.txtTreasure.setText(getText("gameview.money"));

        this.btnEndActionPhase.setText(getText("gameview.endActionPhase"));
        this.btnPlayMoneyCards.setText(getText("gameview.playTreasure"));
        this.btnEndTurn.setText(getText("gameview.endTurn"));
        this.points = getText("gameview.points");


    }

    public void start() {
        stage.show();
    }

    public void stop() {
        stage.hide();
    }

    /**
     * @author Vanessa Cajochen
     */
    public CardImageView setCardImageView(Card card, CardImageView.CardSize size, int col, int row, int rowSpan, int cardCount) {

        if (card == null) {
            if (EmptyCard.isEmpty() || !EmptyCard.contains(""+col+row)){
                EmptyCard.add("" + col + row);
                ImageView imgVEmptyCard = new ImageView(imgGreyOut);
                imgVEmptyCard.setOpacity(0.5);
                GridPane.setConstraints(imgVEmptyCard, col, row);
                GridPane.setRowSpan(imgVEmptyCard, rowSpan);
                gp.getChildren().add(imgVEmptyCard);

                if (size == CardImageView.CardSize.miniMini) {
                    imgVEmptyCard.setFitWidth(60);
                    imgVEmptyCard.setFitHeight(57.476);
                } else if (size == CardImageView.CardSize.miniSize) {
                    imgVEmptyCard.setFitWidth(90);
                    imgVEmptyCard.setFitHeight(86.214);
                }
            }

            Label lbl = new Label();
            GridPane.setConstraints(lbl, col, row);
            setLabelFormat(lbl);
            lbl.setText(Integer.toString(cardCount));


            return null;

        } else {
            CardImageView imgView = new CardImageView(card, size);

            GridPane.setConstraints(imgView, col, row);
            GridPane.setRowSpan(imgView, rowSpan);
            gp.getChildren().add(imgView);

            Tooltip tooltip = new Tooltip();
            CardImageView imgForToolTip = new CardImageView(card, CardImageView.CardSize.tooltip);
            tooltip.setGraphic(imgForToolTip);
            Tooltip.install(imgView, tooltip);

            Label lbl = new Label();
            GridPane.setConstraints(lbl, col, row);
            setLabelFormat(lbl);
            lbl.setText(Integer.toString(cardCount));

            return imgView;
        }
    }

    // Adds the card to the HandStackPane.
    public void addCardToHandStackPane(CardImageView cardImg) {
        handStackList.add(cardImg);
        updateStackLayout();
        hs.getChildren().add(cardImg);
    }

    // Moves card from the HandStackPane to the cardPlayingArea.
    public void moveCardToPlayingArea(CardImageView cardImg) {
        handStackList.remove(cardImg);
        hs.getChildren().remove(cardImg);
        cardPlayingAreaList.add(cardImg);
        updateStackLayout();
        cardPlayingArea.getChildren().add(cardImg);
    }


    // Moves all MoneyCards with one click on the button
    public void moveMoneyCardsToPlayArea() {
        ArrayList<CardImageView> moveList = new ArrayList<CardImageView>();
        for (int i = 0; i < handStackList.size(); i++) {
            if (handStackList.get(i).getCard().getName().equals("Kupfer") || handStackList.get(i).getCard().getName().equals("Silber") || handStackList.get(i).getCard().getName().equals("Gold")) {
                moveList.add(handStackList.get(i));
            }
        }
        for (int i = 0; i < moveList.size(); i++) {
            moveCardToPlayingArea(moveList.get(i));
        }

    }

    // If there are more than 5 cards in the PlayingArea / HandCardArea the gap will between the cards gets smaller
    // 1 card has has a width of 75. 540 ist the maximum length of the Pane. 540 - 75 = 465.
    public void updateStackLayout() {
        if (handStackList.size() > 5) {
            hs.setCardInterval(465 / (handStackList.size() - 1));
        } else {
            hs.setCardInterval(116.25);
        }

        if (cardPlayingAreaList.size() > 5) {
            cardPlayingArea.setCardInterval(465 / (cardPlayingAreaList.size() - 1));
        } else {
            cardPlayingArea.setCardInterval(116.25);
        }
    }


    // Updates the Action, Buy and Money Number
    public void updatelblInfo(int action, int buys, int money) {
        lblAction.setText("" + action);
        lblBuy.setText("" + buys);
        lblTreasure.setText("" + money);
    }


    // Clear HandStack and CardPlayingArea
    public void endOfTurn() {
        hs.getChildren().clear();
        cardPlayingArea.getChildren().clear();
        handStackList.clear();
        cardPlayingAreaList.clear();
    }

    // The number of cards in the pullStack gets updated
    public void updateLblPullStack(int numberOfPullStack) {
        lblPullStack.setText("" + numberOfPullStack);
    }


    // When card "Geldverleiher" is played, a Copper from the handStack is trashed.
    public void trashCopper() {
        boolean found = false;
        int i = 0;
        while (!found) {
            if (handStackList.get(i).getCard().getName().equals("Kupfer")) {
                hs.getChildren().remove(handStackList.get(i));
                handStackList.remove(i);
                found = true;
            }
            i++;
        }
    }

    // The last played card from the handStack lies on the trashStack
    public void setBackCardOfTrashStack(Card card) {
        imgVtrayStack = new CardImageView(card, CardImageView.CardSize.bigSize);
        imgVtrayStack.setFitHeight(120);
        imgVtrayStack.setFitWidth(75);
        GridPane.setRowSpan(imgVtrayStack, 4);
        GridPane.setHalignment(imgVtrayStack, HPos.LEFT);
        GridPane.setConstraints(imgVtrayStack, 0, 15);
        gp.getChildren().add(imgVtrayStack);
    }

    // If the trashStack is empty, the trashStack shows the back of the card.
    public void setBackCardOfTrashStack() {
        imgVtrayStack = new ImageView(new Image(getClass().getResourceAsStream(BASE_PATH + "/back.jpg")));
        imgVtrayStack.setFitHeight(120);
        imgVtrayStack.setFitWidth(75);
        GridPane.setRowSpan(imgVtrayStack, 4);
        GridPane.setHalignment(imgVtrayStack, HPos.LEFT);
        GridPane.setConstraints(imgVtrayStack, 0, 15);
        gp.getChildren().add(imgVtrayStack);
    }

    // Grey img gets removed and Player sees that he can play now
    public void disableView() {
        gp.getChildren().removeAll(imgVGreyOutHandStack, imgVGreyOutButton);
    }

    // HandCards and Button gets greyedout. Player can no longer play cards and click Button
    public void enableView() {
        if (!gp.getChildren().contains(imgVGreyOutHandStack)) {
            gp.getChildren().addAll(imgVGreyOutHandStack, imgVGreyOutButton);
        }
    }


    /**
     *  Getters and Setters
     *  */

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

    public Button getBtnRanking() {
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
