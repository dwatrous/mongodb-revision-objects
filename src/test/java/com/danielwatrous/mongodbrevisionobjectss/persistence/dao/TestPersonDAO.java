/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjectss.persistence.dao;

import com.danielwatrous.mongodbrevisionobjects.persistence.dao.PersonDAO;
import com.danielwatrous.mongodbrevisionobjects.factory.PersonNameFactory;
import com.danielwatrous.mongodbrevisionobjects.model.DisplayMode;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.Person.PersonName;
import com.danielwatrous.mongodbrevisionobjects.module.TestModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

/**
 *
 * @author watrous
 */
public class TestPersonDAO extends TestCase {
    Injector injector;
    PersonName personName;

    @Override
    protected void setUp() {
        injector = Guice.createInjector(new TestModule());
    }
    
    @Override
    protected void tearDown() {
        // pass
    }
    
    public TestPersonDAO(String testName) {
        super (testName);
    }
    
    public void testRetrievePersonPublished() {
        // retrieve personDAO and query for a Person by name
        PersonDAO personDAO = injector.getInstance(PersonDAO.class);
        PersonName searchName = injector.getInstance(PersonNameFactory.class).create("Daniel", "Watrous");
        Person person = personDAO.getPersonByName(searchName);
        assertEquals("daniel@test.com", person.getEmail());
    }

    public void testRetrievePersonDraft() {
        // enable preview mode
        injector.getInstance(DisplayMode.class).enablePreviewModeActive();
        // retrieve personDAO and query for a Person by name
        PersonDAO personDAO = injector.getInstance(PersonDAO.class);
        PersonName searchName = injector.getInstance(PersonNameFactory.class).create("Daniel", "Watrous");
        Person person = personDAO.getPersonByName(searchName);
        assertEquals("daniel@new.com", person.getEmail());
    }

    public void testRetrievePersonHistory() {
        String historyMarker = "1";
        // retrieve personDAO and query for a Person with history marker
        PersonDAO personDAO = injector.getInstance(PersonDAO.class);
        PersonName searchName = injector.getInstance(PersonNameFactory.class).create("Jough", "Psmyth");
        Person person = personDAO.getPersonByName(searchName, historyMarker);
        assertEquals("chip@nootherstory.com", person.getEmail());        
    }
}
