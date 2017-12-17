package help;

import base.View;
import com.weddingcrashers.service.Language;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelpView extends View<HelpModel> {

    public HelpView(Stage stage, HelpModel model){
        super(stage, model);
    }

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

        // Creating vBox and add it to their specific tab
        VBox vBoxConnection = new VBox();
        VBox vBoxLogin = new VBox();
        VBox vBoxRegister = new VBox();
        VBox vBoxLobby = new VBox();
        VBox vBoxGame = new VBox();
        VBox vBoxRules = new VBox();
        VBox vBoxCards = new VBox();

        tabConnection.setContent(vBoxConnection);
        tabLogin.setContent(vBoxLogin);
        tabRegister.setContent(vBoxRegister);
        tabLobby.setContent(vBoxLobby);
        tabGame.setContent(vBoxGame);
        tabRules.setContent(vBoxRules);
        tabCards.setContent(vBoxCards);

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
        txtConnection.wrappingWidthProperty().bind(scene.widthProperty());
        txtGame.wrappingWidthProperty().bind(scene.widthProperty());
        txtLobby.wrappingWidthProperty().bind(scene.widthProperty());
        txtLogin.wrappingWidthProperty().bind(scene.widthProperty());
        txtRegister.wrappingWidthProperty().bind(scene.widthProperty());
        txtRules.wrappingWidthProperty().bind(scene.widthProperty());
        txtCards.wrappingWidthProperty().bind(scene.widthProperty());




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
            txtConnection.setText(newLine + "Um eine Runde Dominion zu spielen, muss zuerst der Host bestimmt werden, welcher sich mit dem Server verbindet und die IP- und Portnummer an die restlichen Mitspieler kommuniziert."
                    + doubleNewLine + "Der Host klickt auf den Button \"Server starten\" und tippt im nachfolgenden Fenster die gewünschte Portnummer ein. Diese soll im Bereich von 1024-49151 (ausgeschlossen 9092) sein."
                    + " Danach erscheint ein Dialog mit der IP- und Portnummer. Diese beiden Nummern müssen nun an die restlichen Mitspieler gesendet werden. Durch den \'Clipboard Button\' können die Nummern in die Zwischenablage gespeichert werden."
                    + doubleNewLine + "Die restlichen Mitspieler klicken auf den Button \"Mit Server verbinden\" und tragen die Nummern ein. Danach kommen alle Spieler zum Login Fenster." + newLine);


            vBoxConnection.getChildren().addAll(txtConnection);
            vBoxConnection.setAlignment(Pos.TOP_CENTER);


            txtGame.setText("");
            txtLobby.setText("");
            txtLogin.setText("");

            // Text for Tab Register
            txtRegister.setText("");


            // Text for Tab Rules
            txtRules.setText(newLine + "Zugablauf" + newLine
            + "1. Phase: Aktion - Der Spieler kann Aktionskarten auf seiner Hand ausspielen. Zu Beginn des Spielzugs hat jeder Spieler nur 1 Aktion zur Verfügung."
            + " Diese Zahl kann jedoch durch die Anweisungen auf bestimmten Aktionskarten erhöht werden. Die Aktionsphase kann auch übersprungen werden."
            + doubleNewLine + "2. Phase: Kauf - Der Spieler kann Karten aus dem Vorrat kaufen. Gekaufte Karten wandern direkt auf den Ablagestapel Dafür muss er genügend Kaufaktionen und genügend Geld besitzen. Zu Beginn des Spielzugs hat jeder Spieler nur 1 Kaufaktion zur Verfügung. "
            + " Diese Zahl kann jedoch durch die Anweisungen auf bestimmten Aktionskarten erhöht werden. Die Kaufphase kann auch übersprungen werden."
            + doubleNewLine + "3. Phase: Aufräumen - Der Spieler legt alle ausgespielten Aktions- und Geldkarten und seine verbliebenen Handkarten auf den Ablagestapel. Die Karten werden immer offen auf den Ablagestapel gelegt, so dass die oberste Karte ersichtlich ist. Diese Phase muss gespielt werden."
            + " Danach zieht der Spieler 5 Karten vom Nachziehstapel und nimmt diese auf seine Hand."
            + doubleNewLine + doubleNewLine + "Spielende"
            + newLine + "Es können 2 Optionen ausgewählt werden um das Spiel zu beenden." + newLine + "1. Das Spiel endet nach einer definierten Anzahl Runden." + newLine + "2. Das Spiel endet, in dem eine der beiden Bedingugen eintritt: Der Provinzstapel ist leer oder 3 beliebige Aktionskartenstapel aus dem Vorrat sind leer."
            + doubleNewLine + "Nach dem Ende des Spiels, werden die Siegespunkte im gesamten Kartensatz (Handkarten, Ablage- und Nachziehstapel) gezählt. Der Spieler mit den meisten Punkten gewinnt das Spiel."
            );

            vBoxRules.getChildren().add(txtRules);
            vBoxRules.setAlignment(Pos.TOP_CENTER);


            // Text for Tab Cards
            txtCards.setText(newLine + "Dorf - Zieh eine Karte und erhalte 2 Aktionen."
            + doubleNewLine + "Gärten - Diese Königreichskarte ist eine Punktekarte, keine Aktionskarte. Sie hat bis zum Ende des Spiels keine Funktion. Bei der Wertung, zählt sie 1 Siegespunkt pro volle 10 Karten im gesamten Kartensatz."
            + doubleNewLine + "Geldverleiher - Entsorge ein Kupfer aus deiner Hand und erhalte dafür 3 virtuelles Geld."
            + doubleNewLine + "Holzfäller - Du erhälst einen zusätzlichen Kauf und 2 virtuelles Geld."
            + doubleNewLine + "Jahrmarkt - Du erhälst 2 Aktionen, einen zusätzlichen Kauf und 2 virtuelles Geld."
            + doubleNewLine + "Laboratorium - Zieh 2 Karten und erhalte eine zusätzliche Aktion."
            + doubleNewLine + "Markt - Zieh eine Karte und erhalte 1 zusätzliche Aktion, einen zusätzlichen Kauf und 1 virtuelles Geld."
            + doubleNewLine + "Schmiede - Zieh 3 Karten."
            );

            vBoxCards.getChildren().addAll(txtCards);
            vBoxCards.setAlignment(Pos.TOP_CENTER);

        }

        root.getChildren().add(bp);
        stage.setScene(scene);


        return scene;
    }

    protected void setTexts() {

    }

}
