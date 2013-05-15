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
    //To use multiple parameters of the same type, use a named @Assisted annotation with a String value to disambiguate the parameters.
    VersionedPerson create(@Assisted("published") Person published, 
                           @Assisted("draft") Person draft, 
                           @Assisted("history") Map<String, HistoricalPerson> history);
}
