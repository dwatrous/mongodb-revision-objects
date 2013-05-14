/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.factory;

import com.danielwatrous.mongodbrevisionobjects.model.Person.PersonName;
import com.google.inject.assistedinject.Assisted;

/**
 *
 * @author watrous
 */
public interface PersonNameFactory {
    //To use multiple parameters of the same type, use a named @Assisted annotation with a String value to disambiguate the parameters.
    PersonName create(@Assisted("firstName") String firstName, @Assisted("lastName") String lastName);

}
