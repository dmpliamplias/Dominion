package com.weddingcrashers.businessmodels;

import java.util.ArrayList;

/**
 * @author  Michel Schlatter
 */
public class DominionSet {
    ArrayList<Card> trayStack; // Ablagestappel
    ArrayList<Card> pullStack; // Ziehstappel

    public ArrayList<Card> getTrayStack() {
        return trayStack;
    }

    public void setTrayStack(ArrayList<Card> trayStack) {
        this.trayStack = trayStack;
    }

    public ArrayList<Card> getPullStack() {
        return pullStack;
    }

    public void setPullStack(ArrayList<Card> pullStack) {
        this.pullStack = pullStack;
    }




}
