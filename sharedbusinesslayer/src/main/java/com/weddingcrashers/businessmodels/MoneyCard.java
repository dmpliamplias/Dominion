package com.weddingcrashers.businessmodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public class MoneyCard  extends Card implements Serializable {
    MoneyType moneyType;
    int value;

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}

