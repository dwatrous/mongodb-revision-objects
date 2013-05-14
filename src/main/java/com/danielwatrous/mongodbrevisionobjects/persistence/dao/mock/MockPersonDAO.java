/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.persistence.dao.mock;

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
    
    @Inject DisplayMode displayMode;
    static final PersonName name1 = new MockPersonName("Daniel", "Watrous");
    static final PersonName name2 = new MockPersonName("Jough", "Psmyth");
    static final PersonName name2_b = new MockPersonName("Chip", "Cheesman");
    static final PersonName name3 = new MockPersonName("Ted", "Bear");
    static final Person person1 = new MockPerson(name1, 32, "daniel@test.com", true);
    static final Person person1_b = new MockPerson(name1, 33, "daniel@new.com", true);
    static final Person person2 = new MockPerson(name2, 14, "jough@nootherstory.com", true);
    static final Person person2_b = new MockPerson(name2_b, 14, "chip@nootherstory.com", true);
    static final Person person3 = new MockPerson(name3, 32, "ted@bear.com", false);
    static final Map<String, Person> personMap = new HashMap<String, Person>();
    static final VersionedPerson versionedPerson1 = new MockVersionedPerson(person1, person1_b, null);
    static final VersionedPerson versionedPerson2 = new MockVersionedPerson(person2, person2, personMap);
    static final VersionedPerson versionedPerson3 = new MockVersionedPerson(person3, null, null);

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
        versionedPerson2.getHistory().put("1", person2_b);
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
