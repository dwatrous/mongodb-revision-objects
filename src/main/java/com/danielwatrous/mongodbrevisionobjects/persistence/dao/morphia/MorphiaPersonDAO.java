/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.persistence.dao.morphia;

import com.danielwatrous.mongodbrevisionobjects.factory.HistoricalPersonFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.PersonFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.PersonNameFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.VersionedPersonFactory;
import com.danielwatrous.mongodbrevisionobjects.model.DisplayMode;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.Person.PersonName;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.model.morphia.MorphiaVersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.persistence.dao.PersonDAO;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.ArrayList;
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

    public void saveDraft(Person person, ObjectId objectid) {
        // locate and load existing record
        VersionedPerson versionedPerson = ds.get(entityClazz, objectid);
        // overwrite existing draft Person with the Person attribute passed in with this call
        versionedPerson.setDraft(person);
        // save versioned object
        ds.save(versionedPerson);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void publish(Person person, ObjectId objectid) {
        // locate and load existing record
        // save currently published object in history
        // overwrite published Person with the Person attribute passed in with this call
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Person getPersonByName(PersonName name) {
        MorphiaVersionedPerson versionedPerson =  ds.find(entityClazz).filter("published.name", name).get();
        return getAppropriateDisplayOption(versionedPerson);
    }

    public Person getPersonByName(PersonName name, Integer historyMarker) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Person> getPersonsByLastName(String lastName) {
        List result = ds.find(entityClazz).filter("published.name.last", lastName).asList();
        List<Person> results = new ArrayList<Person>();
        for (Object versionedPerson : result) {
            results.add(getAppropriateDisplayOption((VersionedPerson)versionedPerson));
        }
        return results;
    }
    
    private Person getAppropriateDisplayOption(VersionedPerson versionedPerson) {
        if (displayMode.isPreviewModeActive()) {
            return versionedPerson.getDraft();
        } else {
            return versionedPerson.getPublished();
        }
    }
}
