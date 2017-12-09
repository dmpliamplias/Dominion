package splashscreen;

import base.View;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import com.sun.javafx.geom.Shape;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import static util.StyleSheetPath.SPLASH_SCREEN;

/**
 * Framework for professional applications:
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards.
 * All rights reserved. This code 5 is licensed under the terms of the BSD 3-clause license
 * For more details please see the file "license.txt").
 *
 * @author Brad Richards
 */
public class SplashScreenView extends View<SplashScreenModel> {
    int delay = 0;

    // ---- Members

    /** The progess bar. */
    ProgressIndicator progress;


    // ---- Constructor

    /**
     * Constructor
     *
     * @param stage the stage.
     * @param model the model.
     */
    public SplashScreenView(Stage stage, SplashScreenModel model) {
        super(stage, model);

        stage.initStyle(StageStyle.TRANSPARENT);
    }


    // ---- Methods

    @Override
    protected Scene create_GUI() {
        //TODO: 09.12.2017: Dyoni, bitte mal ahluege warum es min progressindicator ned updated....danke :-)

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


        ImageView imgVDominion = new ImageView(new Image(getClass().getResourceAsStream("/splashscreen/Dominion.png")));
        imgVDominion.setPreserveRatio(true);
        imgVDominion.setFitWidth(350);
        imgVDominion.setLayoutX(25);
        imgVDominion.setLayoutY(25);

        ImageView imgVPcGame = new ImageView(new Image(getClass().getResourceAsStream("/splashscreen/PcGame.png")));
        imgVPcGame.setPreserveRatio(true);
        imgVPcGame.setFitWidth(90);
        imgVPcGame.setLayoutX(155);
        imgVPcGame.setLayoutY(130);

        progress = new ProgressIndicator();
        progress.setPrefSize(75, 75);
        progress.setLayoutX(162.5);
        progress.setLayoutY(210);


        ImageView imgVCreatedBy = new ImageView(new Image(getClass().getResourceAsStream("/splashscreen/CreatedBy_.png")));
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


    public void createTransition(Text name){
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


