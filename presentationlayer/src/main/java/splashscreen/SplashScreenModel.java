package splashscreen;

import base.Model;
import com.weddingcrashers.service.ServiceLocator;
import javafx.concurrent.Task;

import static java.lang.Thread.sleep;

/**
 * Framework for professional applications:
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards.
 * All rights reserved. This code 5 is licensed under the terms of the BSD 3-clause license
 * For more details please see the file "license.txt").
 * 
 * @author Brad Richards
 */
public class SplashScreenModel extends Model {

    // ---- Members
    
    /** The service locator. */
    private ServiceLocator serviceLocator;

    
    // ---- Constructor

    /**
     * Constructor.
     */
    public SplashScreenModel(ServiceLocator serviceLocator) {
        super();
        
        this.serviceLocator = serviceLocator;
    }

    final Task<Void> initializer = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            // initial progress update
            updateProgress(1, 3);

            // db start progress update
            serviceLocator.startDatabase();
            updateProgress(2, 3);

            // pseudo 1.5 second
            sleep(1500);
            updateProgress(3, 3);

            return null;
        }
    };


    // ---- Methods

    public void initialize() {
        new Thread(initializer).start();
    }

}
