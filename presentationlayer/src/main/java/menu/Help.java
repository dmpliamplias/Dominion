package menu;

import base.Model;
import base.View;
import connection.ConnectionModel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static util.StyleSheetPath.CONNECTION;

public class Help extends View {

    public Help (Stage stage, Model model){
        super(stage, model);
    }

    public Scene create_GUI(){

        stage.setTitle("Help");
        Group root = new Group();
        Scene scene = new Scene(root, 800, 500, Color.WHITE);

        TabPane tabPane = new TabPane();
        BorderPane bp = new BorderPane();

        bp.setCenter(tabPane);
        root.getChildren().add(bp);

        Tab tabConnection = new Tab("Connection");
        Tab tabLogin = new Tab("Login");
        Tab tabRegister = new Tab("Register");
        Tab tabLobby = new Tab("Lobby");
        Tab tabGame = new Tab("Game");
        Tab tabRules = new Tab("Rules");

        tabPane.getTabs().addAll(tabConnection, tabLogin, tabRegister, tabLobby, tabGame, tabRules);


        // tabPane size adjusts to scene
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());


        stage.setScene(scene);
       // setStylesheet(scene, CONNECTION);

        return scene;
    }

    protected void setTexts() {}



}
