package com.weddingcrashers.managers;

import com.weddingcrashers.server.Client;
import com.weddingcrashers.service.ServiceLocator;

/**
 * @author Michel Schlatter
 */
public class Manager {

    // ---- Members

    /** The service locator. */
    protected final ServiceLocator serviceLocator = ServiceLocator.getServiceLocator();

    Client client;

    // ---- Constructor

    /**
     * Constructor.
     *
     * @param client the client.
     */
    public Manager(Client client){
        this.client = client;
    }

}
