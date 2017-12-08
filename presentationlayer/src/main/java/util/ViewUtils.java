package util;

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
        if (id == 5){
            color = Color.PINK;
        }
        if (id == 6){
            color = Color.ORANGE;
        }
        if (id == 7){
            color = Color.BROWN;
        }
        if (id == 8){
            color = Color.DARKGREY;
        }

        return color;
    }
}
