package com.weddingcrashers.businessmodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public class PointCard extends Card implements Serializable {

    PointCardType pointCardType;
    int value;


    public PointCardType getPointCardType() {
        return pointCardType;
    }

    public void setPointCardType(PointCardType pointCardType) {
        this.pointCardType = pointCardType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
