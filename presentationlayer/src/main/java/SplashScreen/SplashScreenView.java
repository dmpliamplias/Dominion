package splashscreen;

import com.weddingcrashers.View;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Framework for professional applications:
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards.
 * All rights reserved. This code 5 is licensed under the terms of the BSD 3-clause license
 * For more details please see the file "license.txt").
 *  @author Brad Richards, small changes from Michel Schlatter
 */

public class SplashScreenView extends View<SplashScreenModel> {
    ProgressBar progress;
    private Label lblStatus;

    public SplashScreenView(Stage stage, SplashScreenModel model) {
        super(stage, model);
        stage.initStyle(StageStyle.TRANSPARENT); // also undecorated
    }

    @Override
    protected Scene create_GUI() {
        BorderPane root = new BorderPane();
        root.setId("splash");

        lblStatus = new Label("Woof");
        root.setCenter(lblStatus);

        progress = new ProgressBar();
        HBox bottomBox = new HBox();
        bottomBox.setId("progressbox");
        bottomBox.getChildren().add(progress);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 300, 300, Color.TRANSPARENT);
        scene.getStylesheets().addAll(this.getClass().getResource("/splash.css").toExternalForm());

        return scene;
    }
}


