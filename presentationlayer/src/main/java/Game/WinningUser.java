package Game;


 /*@author Murat Kelleci - 3.12.2017 -ausgelagert aus GameView
 */

public class WinningUser {

    private String userName;
    private int points, position;

    public WinningUser(String userName, int points, int position) {
        this.userName = userName;
        this.points = points;
        this.position = position;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
