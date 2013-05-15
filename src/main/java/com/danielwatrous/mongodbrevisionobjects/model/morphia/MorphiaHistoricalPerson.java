/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.model.morphia;

import com.danielwatrous.mongodbrevisionobjects.model.HistoricalPerson;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.google.code.morphia.annotations.Entity;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.Date;

/**
 *
 * @author watrous
 */
@Entity(noClassnameStored = true)
public class MorphiaHistoricalPerson implements HistoricalPerson {
    private Person person;
    private Date dateRetired;

    @Inject
    public MorphiaHistoricalPerson(@Assisted Person person) {
        this.person = person;
        this.dateRetired = new Date();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getDateRetired() {
        return dateRetired;
    }

    public void setDateRetired(Date dateRetired) {
        this.dateRetired = dateRetired;
    }

}
