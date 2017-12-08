package base;

import com.weddingcrashers.service.Language;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import menu.DominionMenuBar;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

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
    /** The image icons. */
    protected Image imgIcon = new Image(getClass().getResourceAsStream("/base/castle.png"));
    /** The menu bar. */
    public DominionMenuBar menuBar;
    /** Menu bar used indicator. */
    protected boolean menuBarUsed = false;


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
        menuBar = new DominionMenuBar(this, translator);

        scene = create_GUI();
        setTexts();

        stage.setScene(scene);
        stage.getIcons().add(imgIcon);
    }


    // ---- Methods

    /**
     * Creates the GUI.
     *
     * @return the scene.
     */
    protected abstract Scene create_GUI();

    /**
     * Sets the texts.
     */
    protected abstract void setTexts();

    /**
     * Gets the text from the translation file.
     *
     * @param key the key to get the value for.
     * @return the value for the key.
     */
    protected String getText(String key) {
        return translator.getString(key);
    }

    /**
     * Sets the style sheet to the given scene.
     *
     * @param scene the scene to set the style.
     * @param pathToCss the path to the style sheet.
     */
    protected void setStylesheet(Scene scene, String pathToCss) {
        scene.getStylesheets().addAll(this.getClass().getResource(pathToCss).toExternalForm());
    }

    /**
     * Display the view.
     */
    public void start() {
        stage.show();
    }

    /**
     * Hide the view.
     */
    public void stop() {
        stage.hide();
    }

    /**
     * Getter for the stage, so that the controller can access window events.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Creates an alert for the given alert type and message.
     *
     * @param msg the message.
     * @param alertType the alert type.
     */
    public void alert(String msg, Alert.AlertType alertType){
        Alert alert = new Alert(alertType, msg);
        alert.getDialogPane().setMinHeight(USE_PREF_SIZE);
        alert.showAndWait();
    }

    /**
     * The language to switch the translator for.
     *
     * @param language the language to switch.
     */
    public void switchTranslator(Language language) {
        serviceLocator.setTranslator(language);
        translator = serviceLocator.getTranslator();

        setTexts();
        menuBar.setMenuTexts();
    }

    protected DominionMenuBar getMenuBar() {
        this.menuBarUsed = true;
        return menuBar;
    }

    public void setMenuBarUsed(boolean menuBarUsed) {
        this.menuBarUsed = menuBarUsed;
    }

}
