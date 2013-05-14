/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.module;

import com.danielwatrous.mongodbrevisionobjects.factory.PersonFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.PersonNameFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.VersionedPersonFactory;
import com.danielwatrous.mongodbrevisionobjects.model.DisplayMode;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.model.morphia.MorphiaPerson;
import com.danielwatrous.mongodbrevisionobjects.model.morphia.MorphiaPersonName;
import com.danielwatrous.mongodbrevisionobjects.model.morphia.MorphiaVersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.module.providers.MongoConnectionProvider;
import com.danielwatrous.mongodbrevisionobjects.module.providers.MorphiaProvider;
import com.danielwatrous.mongodbrevisionobjects.persistence.dao.PersonDAO;
import com.danielwatrous.mongodbrevisionobjects.persistence.dao.morphia.MorphiaPersonDAO;
import com.danielwatrous.mongodbrevisionobjects.state.CurrentDisplayMode;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.mongodb.Mongo;
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
        install(new FactoryModuleBuilder().implement(Person.class, MorphiaPerson.class).build(PersonFactory.class));
        install(new FactoryModuleBuilder().implement(Person.PersonName.class, MorphiaPersonName.class).build(PersonNameFactory.class));
        install(new FactoryModuleBuilder().implement(VersionedPerson.class, MorphiaVersionedPerson.class).build(VersionedPersonFactory.class));

        bind(PersonDAO.class).to(MorphiaPersonDAO.class).in(Singleton.class);
        bind(DisplayMode.class).to(CurrentDisplayMode.class).in(Singleton.class);

        bind(Mongo.class).toProvider(MongoConnectionProvider.class).in(Singleton.class);
        bind(Morphia.class).toProvider(MorphiaProvider.class).in(Singleton.class);
        bind(Datastore.class).annotatedWith(Names.named("peopleDatabase")).toProvider(DocumentsDatabaseDatastoreProvider.class).in(Singleton.class);
    }
    
    static class DocumentsDatabaseDatastoreProvider implements Provider<Datastore> {

        @Inject private Mongo mongo;
        @Inject private Morphia morphia;
        @Inject @Named("mongodbPeopleDatabaseName") private String mongodbDocumentsDatabaseName;
        
        public DocumentsDatabaseDatastoreProvider() {
            // pass
        }
        
        public Datastore get() {
            final Datastore ds = morphia.createDatastore(mongo, mongodbDocumentsDatabaseName);
            return ds;
        }
    }
}
