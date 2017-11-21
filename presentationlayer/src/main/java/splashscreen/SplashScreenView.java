package splashscreen;

import base.View;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    // ---- Members

    /** The progess bar. */
    ProgressBar progress;


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
        BorderPane root = new BorderPane();
        root.setId("splash");

        progress = new ProgressBar();
        HBox bottomBox = new HBox();
        bottomBox.setId("progressbox");
        bottomBox.getChildren().add(progress);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 500, 500, Color.TRANSPARENT);
        setStylesheet(scene, SPLASH_SCREEN);

        return scene;
    }

    @Override
    protected void setTexts() {
        // nop
    }

}


