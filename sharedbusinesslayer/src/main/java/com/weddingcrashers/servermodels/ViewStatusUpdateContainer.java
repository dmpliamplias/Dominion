package com.weddingcrashers.servermodels;

/**
 * @author  Michel Schlatter
 */
public class ViewStatusUpdateContainer extends Container {
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
