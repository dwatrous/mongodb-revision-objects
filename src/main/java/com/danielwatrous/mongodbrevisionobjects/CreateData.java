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
import com.danielwatrous.mongodbrevisionobjects.model.Person.PersonName;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.module.MorphiaModule;
import com.danielwatrous.mongodbrevisionobjects.persistence.dao.PersonDAO;
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
        PersonDAO personDao = injector.getInstance(PersonDAO.class);
        
        PersonNameFactory personNameFactory = injector.getInstance(PersonNameFactory.class);
        PersonFactory personFactory = injector.getInstance(PersonFactory.class);
        VersionedPersonFactory versionedPersonFactory = injector.getInstance(VersionedPersonFactory.class);

        PersonName name = personNameFactory.create("Daniel", "Watrous");
        Person publishedPerson = personFactory.create(name, 32, "daniel@current.com", true);
        personDao.save(publishedPerson);
        
//        VersionedPerson versionedPerson = personDao.getPersonByName(name);
        
        Person draftPerson = personFactory.create(personNameFactory.create("Daniel", "Watrous"), 33, "daniel@future.com", true);
        Person history1Person = personFactory.create(personNameFactory.create("Danny", "Watrous"), 23, "daniel@oldschool.com", false);
        Person history2Person = personFactory.create(personNameFactory.create("Dan", "Watrous"), 33, "daniel@beforeinternet.com", true);

        VersionedPerson versionedPerson = versionedPersonFactory.create(publishedPerson);
        versionedPerson.setDraft(draftPerson);
        versionedPerson.addToHistory(history1Person);
        versionedPerson.addToHistory(history2Person);
        
        Datastore ds = injector.getInstance(Key.get(Datastore.class, Names.named("peopleDatabase")));
//        ds.save(versionedPerson);
        
    }
}
