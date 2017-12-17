package com.weddingcrashers.servermodels;

import java.io.Serializable;
/***
 * @Author Michel Schlatter
 */
public class WinningInformation implements Comparable<WinningInformation>, Serializable {

    private int clientId;
    private int userId;
    private int points;
    private int position;
    private int newPosition;
    private String username;

    public WinningInformation(int clientId, int userId, String username, int points){
        this.clientId = clientId;
        this.userId = userId;
        this.points = points;
        this.username = username;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(final int newPosition) {
        this.newPosition = newPosition;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public int compareTo(WinningInformation o) {
        return (new Integer(this.getPoints()).compareTo(o.getPoints()) * -1);
    }
}
