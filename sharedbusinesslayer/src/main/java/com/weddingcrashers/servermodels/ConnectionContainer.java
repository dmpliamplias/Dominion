package com.weddingcrashers.servermodels;
/**
 *  @author Michel Schlatter
 *  */
public class ConnectionContainer extends Container {
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
