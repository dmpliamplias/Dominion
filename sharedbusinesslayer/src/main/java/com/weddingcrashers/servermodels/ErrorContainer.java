package com.weddingcrashers.servermodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public class ErrorContainer extends Container implements Serializable {
    private String error;

    public String getError() {
        return error;
    }

    public ErrorContainer(String error) {
        super(Methods.Client_Server_Error);
        this.error = error;
    }
}
