package com.weddingcrashers.businessmodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public class Card implements Serializable {
    String name;
    int cost;
    String filePath;
    int value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
