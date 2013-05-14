/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.module.providers;

import com.google.code.morphia.Morphia;
import com.google.inject.Provider;

/**
 *
 * @author watrous
 */
public class MorphiaProvider implements Provider<Morphia> {

    public MorphiaProvider() {
        // pass
    }

    public Morphia get() {
        final Morphia morphia = new Morphia();
        return morphia;
    }
}
