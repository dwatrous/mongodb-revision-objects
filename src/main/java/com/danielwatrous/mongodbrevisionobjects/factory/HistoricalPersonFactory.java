/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.factory;

import com.danielwatrous.mongodbrevisionobjects.model.HistoricalPerson;
import com.danielwatrous.mongodbrevisionobjects.model.Person;

/**
 *
 * @author watrous
 */
public interface HistoricalPersonFactory {
    HistoricalPerson create(Person name);
}
