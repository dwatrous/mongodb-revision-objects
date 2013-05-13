/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjectss.persistence.dao;

import com.danielwatrous.mongodbrevisionobjectss.factory.PersonFactory;
import com.danielwatrous.mongodbrevisionobjectss.factory.PersonNameFactory;
import com.danielwatrous.mongodbrevisionobjectss.model.Person;
import com.danielwatrous.mongodbrevisionobjectss.model.Person.PersonName;
import com.danielwatrous.mongodbrevisionobjectss.module.TestModule;
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
    
    public void testRetrievePerson() {
        PersonDAO personDAO = injector.getInstance(PersonDAO.class);
        PersonName searchName = injector.getInstance(PersonNameFactory.class).create("Daniel", "Watrous");
        Person person = personDAO.getPersonByName(searchName);
        assertEquals("daniel@test.com", person.getEmail());
    }

}
