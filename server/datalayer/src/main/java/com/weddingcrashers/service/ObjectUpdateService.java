package com.weddingcrashers.service;

import java.util.List;

/**
 * Object update service. Service for creating, updating and perform
 * other actions on entities.
 *
 * @author dmpliamplias
 */
interface ObjectUpdateService {

    /**
     * Creates a new entity.
     *
     * @param entity the entity to create.
     */
    void create(Object entity);

    /**
     * Updates the given entity.
     *
     * @param entity the entity to update.
     */
    void update(Object entity);

    /**
     * Deletes the data for the given id and class.
     *
     * @param id the id of the data to delete.
     * @param clazz the corresponding class.
     */
    void delete(long id, Class clazz);

    /**
     * Lists all data for the given class.
     *
     * @param clazz the class to list.
     * @param <T> the class type.
     * @return all data for the given class.
     */
    <T> List<T> list(Class clazz);

}
