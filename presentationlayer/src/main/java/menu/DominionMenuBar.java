package menu;

import base.View;
import com.weddingcrashers.service.Translator;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Dominion menu bar.
 *
 * @author Vanessa Cajochen
 */
public class DominionMenuBar extends MenuBar {

    // ---- Members

    private Menu menuSettings;
    private Menu menuLanguage;
    private Menu menuMusic;
    private Menu menuSound;
    private MenuItem menuItemHelp;
    private MenuItem menuItemDE;
    private MenuItem menuItemENG;
    private MenuItem menuItemCH;
    private ToggleGroup toggleGroupMusic;
    private ToggleGroup toggleGroupSound;
    private RadioMenuItem menuItemMusicMute;
    private RadioMenuItem menuItemMusicUnmute;
    private RadioMenuItem menuItemSoundMute;
    private RadioMenuItem menuItemSoundUnmute;

    private ImageView imgViewEngFlag;
    private ImageView imgViewChFlag;
    private ImageView imgViewDeFlag;
    private ImageView imgViewMute;
    private ImageView imgViewUnmute;

    private Translator translator;


    // ---- Constructor

    /**
     * Constructor.
     *
     * @param view the view.
     * @param translator the translator.
     */
    public DominionMenuBar(View view, Translator translator) {
       this.translator = translator;

        // Base menu
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

        this.getMenus().add(menuSettings);
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
        view.setMenuBarUsed(true);
    }

    /**
     * Sets the menu texts.
     */
    public void setMenuTexts() {
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

    /**
     * Sets the image view size.
     *
     * @param imgV the image view.
     */
    private void setIconSize(ImageView imgV){
        imgV.setFitHeight(20);
        imgV.setFitWidth(20);
    }

    /**
     * Gets the value string for the given key.
     *
     * @param key the key to get the value for.
     * @return the value for given key.
     */
    private String getText(String key) {
        return translator.getString(key);
    }

    public Menu getMenuSettings() {
        return menuSettings;
    }

    public Menu getMenuLanguage() {
        return menuLanguage;
    }

    public Menu getMenuSound() {
        return menuSound;
    }

    public MenuItem getMenuItemDE() {
        return menuItemDE;
    }

    public MenuItem getMenuItemENG() {
        return menuItemENG;
    }

    public MenuItem getMenuItemCH() {
        return menuItemCH;
    }

    public ImageView getImgViewEngFlag() {
        return imgViewEngFlag;
    }

    public ImageView getImgViewChFlag() {
        return imgViewChFlag;
    }

    public ImageView getImgViewDeFlag() {
        return imgViewDeFlag;
    }

    public ImageView getImgViewMute() {
        return imgViewMute;
    }

    public ImageView getImgViewUnmute() {
        return imgViewUnmute;
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
