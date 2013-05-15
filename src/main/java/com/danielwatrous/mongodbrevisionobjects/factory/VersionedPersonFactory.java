/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.factory;

import com.danielwatrous.mongodbrevisionobjects.model.HistoricalPerson;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.google.inject.assistedinject.Assisted;
import java.util.Map;

/**
 *
 * @author watrous
 */
public interface VersionedPersonFactory {
    VersionedPerson create(@Assisted("published") Person published);
}
