package com.weddingcrashers.businessmodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public class Card implements Serializable {
    String name;
    int Cost;
    String filePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
