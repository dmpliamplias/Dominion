package base;

import com.weddingcrashers.service.Language;
import com.weddingcrashers.service.ServiceLocator;
import com.weddingcrashers.service.Translator;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    /** Image Icon */
    protected Image imgIcon = new Image(getClass().getResourceAsStream("/base/castle.png"));

    /** Menu */
    protected MenuBar menuBar;
    protected Menu menuSettings;
    protected Menu menuLanguage;
    protected Menu menuMusic;
    protected Menu menuSound;
    protected MenuItem menuItemHelp;
    protected MenuItem menuItemDE;
    protected MenuItem menuItemENG;
    protected MenuItem menuItemCH;
    protected ToggleGroup toggleGroupMusic;
    protected ToggleGroup toggleGroupSound;
    protected RadioMenuItem menuItemMusicMute;
    protected RadioMenuItem menuItemMusicUnmute;
    protected RadioMenuItem menuItemSoundMute;
    protected RadioMenuItem menuItemSoundUnmute;

    protected ImageView imgViewEngFlag;
    protected ImageView imgViewChFlag;
    protected ImageView imgViewDeFlag;
    protected ImageView imgViewMute;
    protected ImageView imgViewUnmute;
    protected Boolean menuBarUsed;


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
        this.menuBarUsed = new Boolean( false );

        serviceLocator = ServiceLocator.getServiceLocator();
        translator = serviceLocator.getTranslator();
        scene = create_GUI(); // Create all controls within "root"
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
        this.translator = serviceLocator.getTranslator();
        setTexts();
        setMenuTexts();

    }

    /**
     *  @author Vanessa Cajochen
     *  */

    public MenuBar getMenuBar() {

        menuBar = new MenuBar();
        menuSettings = new Menu();
        menuLanguage = new Menu();
        menuItemDE = new MenuItem();
        menuItemENG = new MenuItem();
        menuItemCH = new MenuItem();

        // Music Menu
        menuMusic = new Menu();
        toggleGroupMusic = new ToggleGroup();
        menuItemMusicMute = new RadioMenuItem();
        menuItemMusicUnmute = new RadioMenuItem();
        menuItemMusicMute.setToggleGroup(toggleGroupMusic);
        menuItemMusicUnmute.setToggleGroup(toggleGroupMusic);
        menuItemMusicUnmute.setSelected(true);

        // Sound Menu
        menuSound = new Menu();
        toggleGroupSound = new ToggleGroup();
        menuItemSoundMute = new RadioMenuItem();
        menuItemSoundUnmute = new RadioMenuItem();
        menuItemSoundMute.setToggleGroup(toggleGroupSound);
        menuItemSoundUnmute.setToggleGroup(toggleGroupSound);
        menuItemSoundUnmute.setSelected(true);

        // Help Menu
        menuItemHelp = new MenuItem();

        menuBar.getMenus().add(menuSettings);
        menuSettings.getItems().addAll(menuLanguage, menuMusic, menuSound, menuItemHelp);
        menuLanguage.getItems().addAll(menuItemCH, menuItemDE, menuItemENG);
        menuMusic.getItems().addAll(menuItemMusicUnmute, menuItemMusicMute);
        menuSound.getItems().addAll(menuItemSoundUnmute, menuItemSoundMute);




        // Create Language Icons
        imgViewDeFlag = new ImageView(new Image(getClass().getResourceAsStream("/connection/germanFlag.png")));
        setIconSize(imgViewDeFlag);

        imgViewChFlag = new ImageView(new Image(getClass().getResourceAsStream("/connection/swissFlag.png")));
        setIconSize(imgViewChFlag);

        imgViewEngFlag = new ImageView (new Image(getClass().getResourceAsStream("/connection/englishFlag.png")));
        setIconSize(imgViewEngFlag);


        // Create mute/unmute icons
        imgViewMute = new ImageView(new Image(getClass().getResourceAsStream("/base/mute.png")));
        setIconSize(imgViewMute);

        imgViewUnmute = new ImageView(new Image(getClass().getResourceAsStream("/base/unmute.png")));
        setIconSize(imgViewUnmute);


        setMenuTexts();

        menuBarUsed = true;

        return menuBar;
    }

    private void setIconSize(ImageView imgV){
        imgV.setFitHeight(20);
        imgV.setFitWidth(20);
    }

    protected void setMenuTexts() {
        menuSettings.setText(getText("menu.menuSetting"));
        menuLanguage.setText(getText("menu.menuLanguage"));
        menuMusic.setText(getText("menu.menuMusic"));
        menuSound.setText(getText("menu.menuSound"));
        menuItemHelp.setText(getText("connectionview.btnHelp"));
        menuItemDE.setText(getText("menu.languageDe"));
        menuItemDE.setGraphic(imgViewDeFlag);
        menuItemENG.setText(getText("menu.languageEng"));
        menuItemENG.setGraphic(imgViewEngFlag);
        menuItemCH.setText(getText("menu.languageCh"));
        menuItemCH.setGraphic(imgViewChFlag);

        menuItemMusicUnmute.setText(getText("menu.unMute"));
        menuItemMusicUnmute.setGraphic(imgViewUnmute);
        menuItemMusicMute.setText(getText("menu.mute"));
        menuItemMusicMute.setGraphic(imgViewMute);

        menuItemSoundUnmute.setText(getText("menu.unMute"));
        menuItemSoundUnmute.setGraphic(imgViewUnmute);
        menuItemSoundMute.setText(getText("menu.mute"));
        menuItemSoundMute.setGraphic(imgViewMute);
    }

    public RadioMenuItem getMenuItemMusicMute() {
        return menuItemMusicMute;
    }

    public RadioMenuItem getMenuItemMusicUnmute() {
        return menuItemMusicUnmute;
    }

    public RadioMenuItem getMenuItemSoundMute() {
        return menuItemSoundMute;
    }

    public RadioMenuItem getMenuItemSoundUnmute() {
        return menuItemSoundUnmute;
    }
}
