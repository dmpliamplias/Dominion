package app;

import javafx.scene.paint.Color;

/**
 * author Michel Schlatter
 */
public class ViewUtils {

    /**
     * author Michel Schlatter
     * @param id
     * @return
     */
    public static Color getColorByClientId(int id) {

        Color color = Color.WHITE;

        if (id == 1) {
            color = Color.BLUE;
        }
        if (id == 2) {
            color = Color.RED;
        }
        if (id == 3) {
            color = Color.PURPLE;
        }
        if (id == 4) {
            color = Color.GREEN;
        }
        return color;
    }
}
