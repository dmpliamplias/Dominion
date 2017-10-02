package app;

import com.weddingcrashers.model.User;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.UserService;
import connection.ConnectionController;
import connection.ConnectionModel;
import connection.ConnectionView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginModel;
import login.LoginView;
import splashscreen.SplashScreenController;
import splashscreen.SplashScreenModel;
import splashscreen.SplashScreenView;

/**
 * Framework for professional applications:
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards.
 * All rights reserved. This code 5 is licensed under the terms of the BSD 3-clause license
 * For more details please see the file "license.txt").
 *  @author Brad Richards, small changes from Michel Schlatter
 */

public class Dominion extends Application {

    private static Dominion mainProgram; // singleton
    private SplashScreenView splashView;
    private ConnectionView view;

    private ServiceLocator serviceLocator; // resources, after initialization

    /**
     * Note: This method is called on the main thread, not the JavaFX
     * Application Thread. This means that we cannot display anything to the
     * user at this point. Since we want to show a splash screen, this means
     * that we cannot do any real initialization here.
     *
     * This implementation ensures that the application is a singleton; only one
     * per JVM-instance. On client installations this is not necessary (each
     * application runs in its own JVM). However, it can be important on server
     * installations.
     *
     * Why is it important that only one instance run in the JVM? Because our
     * initialized resources are a singleton - if two programs instances were
     * running, they would use (and overwrite) each other's resources!
     */
    @Override
    public void init() {
        if (mainProgram == null) {
            mainProgram = this;
        } else {
            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method is called after init(), and is called on the JavaFX
     * Application Thread, so we can display a GUI. We have two GUIs: a splash
     * screen and the application. Both of these follow the MVC model.
     *
     * We first display the splash screen. The model is where all initialization
     * for the application takes place. The controller updates a progress-bar in
     * the view, and (after initialization is finished) calls the startApp()
     * method in this class.
     */
    @Override
    public void start(Stage primaryStage) {
        // Create and display the splash screen and model
        SplashScreenModel splashModel = new SplashScreenModel();
        splashView = new SplashScreenView(primaryStage, splashModel);
        new SplashScreenController(this, splashModel, splashView);
        splashView.start();

        // Display the splash screen and begin the initialization
        splashModel.initialize();
    }

    /**
     * This method is called when the splash screen has finished initializing
     * the application. The initialized resources are in a PLServiceLocator
     * singleton. Our task is to now create the application MVC components, to
     * hide the splash screen, and to display the application GUI.
     *
     * Multitasking note: This method is called from an event-handler in the
     * Splash_Controller, which means that it is on the JavaFX Application
     * Thread, which means that it is allowed to work with GUI components.
     * http://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
     */
    public void startApp() {
        Stage appStage = new Stage();

        // Initialize the application MVC components. Note that these components
        // can only be initialized now, because they may depend on the
        // resources initialized by the splash screen
        ConnectionModel model = new ConnectionModel();
        view = new ConnectionView(appStage, model);
        new ConnectionController(model,view);

        // Resources are now initialized
        serviceLocator = ServiceLocator.getServiceLocator();
        final User user = new User();
        user.setUserEmail("gaga@g.com");
        user.setUserName("BéééééééééG");
        user.setPassword("3wadawdawdawd");
        final UserService userService = ServiceLocator.getUserService();
        userService.create(user);

        // Close the splash screen, and set the reference to null, so that all
        // Splash_XXX objects can be garbage collected
        splashView.stop();
        splashView = null;

        view.start();
    }

    /**
     * The stop method is the opposite of the start method. It provides an
     * opportunity to close down the program, including GUI components. If the
     * start method has never been called, the stop method may or may not be
     * called.
     *
     * Make the GUI invisible first. This prevents the user from taking any
     * actions while the program is ending.
     */
    @Override
    public void stop() {
//        serviceLocator.getConfiguration().save();
        if (view != null) {
            // Make the view invisible
            view.stop();
        }

        serviceLocator.getLogger().info("Application terminated");
    }

    // Static getter for a reference to the main program object
    protected static Dominion getMainProgram() {
        return mainProgram;
    }
}
