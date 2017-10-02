package com.weddingcrashers.servermodels;

import java.io.Serializable;

/**
 * @author  Michel Schlatter
 */
public class ViewStatusUpdateContainer extends Container implements Serializable {
    ViewStatus viewStatus;

    public ViewStatusUpdateContainer(){
        super(Methods.SetViewStatus);
    }

    public ViewStatus getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(ViewStatus view) {
        this.viewStatus = view;
    }
}
