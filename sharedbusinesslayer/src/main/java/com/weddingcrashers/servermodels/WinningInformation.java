package com.weddingcrashers.servermodels;

import java.io.Serializable;

public class WinningInformation implements Comparable<WinningInformation>, Serializable{
    int clientId;
    int userId;
    int points;
    int position;

    public WinningInformation(int clientId,int userId,int points){
        this.clientId = clientId;
        this.userId = userId;
        this.points = points;
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


    @Override
    public int compareTo(WinningInformation o) {
        return new Integer(this.points).compareTo(o.points);
    }
}
