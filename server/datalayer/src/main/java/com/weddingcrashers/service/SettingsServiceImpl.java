package com.weddingcrashers.service;

import com.weddingcrashers.model.Settings;
import com.weddingcrashers.model.User;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Implements {@link SettingsService}.
 *
 * @author dmpliamplias
 */
public class SettingsServiceImpl extends BaseService implements SettingsService {

    // ---- Members

    /** The object update service. */
    @SuppressWarnings("unchecked")
    private final ObjectUpdateService<Settings> objectUpdateService = new ObjectUpdateServiceImpl();


    // ---- Methods

    @Override
    public Settings create(final Settings settings) {
        notNull(settings);

        return objectUpdateService.create(settings);
    }

    @Override
    public Settings loadSettingsFor(final User user) {
        // TODO: 30.09.2017 query that
        return null;
    }

    @Override
    protected Class getSubClass() {
        return SettingsServiceImpl.class;
    }

}
