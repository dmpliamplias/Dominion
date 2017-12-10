package com.weddingcrashers.managers;

import com.weddingcrashers.server.Client;
import com.weddingcrashers.service.ServiceLocator;

/**
 * @author Michel Schlatter
 */
public class Manager {

    protected final ServiceLocator serviceLocator = ServiceLocator.getServiceLocator();
    Client client;
    public Manager(Client client){
        this.client = client;
    }

}
