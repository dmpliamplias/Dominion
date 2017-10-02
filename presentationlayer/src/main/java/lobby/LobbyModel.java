package lobby;

import base.Model;
import com.weddingcrashers.model.Settings;

/**
 *  author Manuel Wirz
 *  */

public class LobbyModel extends Model {
    private Settings settings;

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

}
