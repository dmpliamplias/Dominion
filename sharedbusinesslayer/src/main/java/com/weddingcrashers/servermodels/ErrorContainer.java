package com.weddingcrashers.servermodels;
/**
 *  @author Michel Schlatter
 *  */
public class ErrorContainer extends Container {
    private String error;

    public ErrorContainer(String error) {
        this.error = error;
        this.setMethod(Methods.Client_Server_Error);
    }
}
