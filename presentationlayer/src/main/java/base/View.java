package base;

import alert.DominionAlert;
import com.weddingcrashers.service.Language;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import menu.DominionMenuBar;

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


    public View() {

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
     * @param contentTextKey the key of the content text.
     * @param alertType the alert type.
     */
    public DominionAlert alert(String contentTextKey, Alert.AlertType alertType){
        return new DominionAlert(contentTextKey, alertType, translator);
    }

    public void simpleAlert(String msg, Alert.AlertType alertType){
        Alert alert = new Alert(alertType, msg);
        alert.showAndWait();
    }

    /**
     * Creates an alert for the given alert type and message.
     *
     * @param contentTextKey the key of the content text.
     * @param headerTextKey the key of the header text.
     * @param alertType the alert type.
     */
    public DominionAlert alert(String contentTextKey, String headerTextKey, Alert.AlertType alertType){
        return new DominionAlert(contentTextKey, headerTextKey, alertType, translator);
    }

    /**
     * Creates an alert for the given alert type and message.
     *
     * @param contentTextKey the key of the content text.
     * @param headerTextKey the key of the header text.
     * @param titleTextKey the key of the title text.
     * @param alertType the alert type.
     */
    public DominionAlert alert(String contentTextKey, String headerTextKey, String titleTextKey, Alert.AlertType alertType){
        return new DominionAlert(contentTextKey, headerTextKey, titleTextKey, alertType, translator);
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
        menuBar.setMenuTexts(translator);
    }

    public void setMenuBarUsed(boolean menuBarUsed) {
        this.menuBarUsed = menuBarUsed;
    }

}
