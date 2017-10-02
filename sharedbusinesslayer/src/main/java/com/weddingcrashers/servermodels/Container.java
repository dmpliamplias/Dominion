package com.weddingcrashers.servermodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public abstract class Container implements Serializable {
    private Methods method;

    public Container(Methods method){
        this.method = method;
    }
    public Methods getMethod() {
        return method;
    }




}
