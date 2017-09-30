package gamestart;

import base.Model;
import com.weddingcrashers.model.Settings;

/**
 *  author Manuel Wirz
 *  */

public class GameStartModel extends Model {
    private Settings settings;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

}
