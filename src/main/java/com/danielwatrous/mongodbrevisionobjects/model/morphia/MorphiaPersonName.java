/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.model.morphia;

import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 *
 * @author watrous
 */
@Entity(noClassnameStored = true)
@Embedded()
public class MorphiaPersonName implements Person.PersonName {
    private String firstName;
    private String lastName;

    public MorphiaPersonName() {
    }

    @Inject
    public MorphiaPersonName(@Assisted("firstName") String firstName, @Assisted("lastName") String lastName) {
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
