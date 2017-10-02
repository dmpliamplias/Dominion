package com.weddingcrashers.servermodels;
/**
 *  @author Michel Schlatter
 *  */
public abstract class Container {
    private Methods method;

    public Container(Methods method){
        this.method = method;
    }
    public Methods getMethod() {
        return method;
    }




}
