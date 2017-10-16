package base;

import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * View base model.
 *
 * @param <M> the type of the model.
 */
public abstract class View<M extends Model> {

    // ---- Members

    /** The stage. */
    protected Stage stage;
    /** The scene. */
    protected Scene scene;
    /** The model. */
    protected M model;
    /** The service locator. */
    protected ServiceLocator serviceLocator;
    /** The translator. */
    protected Translator translator;

    // ---- Constructor

    /**
     * Set any options for the stage in the subclass constructor
     *
     * @param stage the stage.
     * @param model the model.
     */
    protected View(Stage stage, M model) {
        this.stage = stage;
        this.model = model;

        serviceLocator = ServiceLocator.getServiceLocator();
        translator = serviceLocator.getTranslator();
        scene = create_GUI(); // Create all controls within "root"
        stage.setScene(scene);
    }


    // ---- Methods

    protected abstract Scene create_GUI();
    protected abstract void setTexts();

    /**
     * Display the view
     */
    public void start() {
        stage.show();
    }

    /**
     * Hide the view
     */
    public void stop() {
        stage.hide();
    }

    /**
     * Getter for the stage, so that the controller can access window events
     */
    public Stage getStage() {
        return stage;
    }

    public void alert(String msg, Alert.AlertType alertType){
        Alert alert = new Alert(alertType, msg);
        alert.showAndWait();
    }

    /**
     * Switch the translator to the corresponding locale.
     *
     * @param locale the locale to switch the translator for.
     */
    public void switchTranslator(String locale) {
        serviceLocator.setTranslator(locale);
        setTexts();
    }

}
