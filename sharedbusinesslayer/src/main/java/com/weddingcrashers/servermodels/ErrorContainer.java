package com.weddingcrashers.servermodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public class ErrorContainer extends Container implements Serializable {
    private String error;
    private int errorCode;

    public String getError() {
        return error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorContainer(String error) {
        super(Methods.Client_Server_Error);
        this.error = error;
    }
}
