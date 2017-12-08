package Game;



 /*@author Murat Kelleci - 3.12.2017 -ausgelagert aus GameView
 */

import java.io.Serializable;

import com.weddingcrashers.servermodels.WinningInformation;

public class WinningUser implements Comparable<WinningUser>, Serializable{

    private long userId;
    private String userName;
    private int points, position;

    public WinningUser(long userId, String userName, int points, int position) {
        this.userId = userId;
        this.userName = userName;
        this.points = points;
        this.position = position;
    }

    public long getUserId() {
        return userId;
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

    @Override
    public int compareTo(WinningUser o) {
        return new Integer(this.points).compareTo(o.points);
    }

}
