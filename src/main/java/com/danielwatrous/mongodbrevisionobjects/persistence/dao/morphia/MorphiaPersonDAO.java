/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.persistence.dao.morphia;

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
    
    @Inject
    public MorphiaPersonDAO(@Named("peopleDatabase") Datastore ds, DisplayMode displayMode) {
        super(ds);
        this.displayMode = displayMode;
    }

    public void saveAsDraft(Person person) {
//        ds.save(person);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void publish() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Person getPersonByName(PersonName name) {
        MorphiaVersionedPerson versionedPerson =  ds.find(entityClazz).filter("published.name", name).get();
        return getAppropriateDisplayOption(versionedPerson);
    }

    public Person getPersonByName(PersonName name, String historyMarker) {
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
