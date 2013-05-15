/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects;

import com.danielwatrous.mongodbrevisionobjects.factory.HistoricalPersonFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.PersonFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.PersonNameFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.VersionedPersonFactory;
import com.danielwatrous.mongodbrevisionobjects.model.HistoricalPerson;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.module.MorphiaModule;
import com.google.code.morphia.Datastore;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author watrous
 */
public class CreateData {

    public static void main(String[] args) {
        System.out.println("Populate Versioned Objects in MongoDB!");
        Injector injector = Guice.createInjector(new MorphiaModule());

        // create some person objects
        PersonNameFactory personNameFactory = injector.getInstance(PersonNameFactory.class);
        PersonFactory personFactory = injector.getInstance(PersonFactory.class);
        HistoricalPersonFactory historicalPersonFactory = injector.getInstance(HistoricalPersonFactory.class);
        VersionedPersonFactory versionedPersonFactory = injector.getInstance(VersionedPersonFactory.class);

        Person publishedPerson = personFactory.create(personNameFactory.create("Daniel", "Watrous"), 32, "daniel@current.com", true);
        Person draftPerson = personFactory.create(personNameFactory.create("Daniel", "Watrous"), 33, "daniel@future.com", true);
        HistoricalPerson history1Person = historicalPersonFactory.create(personFactory.create(personNameFactory.create("Danny", "Watrous"), 23, "daniel@oldschool.com", false));
        HistoricalPerson history2Person = historicalPersonFactory.create(personFactory.create(personNameFactory.create("Dan", "Watrous"), 33, "daniel@beforeinternet.com", true));

        Map<String, HistoricalPerson> history = new HashMap<String, HistoricalPerson>();
        history.put("1", history1Person);
        history.put("2", history2Person);
        
        VersionedPerson versionedPerson = versionedPersonFactory.create(publishedPerson, draftPerson, history);
        
        Datastore ds = injector.getInstance(Key.get(Datastore.class, Names.named("peopleDatabase")));
        ds.save(versionedPerson);
        
    }
}
