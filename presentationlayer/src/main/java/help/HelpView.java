package help;

import base.View;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelpView extends View<HelpModel> {

    public HelpView(Stage stage, HelpModel model){
        super(stage, model);
    }

    public Scene create_GUI(){

        stage.setTitle("HelpView");
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

    protected void setTexts() {

    }

}
