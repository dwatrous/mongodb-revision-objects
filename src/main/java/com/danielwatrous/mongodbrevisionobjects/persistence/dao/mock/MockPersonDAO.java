/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.persistence.dao.mock;

import com.danielwatrous.mongodbrevisionobjects.factory.PersonFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.PersonNameFactory;
import com.danielwatrous.mongodbrevisionobjects.factory.VersionedPersonFactory;
import com.danielwatrous.mongodbrevisionobjects.model.DisplayMode;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.Person.PersonName;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.model.mock.MockPerson;
import com.danielwatrous.mongodbrevisionobjects.model.mock.MockPersonName;
import com.danielwatrous.mongodbrevisionobjects.model.mock.MockVersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.persistence.dao.PersonDAO;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author watrous
 */
public class MockPersonDAO implements PersonDAO {

    private DisplayMode displayMode;
    private PersonName name1;
    private PersonName name2;
    private PersonName name2_b;
    private PersonName name3;
    private Person person1;
    private Person person1_b;
    private Person person2;
    private Person person2_b;
    private Person person3;
    private Map<String, Person> personMap;
    private VersionedPerson versionedPerson1;
    private VersionedPerson versionedPerson2;
    private VersionedPerson versionedPerson3;

    @Inject
    public MockPersonDAO(DisplayMode displayMode, PersonNameFactory personNameFactory, PersonFactory personFactory, VersionedPersonFactory versionedPersonFactory) {
        this.displayMode = displayMode;
        name1 = personNameFactory.create("Daniel", "Watrous");
        name2 = personNameFactory.create("Jough", "Psmyth");
        name2_b = personNameFactory.create("Chip", "Cheesman");
        name3 = personNameFactory.create("Ted", "Bear");
        person1 = personFactory.create(name1, 32, "daniel@test.com", true);
        person1_b = personFactory.create(name1, 33, "daniel@new.com", true);
        person2 = personFactory.create(name2, 14, "jough@nootherstory.com", true);
        person2_b = personFactory.create(name2_b, 14, "chip@nootherstory.com", true);
        person3 = personFactory.create(name3, 32, "ted@bear.com", false);
        personMap = new HashMap<String, Person>();
        personMap.put("1", person2_b);
        versionedPerson1 = versionedPersonFactory.create(person1, person1_b, new HashMap<String, Person>());
        versionedPerson2 = versionedPersonFactory.create(person2, person2, personMap);
        versionedPerson3 = versionedPersonFactory.create(person3, person3, new HashMap<String, Person>());
    }

    public void saveAsDraft(Person person) {
        throw new UnsupportedOperationException("Not supported in test."); //To change body of generated methods, choose Tools | Templates.
    }

    public void publish() {
        throw new UnsupportedOperationException("Not supported in test."); //To change body of generated methods, choose Tools | Templates.
    }

    public Person getPersonByName(PersonName name) {
        if (name.getFirstName().equalsIgnoreCase("Daniel") && name.getLastName().equalsIgnoreCase("Watrous")) {
            return getAppropriateDisplayOption(versionedPerson1);
        } else {
            return null;
        }
    }

    public Person getPersonByName(PersonName name, String historyMarker) {
        return versionedPerson2.getHistory().get(historyMarker);
    }

    public List<Person> getPersonsByLastName(String lastName) {
        List<Person> persons = new ArrayList<Person>();
        persons.add(getAppropriateDisplayOption(versionedPerson1));
        persons.add(getAppropriateDisplayOption(versionedPerson2));
        persons.add(getAppropriateDisplayOption(versionedPerson3));
        return persons;
    }
    
    private Person getAppropriateDisplayOption(VersionedPerson versionedPerson) {
        if (displayMode.isPreviewModeActive()) {
            return versionedPerson.getDraft();
        } else {
            return versionedPerson.getPublished();
        }
    }
    
}
