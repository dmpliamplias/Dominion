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

        stage.setTitle("Help");

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
            tabConnection.setText("Connection");
            tabLogin.setText("Login");
            tabRegister.setText("Register");
            tabLobby.setText("Lobby");
            tabGame.setText("Game");
            tabRules.setText("Rules");
            tabCards.setText("Cards");


            txtConnection.setText("");
            txtGame.setText("");
            txtLobby.setText("");
            txtLogin.setText("");
            txtRegister.setText("");
            txtRules.setText("");
            txtCards.setText("");

        }

        else if (translator.getCurrentLanguage() == Language.SWISS_GERMAN){
            tabConnection.setText("Verbindig");
            tabLogin.setText("Login");
            tabRegister.setText("Registrierig");
            tabLobby.setText("Lobby");
            tabGame.setText("Spiel");
            tabRules.setText("Spielreglä");
            tabCards.setText("Kartä");


            txtConnection.setText("");
            txtGame.setText("");
            txtLobby.setText("");
            txtLogin.setText("");
            txtRegister.setText("");
            txtRules.setText("");
            txtCards.setText("");



        }   else{
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
            txtConnection.setText(newLine + "Um Dominion spielen zu können, muss zuerst der Host bestimmt werden, welcher sich mit dem Server verbindet und die IP- und Portnummer an die restlichen Mitspieler kommuniziert."
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
