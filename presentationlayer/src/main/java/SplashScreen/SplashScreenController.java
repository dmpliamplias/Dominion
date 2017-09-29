package splashscreen;

import com.weddingcrashers.Controller;
import javafx.concurrent.Worker;
import app.Dominion;

/**
 * Framework for professional applications:
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards.
 * All rights reserved. This code 5 is licensed under the terms of the BSD 3-clause license
 * For more details please see the file "license.txt").
 *  @author Brad Richards, small changes from Michel Schlatter
 */

public class SplashScreenController extends Controller<SplashScreenModel, SplashScreenView> {

    public SplashScreenController(final Dominion main, SplashScreenModel model, SplashScreenView view){
        super(model,view);
        view.progress.progressProperty().bind(model.initializer.progressProperty());

        model.initializer.stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED)
                main.startApp();
        });
    }
}
