package com.weddingcrashers.servermodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public class ConnectionContainer extends Container implements Serializable {
    int id;

    public ConnectionContainer(){
        super(Methods.Connect);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
