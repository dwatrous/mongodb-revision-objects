/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects;

import com.danielwatrous.mongodbrevisionobjects.factory.PersonFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.PersonNameFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.VersionedPersonFactory;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.module.MorphiaModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
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
        VersionedPersonFactory versionedPersonFactory = injector.getInstance(VersionedPersonFactory.class);

        Person publishedPerson = personFactory.create(personNameFactory.create("Daniel", "Watrous"), 32, "daniel@current.com", true);
        Person draftPerson = personFactory.create(personNameFactory.create("Daniel", "Watrous"), 33, "daniel@future.com", true);
        Person history1Person = personFactory.create(personNameFactory.create("Dan", "Watrous"), 23, "daniel@oldschool.com", false);
        Person history2Person = personFactory.create(personNameFactory.create("Danny", "Watrous"), 33, "daniel@beforeinternet.com", true);

        Map<String, Person> history = new HashMap<String, Person>();
        history.put("1", history1Person);
        history.put("2", history2Person);
        
        VersionedPerson versionedPerson = versionedPersonFactory.create(publishedPerson, draftPerson, history);
    }
}
