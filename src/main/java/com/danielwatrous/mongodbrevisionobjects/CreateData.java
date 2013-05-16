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

        PersonName name = personNameFactory.create("Danny", "Watrous");
        Person publishedPerson = personFactory.create(name, 15, "daniel@beforeinternet.com", true);
        personDao.save(publishedPerson);
        
        // get the person we just saved above (for the object ID
        Person retrievedPerson = personDao.getPersonByName(name);
        retrievedPerson.setAge(23);
        retrievedPerson.setEmail("daniel@oldschool.com");
        personDao.publish(retrievedPerson);
        
        retrievedPerson.setAge(32);
        retrievedPerson.setEmail("daniel@current.com");
        personDao.publish(retrievedPerson);
        
        retrievedPerson.setAge(33);
        retrievedPerson.setEmail("daniel@future.com");
        personDao.saveDraft(retrievedPerson);
        
    }
}
