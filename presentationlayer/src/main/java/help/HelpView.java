package help;

import base.View;
import com.weddingcrashers.service.Language;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;

public class HelpView extends View<HelpModel> {

    public HelpView(Stage stage, HelpModel model){
        super(stage, model);
    }

    /**
     * @author Vanessa Cajochen
     */

    public Scene create_GUI(){



        Group root = new Group();
        Scene scene = new Scene(root, 800, 500, Color.WHITE);

        // Creating tabPane and Tabs
        TabPane tabPane = new TabPane();
        Tab tabConnection = new Tab();
        Tab tabLogin = new Tab();
        Tab tabRegister = new Tab();
        Tab tabLobby = new Tab();
        Tab tabGame = new Tab();
        Tab tabRules = new Tab();
        Tab tabCards = new Tab();

        // Tabs should not be closable
        tabRules.setClosable(false);
        tabConnection.setClosable(false);
        tabLobby.setClosable(false);
        tabLogin.setClosable(false);
        tabRegister.setClosable(false);
        tabGame.setClosable(false);
        tabCards.setClosable(false);

        tabPane.getTabs().addAll(tabConnection, tabLogin, tabRegister, tabLobby, tabGame, tabRules, tabCards);

        // Create ScrollPanes and add them to their specific tab
        ScrollPane scrollConnection = new ScrollPane();
        ScrollPane scrollLogin = new ScrollPane();
        ScrollPane scrollRegister = new ScrollPane();
        ScrollPane scrollLobby = new ScrollPane();
        ScrollPane scrollGame = new ScrollPane();
        ScrollPane scrollRules = new ScrollPane();
        ScrollPane scrollCards = new ScrollPane();

        tabConnection.setContent(scrollConnection);
        tabLogin.setContent(scrollLogin);
        tabRegister.setContent(scrollRegister);
        tabLobby.setContent(scrollLobby);
        tabGame.setContent(scrollGame);
        tabRules.setContent(scrollRules);
        tabCards.setContent(scrollCards);

        //Vertical ScrollBar should never appear because Text is resizeable
        scrollConnection.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollLogin.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollRegister.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollLobby.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollGame.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollRules.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollCards.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        // Creating vBox and add it to their specific ScrollPane
        VBox vBoxConnection = new VBox();
        VBox vBoxLogin = new VBox();
        VBox vBoxRegister = new VBox();
        VBox vBoxLobby = new VBox();
        VBox vBoxGame = new VBox();
        VBox vBoxRules = new VBox();
        VBox vBoxCards = new VBox();

        scrollConnection.setContent(vBoxConnection);
        scrollLogin.setContent(vBoxLogin);
        scrollRegister.setContent(vBoxRegister);
        scrollLobby.setContent(vBoxLobby);
        scrollGame.setContent(vBoxGame);
        scrollRules.setContent(vBoxRules);
        scrollCards.setContent(vBoxCards);

        //vBox should have a gap to the right side and bottom and content should be aligned center
        vBoxConnection.setPadding(new Insets(0, 0, 30, 20));
        vBoxLogin.setPadding(new Insets(0, 0, 30, 20));
        vBoxRegister.setPadding(new Insets(0, 0, 30, 20));
        vBoxLobby.setPadding(new Insets(0, 0, 30, 20));
        vBoxGame.setPadding(new Insets(0, 0, 30, 20));
        vBoxRules.setPadding(new Insets(0, 0, 30, 20));
        vBoxCards.setPadding(new Insets(0, 0, 30, 20));

        vBoxConnection.setAlignment(Pos.TOP_CENTER);
        vBoxLogin.setAlignment(Pos.TOP_CENTER);
        vBoxRegister.setAlignment(Pos.TOP_CENTER);
        vBoxLobby.setAlignment(Pos.TOP_CENTER);
        vBoxGame.setAlignment(Pos.TOP_CENTER);
        vBoxRules.setAlignment(Pos.TOP_CENTER);
        vBoxCards.setAlignment(Pos.TOP_CENTER);


        // Create Text
        Text txtConnection = new Text();
        Text txtLogin = new Text();
        Text txtRegister = new Text();
        Text txtLobby = new Text();
        Text txtGame = new Text();
        Text txtRules = new Text();
        Text txtCards = new Text();

        BorderPane bp = new BorderPane();
        bp.setCenter(tabPane);

        // tabPane and text size adjusts to scene
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());
        txtConnection.wrappingWidthProperty().bind(scene.widthProperty().add(-30));
        txtGame.wrappingWidthProperty().bind(scene.widthProperty().add(-30));
        txtLobby.wrappingWidthProperty().bind(scene.widthProperty().add(-30));
        txtLogin.wrappingWidthProperty().bind(scene.widthProperty().add(-30));
        txtRegister.wrappingWidthProperty().bind(scene.widthProperty().add(-30));
        txtRules.wrappingWidthProperty().bind(scene.widthProperty().add(-30));
        txtCards.wrappingWidthProperty().bind(scene.widthProperty().add(-30));

        // setText according to Language

        if (translator.getCurrentLanguage() == Language.ENGLISH){
            stage.setTitle("Help");

            tabConnection.setText("Connection");
            tabLogin.setText("Login");
            tabRegister.setText("Register");
            tabLobby.setText("Lobby");
            tabGame.setText("Game");
            tabRules.setText("Rules");
            tabCards.setText("Cards");

            // Text for Tab Connection
            String newLine = "\n";
            String doubleNewLine = "\n\n";
            txtConnection.setText(newLine + "To play Dominion, all players must choose a host that starts the server and communicates the IP and port numbers to the other players"
                    + doubleNewLine + "The host clicks on the button \"Start Server\" (See A) and fills in the desired port number in the following window (See C). The port number has to be in range between 1024 and 49151 (9092 excluded)."
                    + " Afterwards a dialog will appear with the IP and port number (See D). The host has to communicate those numbers to the other players. The numbers can be saved to the clipboard with the  \'clipboard button\'."
                    + doubleNewLine + "The other players click on the button \"Join Server\" (See B) and fill in the specific fields with the IP and port nummer, which the host communicated (See E). Afterwards all player will go the login window." + doubleNewLine);


            ImageView imgConnection1 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection1_EN.PNG")));
            imgConnection1.setPreserveRatio(true);
            imgConnection1.setFitHeight(250);

            ImageView imgConnection2 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection2_EN.PNG")));
            imgConnection2.setPreserveRatio(true);
            imgConnection2.setFitHeight(250);

            ImageView imgConnection3 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection3_EN.PNG")));
            imgConnection3.setPreserveRatio(true);
            imgConnection3.setFitHeight(250);

            ImageView imgConnection4 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection4_EN.PNG")));
            imgConnection4.setPreserveRatio(true);
            imgConnection4.setFitHeight(250);

            VBox.setMargin(imgConnection1, new Insets(0, 0, 30, 0));
            VBox.setMargin(imgConnection2, new Insets(0, 0, 30, 0));
            VBox.setMargin(imgConnection3, new Insets(0, 0, 30, 0));

            vBoxConnection.getChildren().addAll(txtConnection, imgConnection1, imgConnection2, imgConnection3, imgConnection4);


            // Text for Game
            txtGame.setText(newLine + "Section A" + newLine + "This section shows all players with their chosen username and the current victory points. The player who is currently playing will be highlighted with a green frame."
                    + doubleNewLine + "Section B" + newLine + "This section shows how many actions, buys and coins the player has. With each new turn, the player starts with one action and one buy."
                    + doubleNewLine + "Section C" + newLine + "This section shows all cards which can be bought. The red label indicates the number of cards which are still available. If the player has enough coins and enough buys, he can buy a card with a mouseclick through the buy phase. The bought card will move directly to the traystack. If you remain with your cursor for a few seconds on the card, the card will be displayed bigger. "
                    + doubleNewLine + "Section D" + newLine + "This button will lead the player through the different phases. If a player wants to end the current action phase, he can click on the button \"End action phase\" and will move to the buy phase. In the buy phase, all treasure cards can be played in order to buy cards."
                    + doubleNewLine + "Section E" + newLine + "The handcards will be displayed here. At the beginning of each turn, there will always be 5 cards. That number can change through the round because of actions of played cards and played cards itself."
                    + doubleNewLine + "Section F" + newLine + "This is the pullstack. The red label indicates the remaining cards in the pullstack. If the player has to draw cards and the pullstack is empty, the traystack will be shuffled and put on the pullstack."
                    + doubleNewLine + "Section G" + newLine + "This ist the traystack. All handcards, played cards and bought cards will move to this stack after the end of the turn. All cards will face up in order that the last card will be visible. If the pullstack is empty, the backside of an empty card will be displayed."
                    + doubleNewLine + "Section H" + newLine + "This is the area of played cards. As soon as a card is played from the handcards, it will move to this area."
                    + doubleNewLine + "Section I" + newLine + "This is the game log. It will show which player is currently playing and what each player bought and played."
                    + doubleNewLine + "Section J" + newLine + "This is the chat. All players can communicate with each other through the chat. Each player receives an unique color in order to distinguish the messages. If someone played a good turn, the other players can click on the button \"Well played!\"."
                    + doubleNewLine
            );

            ImageView imgGameView = new ImageView(new Image(getClass().getResourceAsStream("/help/GameView_EN.PNG")));
            imgGameView.setPreserveRatio(true);
            imgGameView.setFitHeight(400);

            vBoxGame.getChildren().addAll(txtGame, imgGameView);


            // Text for Lobby
            txtLobby.setText(newLine + "Section A" + newLine + "In this section, the host can choose the game end option. The game ends after a certain number of rounds or it ends regular."
                    + doubleNewLine + "Section B" + newLine + "This section shows all players with their username, which are currently in the lobby."
                    + doubleNewLine + "Section C" + newLine + "This is the chat. All players can communicate with each other through the chat. Each player receives an unique color in order to distinguish the messages."
                    + doubleNewLine + "Section D" + newLine + "If the host chooses a game end option and all players are already in the lobby, he can click on the button \"Start Game\" to start the game."
                    + doubleNewLine + "Section E" + newLine + "With a click on the button \"Ranking\" the player will see the ranking list. This list is empty as long as a game has never been played."
                    + doubleNewLine + "Section F" + newLine + "With a click on the button  \"Info\" the player receives the information that he has to select a game end option."
                    + doubleNewLine
            );


            ImageView imgLobbyHost = new ImageView(new Image(getClass().getResourceAsStream("/help/Lobby_Host_EN.PNG")));
            imgLobbyHost.setPreserveRatio(true);
            imgLobbyHost.setFitHeight(250);

            ImageView imgLobby = new ImageView(new Image(getClass().getResourceAsStream("/help/Lobby_EN.PNG")));
            imgLobby.setPreserveRatio(true);
            imgLobby.setFitHeight(250);

            vBoxLobby.getChildren().addAll(txtLobby, imgLobbyHost, imgLobby);
            VBox.setMargin(imgLobbyHost, new Insets(0, 0, 30, 0));


            // Text for Tab Login
            txtLogin.setText(newLine + "The player has to log in with his email and his password (See A). If the email and password matches, the player can click on the button \"Login\" and will go to the lobby.  "
                    + "If the player has no login, he has to click on the button \"Register\". \n" + "Initially, each player must register before the first play"
                    + doubleNewLine + "If the player has forgotten his password, he has to contact the host. The host can login with the following email: admin@dominion.com (\n" + "The password is in the documentation for security reasons)."
                    + " With the username and password the host comes to the usermanagement (See B). In the usermanagement you can create,delete, edit and even block a user. Here you can also change the passwords of the users."
                    + doubleNewLine);

            ImageView imgLogin = new ImageView(new Image(getClass().getResourceAsStream("/help/Login_EN.PNG")));
            imgLogin.setPreserveRatio(true);
            imgLogin.setFitHeight(250);

            ImageView imgUserMgmt = new ImageView(new Image(getClass().getResourceAsStream("/help/Benutzerverwaltung_EN.PNG")));
            imgUserMgmt.setPreserveRatio(true);
            imgUserMgmt.setFitHeight(250);

            vBoxLogin.getChildren().addAll(txtLogin, imgLogin, imgUserMgmt);
            VBox.setMargin(imgLogin, new Insets(0, 0, 30, 0));


            // Text for Tab Register
            txtRegister.setText(newLine + "\n" + "For the login you have to register once. The e-mail address and password are used for login. It was decided to use the e-mail address as the username may be forgotten. "
                    + "\n" + "The username is used in chat, in the lobby, in the ranking, and in the game. He is visible to all players."
                    + doubleNewLine);

            ImageView imgRegister = new ImageView(new Image(getClass().getResourceAsStream("/help/Register_ENG.PNG")));
            imgRegister.setPreserveRatio(true);
            imgRegister.setFitHeight(250);

            vBoxRegister.getChildren().addAll(txtRegister, imgRegister);


            // Text for Tab Rules
            txtRules.setText(newLine + "A turn" + newLine
                    + "1. Phase: Action - The player can play action cards from his hand. At the beginning of the turn, each player has only 1 action available."
                    + "However, this number can be increased by the instructions on certain action cards. The action phase can also be skipped."
                    + doubleNewLine + "2. Phase: Buy - The player can buy cards. Bought cards move directly to the traystack. For this the player must have enough buy actions and enough money. At the beginning of the turn, each player has only 1 buy action available."
                    + " However, this number can be increased by the instructions on certain action cards. The buy phase can also be skipped."
                    + doubleNewLine + "3. Phase: Clean-up - The player places all Action and Money Cards and his remaining cards on the traystack. The cards are always placed face up on the traystack, so that the top card is visible. This phase has to be played."
                    + " Then the player draws 5 cards from the pullstack and puts them on his hand."
                    + doubleNewLine + doubleNewLine + "End of game"
                    + newLine + "2 options can be selected to end the game:" + newLine + "1. The game ends after a defined number of rounds." + newLine + "2. The game ends when one of the two conditions occurs: The province stack is empty or any 3 actioncard stacks are empty."
                    + doubleNewLine + "After the end of the game, the winning points are counted in the entire deck of cards (handcards, tray stack and pull stack). The player with the most points wins the game."
            );

            vBoxRules.getChildren().add(txtRules);


            // Text for Tab Cards
            txtCards.setText(newLine + "Village - Draw a card and gain 2 actions."
                    + doubleNewLine + "Gardens - This card is a points card, not an action card. She has no function until the end of the game. In the rating, it counts 1 Victory Point per full 10 cards in the entire deck."
                    + doubleNewLine + "Moneylender - Trash a copper from your hand and get 3 virtual money."
                    + doubleNewLine + "Woodcutter - You gain 1 buy and 2 virtual money."
                    + doubleNewLine + "Festival - You gain 2 actions, 1 buy and 2 virtual money."
                    + doubleNewLine + "Laboratoty - Draw 2 cards and gain 1 action."
                    + doubleNewLine + "Market - Draw a card and gain 1 action, 1 buy and 1 virtual money."
                    + doubleNewLine + "Smithy - Draw 3 cards."
            );

            vBoxCards.getChildren().addAll(txtCards);

        }

        else if (translator.getCurrentLanguage() == Language.SWISS_GERMAN){
            stage.setTitle("Hilf");

            tabConnection.setText("Verbindig");
            tabLogin.setText("Login");
            tabRegister.setText("Registrierig");
            tabLobby.setText("Lobby");
            tabGame.setText("Spiel");
            tabRules.setText("Spielreglä");
            tabCards.setText("Kartä");


            // Text for Tab Connection
            String newLine = "\n";
            String doubleNewLine = "\n\n";
            txtConnection.setText(newLine + "Um Dominion spiele chöne, muess mer zerst de Host bestimme, wele de Server startet und IP- und Portnummere ah die restliche Mitspieler kommuniziert."
                    + doubleNewLine + "Dä Host klickt uf de Button \"Server startä\" (Lueg A) und tippt im nachfolgende Fenster die gwünscht Portnummere ih (Lueg C). Die söll im Bereich vo 1024-49151 (usgschlosse 9092) sii."
                    + " Danach erschiehnt en Dialog mit de IP- und Portnummere (Lueg D). Die beide Nummere mönd ah die restliche Mitspieler gsendet bzw. zur Verfüegig gstellt werdä. Dur de \'Clipboard Button\' chönd die Nummere i Zwüscheablag gspeicheret werde."
                    + doubleNewLine + "Die restliche Mitspieler klicket uf de Button \"Mit Server verbindä\" (Lueg B) und gänd ih die entsprechende Felder jewiils die IP- und Portnummere ih (Lueg E). Danach chömet all Spieler zum Login Fenster." + doubleNewLine);

            ImageView imgConnection1 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection1_CH.PNG")));
            imgConnection1.setPreserveRatio(true);
            imgConnection1.setFitHeight(250);

            ImageView imgConnection2 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection2_CH.PNG")));
            imgConnection2.setPreserveRatio(true);
            imgConnection2.setFitHeight(250);

            ImageView imgConnection3 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection3_CH.PNG")));
            imgConnection3.setPreserveRatio(true);
            imgConnection3.setFitHeight(250);

            ImageView imgConnection4 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection4_CH.PNG")));
            imgConnection4.setPreserveRatio(true);
            imgConnection4.setFitHeight(250);

            VBox.setMargin(imgConnection1, new Insets(0, 0, 30, 0));
            VBox.setMargin(imgConnection2, new Insets(0, 0, 30, 0));
            VBox.setMargin(imgConnection3, new Insets(0, 0, 30, 0));

            vBoxConnection.getChildren().addAll(txtConnection, imgConnection1, imgConnection2, imgConnection3, imgConnection4);


            // Text for Game
            txtGame.setText(newLine + "Abschnitt A" + newLine + "Ih dem Bereich sind die jewiilige Spieler mit ihrem Benutzernamä und de aktuelle Punktezahl ersichtlich. De Spieler, wele jewiils am Zug isch, wird durch e grüeni Umrandig hervorghobe."
                    + doubleNewLine + "Abschnitt B" + newLine + "Ih dem Bereich zeigts ah, wie viel Aktione, Käuf und Geld em Spieler zur Verfügig staht. Bi jedem Spielzug startet de Spieler mit einere Aktion und eim Kauf."
                    + doubleNewLine + "Abschnitt C" + newLine + "Ih dem Bereich lieget die Kartä wo kauft werdet chöne. S'rote Label zeigt Ahzahl vo de Karte wo no kauft werde chönd. Wenn de Spieler gnüegend Kaufaktione bsitzt, chan er die Karte ih de Kaufphase mit enem Muusklick kaufä. Die kaufte Karte wanderet direkt in Ablagestapel. Verbliebt mer mit em Cursor 1-2 Sekundä uf enem Bild, wird es vergrösseret dargstellt. "
                    + doubleNewLine + "Abschnitt D" + newLine + "Dä Button führt Spieler durch die verschiedene Phase. Möchti de Spieler die Aktionsphase beände, chan er eifach uf de Button \"Aktionsphase beände\" klicke und befindet sich denn i de Kaufphase. Ih de Kaufphase chönet Geldkarte gspielt und Karte damit kauft werde."
                    + doubleNewLine + "Abschnitt E" + newLine + "Das sind Handkarte vom Spieler. Am Ahfang vo jedem Spielzug, befindet sich immer 5 Kartä uf de Hand. Die Ahzahl chan sich verändere dur Aktionä vo usgspielte Aktionskartä oder durchs usspiele vo Handkarte."
                    + doubleNewLine + "Abschnitt F" + newLine + "Das isch de Nahziehstapel. S'rote Label zeigt d'Ahzahl Karte, welli no im Stapel sind. Falls es kei Karte meh zum zieh git, wird de Ablagestapel gmischt und Karte chömet uf de Nahziehstapel."
                    + doubleNewLine + "Abschnitt G" + newLine + "Das isch de Ablagestapel. All Handkarte sowie all gspielte Karte werdet nachem Endi vom Spielzug uf de Ablagestapel gleit. Die Karte werdet offe higleit, so dass immer die letzt Karte ersichtlich isch. Falls de Ablagestapel leer isch, wird Rücksiite vo ere leere Karte ahzeigt."
                    + doubleNewLine + "Abschnitt H" + newLine + "Das isch de Platz für usgspielti Karte. Sobald e Karte us de Hand gspielt wird , wanderet sie vo de Handkarte ih de Bereich."
                    + doubleNewLine + "Abschnitt I" + newLine + "Das isch Ahzeig für de Spielverlauf fürs gsamte Spiel. Es wird jewiils ahzeigt, wele Spieler am Zug isch, was er kauft und was er gspielt het."
                    + doubleNewLine + "Abschnitt J" + newLine + "Das isch de Chat. Mittels Chat chönd die Spieler mitenand kommuniziere. Jede Spieler erhält e eigeni Farb, damit Nachrichte unterschiede werde chönd. Falls öbert en guete Spielzug gmacht het, chönd die andere Spieler uf de Button \"Guete Spielzug\" klicke."
                    + doubleNewLine
            );

            ImageView imgGameView = new ImageView(new Image(getClass().getResourceAsStream("/help/GameView_CH.PNG")));
            imgGameView.setPreserveRatio(true);
            imgGameView.setFitHeight(400);

            vBoxGame.getChildren().addAll(txtGame, imgGameView);


            // Text for Lobby
            txtLobby.setText(newLine + "Abschnitt A" + newLine + "Ih dem Bereich chan de Host d'Spielendoption uswähle. S'Spiel endet nach ere bestimmte Ahzahl Runde oder regulär."
                    + doubleNewLine + "Abschnitt B" + newLine + "De Bereich zeigt all Spieler mit ihrem Benutzername ah, wo sich grad i de Lobby befindet."
                    + doubleNewLine + "Abschnitt C" + newLine + "Das isch de Chat. Mittels Chat chönd die Spieler mitenand kommuniziere. Jede Spieler erhält e eigeni Farb, damit Nachrichte unterschiede werde chönd."
                    + doubleNewLine + "Abschnitt D" + newLine + "Wenn de Host e Spielendoption usgwählt het und sich all Mitspieler ih de Lobby befindet, chan er  mit enem Klick uf de Button \"Spiel startä\" s'Spiel startä. All Spieler landet denn ih de Spielahsicht."
                    + doubleNewLine + "Abschnitt E" + newLine + "Mit enem Klick uf de Button \"Rangliste\" chunt de Spieler zur Rangliste. Die Liste isch leer solang no keis Spiel zu Änd gspielt worde isch."
                    + doubleNewLine + "Abschnitt F" + newLine + "Mit enem Klick uf den Button \"Info\" bechunt de Spieler d'Info, dass er e Spieloption uswähle set."
                    + doubleNewLine
            );


            ImageView imgLobbyHost = new ImageView(new Image(getClass().getResourceAsStream("/help/Lobby_Host_CH.PNG")));
            imgLobbyHost.setPreserveRatio(true);
            imgLobbyHost.setFitHeight(250);

            ImageView imgLobby = new ImageView(new Image(getClass().getResourceAsStream("/help/Lobby_CH.PNG")));
            imgLobby.setPreserveRatio(true);
            imgLobby.setFitHeight(250);

            vBoxLobby.getChildren().addAll(txtLobby, imgLobbyHost, imgLobby);
            VBox.setMargin(imgLobbyHost, new Insets(0, 0, 30, 0));


            // Text for Tab Login
            txtLogin.setText(newLine + "Dä jewiilig Spieler muen sich mit de Email und sim Passwort ihlogge (Lueg A). Stimmit Email mit em Password überih, führt en Klick uf de Button \"Login\" dä Spieler i Lobby.  "
                    + "Falls no keis Login bestaht, muen de Spieler uf de Button \"Registrierä\" klcike. Initial muen sich jede Spieler vorem erste Spiel registrierä."
                    + doubleNewLine + "Hät en Spieler sis Passwort vergässe, muen er de Host für en Passwortwechsel ahfröge. De Host chan sich mit de folgende Email im Login ahmelde: admin@dominion.com (S'Passwort staht us Sicherheitsgründ ih de Dokumentation)."
                    + " Damit glangt de Host direkt zu de Benutzerverwaltig (Lueg B). Ih de Benutzerverwaltig chan er Spieler hinzuefüege, lösche und blockiere. Zuesätzlich chan er Passwörter au ändere."
                    + doubleNewLine);

            ImageView imgLogin = new ImageView(new Image(getClass().getResourceAsStream("/help/Login_CH.PNG")));
            imgLogin.setPreserveRatio(true);
            imgLogin.setFitHeight(250);

            ImageView imgUserMgmt = new ImageView(new Image(getClass().getResourceAsStream("/help/Benutzerverwaltung_CH.PNG")));
            imgUserMgmt.setPreserveRatio(true);
            imgUserMgmt.setFitHeight(250);

            vBoxLogin.getChildren().addAll(txtLogin, imgLogin, imgUserMgmt);
            VBox.setMargin(imgLogin, new Insets(0, 0, 30, 0));


            // Text for Tab Register
            txtRegister.setText(newLine + "Fürs Login muen mer sich eimalig registrierä. D'email und s'Passwort werdet fürs Login verwendet. Es isch entschiedä worde, d'email z'verwende will mer de Benutzername eher vergisst. "
                    + "De Benutzername wird im Chat, ih de Lobby, ih de Rangliste und im Spiel verwendet und isch für all Mitspieler ersichtlich."
                    + doubleNewLine);

            ImageView imgRegister = new ImageView(new Image(getClass().getResourceAsStream("/help/Register_CH.PNG")));
            imgRegister.setPreserveRatio(true);
            imgRegister.setFitHeight(250);

            vBoxRegister.getChildren().addAll(txtRegister, imgRegister);


            // Text for Tab Rules
            txtRules.setText(newLine + "Zugablauf" + newLine
                    + "1. Phase: Aktion - De Speler chan Aktionskarte vo sinere Hand usspiele. Am Ahfang vom Spielzug hät jede Spieler nur 1 Aktion zur Verfüegig."
                    + " Die Zahl chan sich aber durch Ahwiisige vo bestimmte Karte erhöhe. D'Aktionsphase chan au übersprunge werdä."
                    + doubleNewLine + "2. Phase: Kauf - Dä Spieler chan Karte usem Vorrat kaufe. Die kaufte Karte wanderet direkt uf de Ablagestapel. Defür muess de Spieler gnüegend Kaufaktione und gnüegend Geld bsitze. Am Ahfang vo jedem Spielzug het jedä Spieler nur 1 Kaufaktion zur Verfüegig. "
                    + " Die Zahl chan sich aber durch Ahwiisige vo bestimmte Karte erhöhe. D'Kaufphase chan au übersprunge werdä."
                    + doubleNewLine + "3. Phase: Ufruume - De Spieler leiht all usgspielte Aktions- und Geldkarte und sini verbliebende Handkarte uf de Ablagestapel. Die Karte werdet immer offe uf de Ablagestapel gleit. Somit isch die obersti Karte ersichtlich. Die Phase muen gspielt werde."
                    + " Danach zieht de Spieler 5 Karte vom Nahziehstapel und nimmt die uf sini Hand."
                    + doubleNewLine + doubleNewLine + "Spielendi"
                    + newLine + "Es chönd 2 Optione usgwählt werde ums Spiel z'beände." + newLine + "1. S'Spiel endet nach ere definierte Ahzahl Rundene." + newLine + "2. S'Spiel endet wenni eini vo beidne Bedingige ihtritt: De Provinzstapel isch leer oder 3 beliebigi Aktionskartestapel usem Vorrat sind leer."
                    + doubleNewLine + "Nachem Endi vom Spiel, werdet die Siegespünkt im gsamte Kartesatz (Handkarte, Ablage- und Nahziehstapel) zählt. De Spieler mit de meiste Pünkt gwünnt s'Spiel."
            );

            vBoxRules.getChildren().add(txtRules);


            // Text for Tab Cards
            txtCards.setText(newLine + "Dorf - Zieh eine Karte und erhalte 2 Aktionen."
                    + doubleNewLine + "Gärten - Diese Königreichskarte ist eine Punktekarte, keine Aktionskarte. Sie hat bis zum Ende des Spiels keine Funktion. Bei der Wertung, zählt sie 1 Siegespunkt pro volle 10 Karten im gesamten Kartensatz."
                    + doubleNewLine + "Geldverleiher - Entsorge ein Kupfer aus deiner Hand und erhalte dafür 3 virtuelles Geld."
                    + doubleNewLine + "Holzfäller - Du erhältst einen zusätzlichen Kauf und 2 virtuelles Geld."
                    + doubleNewLine + "Jahrmarkt - Du erhältst 2 Aktionen, einen zusätzlichen Kauf und 2 virtuelles Geld."
                    + doubleNewLine + "Laboratorium - Zieh 2 Karten und erhalte eine zusätzliche Aktion."
                    + doubleNewLine + "Markt - Zieh eine Karte und erhalte 1 zusätzliche Aktion, einen zusätzlichen Kauf und 1 virtuelles Geld."
                    + doubleNewLine + "Schmiede - Zieh 3 Karten."
            );

            vBoxCards.getChildren().addAll(txtCards);



        }   else{
            stage.setTitle("Hilfe");

            tabConnection.setText("Verbindung");
            tabLogin.setText("Login");
            tabRegister.setText("Registrierung");
            tabLobby.setText("Lobby");
            tabGame.setText("Spiel");
            tabRules.setText("Spielregeln");
            tabCards.setText("Karten");


            // Text for Tab Connection
            String newLine = "\n";
            String doubleNewLine = "\n\n";
            txtConnection.setText(newLine + "Um Dominion spielen zu können, muss zuerst der Host bestimmt werden, welcher den Server startet und die IP- und Portnummer an die restlichen Mitspieler kommuniziert."
                    + doubleNewLine + "Der Host klickt auf den Button \"Server starten\" (Siehe A) und tippt im nachfolgenden Fenster die gewünschte Portnummer ein (Siehe C). Diese soll im Bereich von 1024-49151 (ausgeschlossen 9092) sein."
                    + " Danach erscheint ein Dialog mit der IP- und Portnummer (Siehe D). Diese beiden Nummern müssen nun an die restlichen Mitspieler gesendet bzw. zur Verfügung gestellt werden. Durch den \'Clipboard Button\' können die Nummern in die Zwischenablage gespeichert werden."
                    + doubleNewLine + "Die restlichen Mitspieler klicken auf den Button \"Mit Server verbinden\" (Siehe B) und geben in die entsprecheden Felder jeweils die IP- und Portnummer ein (Siehe E). Danach kommen alle Spieler zum Login Fenster." + doubleNewLine);

            ImageView imgConnection1 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection1_DE.PNG")));
            imgConnection1.setPreserveRatio(true);
            imgConnection1.setFitHeight(250);

            ImageView imgConnection2 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection2_DE.PNG")));
            imgConnection2.setPreserveRatio(true);
            imgConnection2.setFitHeight(250);

            ImageView imgConnection3 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection3_DE.PNG")));
            imgConnection3.setPreserveRatio(true);
            imgConnection3.setFitHeight(250);

            ImageView imgConnection4 = new ImageView(new Image(getClass().getResourceAsStream("/help/Connection4_DE.PNG")));
            imgConnection4.setPreserveRatio(true);
            imgConnection4.setFitHeight(250);

            VBox.setMargin(imgConnection1, new Insets(0, 0, 30, 0));
            VBox.setMargin(imgConnection2, new Insets(0, 0, 30, 0));
            VBox.setMargin(imgConnection3, new Insets(0, 0, 30, 0));

            vBoxConnection.getChildren().addAll(txtConnection, imgConnection1, imgConnection2, imgConnection3, imgConnection4);


            // Text for Game
            txtGame.setText(newLine + "Abschnitt A" + newLine + "In diesem Bereich sind die jeweiligen Spieler mit ihrem Benutzernamen und der aktuellen Punkteanzahl ersichtlich. Der Spieler, welcher jeweils am Zug ist, wird durch eine grüne Umrandung hervorgehoben."
            + doubleNewLine + "Abschnitt B" + newLine + "Dieser Bereich zeigt an, wie viele Aktionen, Käufe und virtuelles Geld dem Spieler zur Verfügung steht. Bei jedem Spielzug startet der Spieler mit einer Aktion und einem Kauf."
            + doubleNewLine + "Abschnitt C" + newLine + "In diesem Bereich liegen die Karten, welche gekauft werden können. Das rote Label zeigt die Anzahl der Karten, die noch gekauft werden können. Wenn der Spieler genügend Geld und genügend Kaufaktionen besitzt, kann er die Karte in der Kaufphase mit einem Mausklick kaufen. Die gekaufte Karte wandert direkt in den Ablagestapel. Verbleibt man mit dem Cursor 1-2 Sekunden auf einem Bild, wird es vergrössert dargestellt. "
            + doubleNewLine + "Abschnitt D" + newLine + "Dieser Button führt den Spieler durch die verschiedenen Phasen. Möchte der Spieler die Aktionsphase beenden, kann er auf den Button \"Aktionsphase beenden\" klicken und befindet sich dann in der Kaufphase. In der Kaufphase können Geldkarten gespielt und Karten damit gekauft werden."
            + doubleNewLine + "Abschnitt E" + newLine + "Dies sind die Handkarten des Spielers. Zu Beginn eines Spielzugs, befinden sich immer 5 Karten auf der Hand. Diese Anzahl kann sich verändern durch Aktionen von ausgespielten Aktionskarten oder auch durch das Ausspielen von Handkarten."
            + doubleNewLine + "Abschnitt F" + newLine + "Dies ist der Nachziehstapel. Das rote Label zeigt die Anzahl Karten, welche sich noch im Stapel befinden. Falls es keine Karten mehr zu ziehen gibt, wird der Ablagestapel gemischt und die Karten kommen auf den Nachziehstapel."
            + doubleNewLine + "Abschnitt G" + newLine + "Dies ist der Ablagestapel. Alle Handkarten sowie alle gespielten oder gekauften Karten werden nach dem Ende des Spielzugs auf den Ablagestapel gelegt. Die Karten werden offen hingelegt, somit ist immer die letzte Karte ersichtlich. Falls der Ablagestapel leer ist, wird die Rückseite einer leeren Karte angezeigt."
            + doubleNewLine + "Abschnitt H" + newLine + "Dies ist der Platz für ausgespielte Karten. Sobald eine Karte aus der Hand gespielt wird, wandert sie von den Handkarten in diesen Bereich "
            + doubleNewLine + "Abschnitt I" + newLine + "Dies ist die Anzeige für den Spielverlauf für das gesamte Spiel. Es wird jeweils angezeigt, welcher Spieler am Zug ist, was er gekauft und was er gespielt hat."
            + doubleNewLine + "Abschnitt J" + newLine + "Dies ist der Chat. Mittels Chat können die Spieler miteinander kommunizieren. Jeder Spieler erhält eine eigene Farbe, damit die Nachrichten unterschieden werden können. Falls jemand einen guten Spielzug durchgeführt hat, können die anderen Spieler auf den Button \"Guter Spielzug\" klicken."
            + doubleNewLine
            );

            ImageView imgGameView = new ImageView(new Image(getClass().getResourceAsStream("/help/GameView_DE.PNG")));
            imgGameView.setPreserveRatio(true);
            imgGameView.setFitHeight(400);

            vBoxGame.getChildren().addAll(txtGame, imgGameView);


            // Text for Lobby
            txtLobby.setText(newLine + "Abschnitt A" + newLine + "In diesem Bereich kann der Host die Spielendoption auswählen. Das Spiel endet nach einer bestimmten Anzahl Runden oder regulär."
            + doubleNewLine + "Abschnitt B" + newLine + "Dieser Bereich zeigt alle Spieler mit ihrem Benutzernamen an, welche sich zurzeit in der Lobby befinden."
            + doubleNewLine + "Abschnitt C" + newLine + "In diesem Bereich befindet sich der Chat. Mittels Chat können die Spieler miteinander kommunizieren. Jeder Spieler erhält eine eigene Farbe, damit die Nachrichten unterschieden werden können."
            + doubleNewLine + "Abschnitt D" + newLine + "Wenn der Host eine Spielendoption ausgewählt hat und sich alle Mitspieler in der Lobby befinden, kann er mit einem Klick auf den Button \"Spiel starten\" das Spiel starten. Alle Spieler landen dann in der Spielansicht."
            + doubleNewLine + "Abschnitt E" + newLine + "Mit einem Klick auf den Button \"Ranking\" gelangt der Spieler zur Rangliste. Diese Liste ist leer solange noch kein Spiel zu Ende gespielt worden ist."
            + doubleNewLine + "Abschnitt F" + newLine + "Mit einem Klick auf den Button \"Info\" erhält der Spieler die Information, dass er eine Spielendoption auswählen soll. "
            + doubleNewLine
            );


            ImageView imgLobbyHost = new ImageView(new Image(getClass().getResourceAsStream("/help/Lobby_Host_DE.PNG")));
            imgLobbyHost.setPreserveRatio(true);
            imgLobbyHost.setFitHeight(250);

            ImageView imgLobby = new ImageView(new Image(getClass().getResourceAsStream("/help/Lobby_DE.PNG")));
            imgLobby.setPreserveRatio(true);
            imgLobby.setFitHeight(250);

            vBoxLobby.getChildren().addAll(txtLobby, imgLobbyHost, imgLobby);
            VBox.setMargin(imgLobbyHost, new Insets(0, 0, 30, 0));


            // Text for Tab Login
            txtLogin.setText(newLine + "Der jeweilige Spieler muss sich nun mit seiner E-Mail Adresse und seinem Passwort einloggen (Siehe A). Stimmt die E-Mail Adresse mit dem Passwort überein, führt ein Klick auf den Button \"Login\" den Spieler in die Lobby.  "
            + "Falls noch kein Login besteht, muss der Spieler auf den Button \"Registrieren\" klicken. Initial muss sich jeder Spieler vor dem ersten Spielen registrieren."
            + doubleNewLine + "Hat ein Spieler sein Passwort vergessen, muss er den Host für einen Passwortwechsel anfragen. Der Host kann sich mit folgender E-Mail Adresse im Login anmelden: admin@dominion.com (Das Passwort steht aus Sicherheitsgründen in der Dokumentation)."
            + " Damit gelangt der Host direkt zur Benutzerverwaltung (Siehe B). In der Benutzerverwaltung können Spieler hinzugefügt, gelöscht oder blockiert werden. Zusätzlich können hier die Passwörter geändert werden."
            + doubleNewLine);

            ImageView imgLoginDE = new ImageView(new Image(getClass().getResourceAsStream("/help/Login_DE.PNG")));
            imgLoginDE.setPreserveRatio(true);
            imgLoginDE.setFitHeight(250);

            ImageView imgUserMgmt = new ImageView(new Image(getClass().getResourceAsStream("/help/Benutzerverwaltung_DE.PNG")));
            imgUserMgmt.setPreserveRatio(true);
            imgUserMgmt.setFitHeight(250);

            vBoxLogin.getChildren().addAll(txtLogin, imgLoginDE, imgUserMgmt);
            VBox.setMargin(imgLoginDE, new Insets(0, 0, 30, 0));


            // Text for Tab Register
            txtRegister.setText(newLine + "Für das Login muss man sich einmalig registrieren. Die E-Mail Adresse und das Passwort werden für das Login verwendet. Es wurde entschieden die E-Mail Adresse zu verwenden, da der Benutzername eher in Vergessenheit geraten kann. "
            + "Der Benutzername wird im Chat, in der Lobby, in der Rangliste und im Spiel verwendet. Er ist für alle Mitspieler ersichtlich."
            + doubleNewLine);

            ImageView imgRegisterDE = new ImageView(new Image(getClass().getResourceAsStream("/help/Register_DE.PNG")));
            imgRegisterDE.setPreserveRatio(true);
            imgRegisterDE.setFitHeight(250);

            vBoxRegister.getChildren().addAll(txtRegister, imgRegisterDE);


            // Text for Tab Rules
            txtRules.setText(newLine + "Zugablauf" + newLine
            + "1. Phase: Aktion - Der Spieler kann Aktionskarten aus seiner Hand ausspielen. Zu Beginn des Spielzugs hat jeder Spieler nur 1 Aktion zur Verfügung."
            + " Diese Zahl kann jedoch durch die Anweisungen auf bestimmten Aktionskarten erhöht werden. Die Aktionsphase kann auch übersprungen werden."
            + doubleNewLine + "2. Phase: Kauf - Der Spieler kann Karten aus dem Vorrat kaufen. Gekaufte Karten wandern direkt auf den Ablagestapel Dafür muss er genügend Kaufaktionen und genügend Geld besitzen. Zu Beginn des Spielzugs hat jeder Spieler nur 1 Kaufaktion zur Verfügung. "
            + " Diese Zahl kann jedoch durch die Anweisungen auf bestimmten Aktionskarten erhöht werden. Die Kaufphase kann auch übersprungen werden."
            + doubleNewLine + "3. Phase: Aufräumen - Der Spieler legt alle ausgespielten Aktions- und Geldkarten und seine verbliebenen Handkarten auf den Ablagestapel. Die Karten werden immer offen auf den Ablagestapel gelegt, so dass die oberste Karte ersichtlich ist. Diese Phase muss gespielt werden."
            + " Danach zieht der Spieler 5 Karten vom Nachziehstapel und nimmt diese auf seine Hand."
            + doubleNewLine + doubleNewLine + "Spielende"
            + newLine + "Es können 2 Optionen ausgewählt werden um das Spiel zu beenden." + newLine + "1. Das Spiel endet nach einer definierten Anzahl an Runden." + newLine + "2. Das Spiel endet, in dem eine der beiden Bedingugen eintritt: Der Provinzstapel ist leer oder 3 beliebige Aktionskartenstapel aus dem Vorrat sind leer."
            + doubleNewLine + "Nach dem Ende des Spiels, werden die Siegespunkte im gesamten Kartensatz (Handkarten, Ablage- und Nachziehstapel) gezählt. Der Spieler mit den meisten Punkten gewinnt das Spiel."
            );

            vBoxRules.getChildren().add(txtRules);


            // Text for Tab Cards
            txtCards.setText(newLine + "Dorf - Zieh eine Karte und erhalte 2 Aktionen."
            + doubleNewLine + "Gärten - Diese Königreichskarte ist eine Punktekarte, keine Aktionskarte. Sie hat bis zum Ende des Spiels keine Funktion. Bei der Wertung, zählt sie 1 Siegespunkt pro volle 10 Karten im gesamten Kartensatz."
            + doubleNewLine + "Geldverleiher - Entsorge ein Kupfer aus deiner Hand und erhalte dafür 3 virtuelles Geld."
            + doubleNewLine + "Holzfäller - Du erhältst einen zusätzlichen Kauf und 2 virtuelles Geld."
            + doubleNewLine + "Jahrmarkt - Du erhältst 2 Aktionen, einen zusätzlichen Kauf und 2 virtuelles Geld."
            + doubleNewLine + "Laboratorium - Zieh 2 Karten und erhalte eine zusätzliche Aktion."
            + doubleNewLine + "Markt - Zieh eine Karte und erhalte 1 zusätzliche Aktion, einen zusätzlichen Kauf und 1 virtuelles Geld."
            + doubleNewLine + "Schmiede - Zieh 3 Karten."
            );

            vBoxCards.getChildren().addAll(txtCards);

        }

        root.getChildren().add(bp);
        stage.setScene(scene);


        return scene;
    }

    protected void setTexts() {

    }

}
