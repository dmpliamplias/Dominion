package com.weddingcrashers.businessmodels;
/**
 *  @author Michel Schlatter
 *  */
public class MoneyCard  extends Card {
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

