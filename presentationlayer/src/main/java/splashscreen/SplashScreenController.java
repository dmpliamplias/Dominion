package splashscreen;

import app.Dominion;
import base.Controller;
import javafx.concurrent.Worker;

/**
 * Framework for professional applications:
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards.
 * All rights reserved. This code 5 is licensed under the terms of the BSD 3-clause license
 * For more details please see the file "license.txt").
 *
 * @author Brad Richards
 */
public class SplashScreenController extends Controller<SplashScreenModel, SplashScreenView> {

    // ---- Constructor

    /**
     * Constructor
     *
     * @param main the main program.
     * @param model the model.
     * @param view the view.
     */
    public SplashScreenController(Dominion main, SplashScreenModel model, SplashScreenView view) {
        super(model,view);
        view.progress.progressProperty().bind(model.initializer.progressProperty());

        model.initializer.stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED)
                main.startApp();
        });
    }

}
