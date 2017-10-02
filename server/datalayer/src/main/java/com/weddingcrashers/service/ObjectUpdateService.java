package com.weddingcrashers.service;

import com.weddingcrashers.model.BaseEntity;

import java.util.List;

/**
 * Object update service. Service for creating, updating and perform
 * other actions on entities.
 *
 * @author dmpliamplias
 * @param <T> the type of the entity.
 */
interface ObjectUpdateService<T extends BaseEntity> {

    /**
     * Creates a new entity.
     *
     * @param entity the entity to create.
     */
    T create(T entity);

    /**
     * Updates the given entity.
     *
     * @param entity the entity to update.
     * @return {@code true} if the entity was updated, {@code false} otherwise.
     */
    T update(T entity);

    /**
     * Deletes the given entity.
     *
     * @param entity the entity to delete.
     * @return {@code true} if the entity was deleted, {@code false} otherwise.
     */
    boolean delete(T entity);

    /**
     * Lists all entries the given entity class.
     *
     * @param clazz the entity class to list.
     * @return all entries.
     */
    List<T> list(Class clazz);

}
