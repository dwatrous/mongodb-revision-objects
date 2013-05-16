/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.persistence.dao.morphia;

import com.danielwatrous.mongodbrevisionobjects.factory.HistoricalPersonFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.PersonFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.VersionedPersonFactory;
import com.danielwatrous.mongodbrevisionobjects.model.DisplayMode;
import com.danielwatrous.mongodbrevisionobjects.model.HistoricalPerson;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.Person.PersonName;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.model.morphia.MorphiaPerson;
import com.danielwatrous.mongodbrevisionobjects.model.morphia.MorphiaVersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.persistence.dao.PersonDAO;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author watrous
 */
public class MorphiaPersonDAO extends BasicDAO<MorphiaVersionedPerson, ObjectId> implements PersonDAO {

    DisplayMode displayMode;
    PersonFactory personFactory;
    HistoricalPersonFactory historicalPersonFactory;
    VersionedPersonFactory versionedPersonFactory;
    
    @Inject
    public MorphiaPersonDAO(@Named("peopleDatabase") Datastore ds, 
                            DisplayMode displayMode, 
                            PersonFactory personFactory, 
                            HistoricalPersonFactory historicalPersonFactory,
                            VersionedPersonFactory versionedPersonFactory) {
        super(ds);
        this.displayMode = displayMode;
        this.personFactory = personFactory;
        this.historicalPersonFactory = historicalPersonFactory;
        this.versionedPersonFactory = versionedPersonFactory;
    }

    public void save(Person person) {
        // create a new versioned object
        VersionedPerson versionedPerson = versionedPersonFactory.create(person);
        ds.save(versionedPerson);
    }

    public void saveDraft(Person person) {
        // locate and load existing record
        VersionedPerson versionedPerson = ds.get(entityClazz, ((MorphiaPerson)person).getId());
        // overwrite existing draft Person with the Person attribute passed in with this call
        versionedPerson.setDraft(person);
        // save versioned object
        ds.save(versionedPerson);
    }

    public void publish(Person person) {
        // locate and load existing record
        VersionedPerson versionedPerson = ds.get(entityClazz, ((MorphiaPerson)person).getId());
        // save currently published Person in history
        HistoricalPerson historicalPerson = historicalPersonFactory.create(versionedPerson.getPublished());
        versionedPerson.addToHistory(historicalPerson);
        // overwrite published Person with the Person attribute passed in with this call
        versionedPerson.setPublished(person);
        // save versioned object
        ds.save(versionedPerson);
    }

    public Person getPersonByName(PersonName name) {
        MorphiaVersionedPerson versionedPerson =  ds.find(entityClazz).disableValidation().filter("published.name", name).get();
        return getAppropriateDisplayOption(versionedPerson);
    }

    public Person getPersonByName(PersonName name, Integer historyMarker) {
        MorphiaVersionedPerson versionedPerson =  ds.find(entityClazz).disableValidation().filter("published.name", name).get();
        return versionedPerson.getHistory().get(historyMarker).getPerson();
    }

    public List<Person> getPersonsByLastName(String lastName) {
        List result = ds.find(entityClazz).filter("published.name.last", lastName).asList();
        List<Person> results = new ArrayList<Person>();
        Iterator<VersionedPerson> versionedPersons = result.iterator();
        while (versionedPersons.hasNext()) {
            results.add(getAppropriateDisplayOption(versionedPersons.next()));
        }
        return results;
    }
    
    private void appendObjectId(Person person, VersionedPerson versionedPerson) {
        ((MorphiaPerson)person).setId(((MorphiaVersionedPerson)versionedPerson).getId());
    }
    
    private Person getAppropriateDisplayOption(VersionedPerson versionedPerson) {
        Person returnPerson;
        if (displayMode.isPreviewModeActive()) {
            returnPerson = versionedPerson.getDraft();
        } else {
            returnPerson = versionedPerson.getPublished();
        }
        appendObjectId(returnPerson, versionedPerson);
        return returnPerson;
    }
}
