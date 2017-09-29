package com.weddingcrashers.servermodels;

public class ErrorContainer extends Container {
    private String error;

    public ErrorContainer(String error) {
        this.error = error;
        this.setMethod(Methods.Client_Server_Error);
    }
}
