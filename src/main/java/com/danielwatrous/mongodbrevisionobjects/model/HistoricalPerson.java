/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.model;

import java.util.Date;

/**
 *
 * @author watrous
 */
public interface HistoricalPerson {
    Person getPerson();
    void setPerson(Person person);
    Date getDateRetired();
    void setDateRetired(Date dateRetired);
}
