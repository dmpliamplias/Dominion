package splashscreen;

import base.View;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.stage.StageStyle.TRANSPARENT;
import static util.StyleSheetPath.SPLASH_SCREEN;

public class SplashScreenView extends View<SplashScreenModel> {

    // ---- Members

    /** The progess bar. */
    ProgressIndicator progress;

    /** The delay. */
    private int delay = 0;


    // ---- Constructor

    /**
     * Constructor
     *
     * @param stage the stage.
     * @param model the model.
     */
    public SplashScreenView(Stage stage, SplashScreenModel model) {
        super(stage, model);
    }


    // ---- Methods

    @Override
    protected Scene create_GUI() {
        stage.initStyle(TRANSPARENT);

        BorderPane root = new BorderPane();
        root.setId("splash");
        root.setPrefSize(400, 400);
        Pane pane = new Pane();
        root.setCenter(pane);
        Scene scene = new Scene(root);

        setStylesheet(scene, SPLASH_SCREEN);

        // Create Names
        Text Manuel = new Text("Manuel Wirz");
        Text Vanessa = new Text("Vanessa Cajochen");
        Text Michel = new Text("Michel Schlatter");
        Text Dyoni = new Text("Dyonisos Mpliamplias");
        Text Murat = new Text("Murat Kelleci");

        // Create 1 transition for each name
        createTransition(Manuel);
        createTransition(Vanessa);
        createTransition(Michel);
        createTransition(Dyoni);
        createTransition(Murat);

        pane.getChildren().addAll(Manuel, Vanessa, Michel, Dyoni, Murat);


        ImageView imgVDominion = new ImageView(new Image(getClass().getResourceAsStream("/splashscreen/dominion.png")));
        imgVDominion.setPreserveRatio(true);
        imgVDominion.setFitWidth(350);
        imgVDominion.setLayoutX(25);
        imgVDominion.setLayoutY(25);

        ImageView imgVPcGame = new ImageView(new Image(getClass().getResourceAsStream("/splashscreen/pcGame.png")));
        imgVPcGame.setPreserveRatio(true);
        imgVPcGame.setFitWidth(90);
        imgVPcGame.setLayoutX(155);
        imgVPcGame.setLayoutY(130);

        progress = new ProgressIndicator();
        progress.setPrefSize(75, 75);
        progress.setLayoutX(162.5);
        progress.setLayoutY(210);


        ImageView imgVCreatedBy = new ImageView(new Image(getClass().getResourceAsStream("/splashscreen/createdBy_.png")));
        imgVCreatedBy.setPreserveRatio(true);
        imgVCreatedBy.setFitWidth(75);
        imgVCreatedBy.setLayoutX(162.5);
        imgVCreatedBy.setLayoutY(300);

        pane.getChildren().addAll(progress, imgVCreatedBy, imgVPcGame, imgVDominion);


        stage.setScene(scene);
        stage.setTitle("SplashScreen");
        stage.show();
        return scene;

    }

    private void createTransition(Text name){
        name.setTranslateX(500);
        name.setTranslateY(330);
        name.setTextOrigin(VPos.TOP);
        name.setFont(Font.font(18));

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(5));
        transition.setDelay(Duration.seconds(delay));
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setNode(name);
        transition.setToX(-500);
        transition.setToY(330);
        transition.play();
        delay += 1;
    }

    @Override
    protected void setTexts() {
        // nop
    }

}


