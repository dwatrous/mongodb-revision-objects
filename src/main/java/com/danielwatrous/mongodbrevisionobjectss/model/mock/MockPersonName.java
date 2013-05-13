/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjectss.model.mock;

import com.danielwatrous.mongodbrevisionobjectss.model.Person;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 *
 * @author watrous
 */
public class MockPersonName implements Person.PersonName {
    String firstName;
    String lastName;

    @Inject
    public MockPersonName(@Assisted("firstName") String firstName, @Assisted("lastName") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
