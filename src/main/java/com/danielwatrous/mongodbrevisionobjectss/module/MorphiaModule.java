/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjectss.module;

import com.danielwatrous.mongodbrevisionobjectss.persistence.dao.PersonDAO;
import com.danielwatrous.mongodbrevisionobjectss.persistence.dao.morphia.MorphiaPersonDAO;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author watrous
 */
public class MorphiaModule extends AbstractModule {
    private Properties properties = new Properties();

    @Override
    protected void configure() {
        try {
            properties.load(MorphiaModule.class.getClassLoader().getResourceAsStream("mongo.properties"));
            Names.bindProperties(binder(), properties);
        } catch (IOException ex) {
            // ah crap
        }
        
        bind(PersonDAO.class).to(MorphiaPersonDAO.class).in(Singleton.class);
    }
}
