package splashscreen;

import base.Model;
import com.weddingcrashers.service.ServiceLocator;
import javafx.concurrent.Task;

/**
 * Framework for professional applications:
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards.
 * All rights reserved. This code 5 is licensed under the terms of the BSD 3-clause license
 * For more details please see the file "license.txt").
 *  @author Brad Richards, small changes from Michel Schlatter
 */
public class SplashScreenModel extends Model {
    ServiceLocator serviceLocator;

    public SplashScreenModel() {
        super();
    }

    // A task is a JavaFX class that implements Runnable. Tasks are designed to
    // have attached listeners, which we can use to monitor their progress.
    final Task<Void> initializer = new Task<Void>() {
        @Override
        protected Void call() throws Exception {

            // Create the service locator to hold our resources
            serviceLocator = ServiceLocator.getServiceLocator();

            //this.updateProgress(1,  6);
            //String language = serviceLocator.getConfiguration().getOption("Language");
            //serviceLocator.setTranslator(new Translator(language));


            // delete this and add real code!
            Integer i = 0;
            for(; i < 10000; i++) {
                    this.updateProgress(i, 10000);
            }

            return null;
        }
    };

    public void initialize() {
        new Thread(initializer).start();
    }

}
