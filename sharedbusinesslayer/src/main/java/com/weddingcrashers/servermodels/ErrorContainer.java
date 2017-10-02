package com.weddingcrashers.servermodels;
/**
 *  @author Michel Schlatter
 *  */
public class ErrorContainer extends Container {
    private String error;

    public String getError() {
        return error;
    }

    public ErrorContainer(String error) {
        super(Methods.Client_Server_Error);
        this.error = error;
    }
}
