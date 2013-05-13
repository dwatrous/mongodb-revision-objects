/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjectss.factory;

import com.danielwatrous.mongodbrevisionobjectss.model.Person;
import com.danielwatrous.mongodbrevisionobjectss.model.Person.PersonName;

/**
 *
 * @author watrous
 */
public interface PersonFactory {
    //To use multiple parameters of the same type, use a named @Assisted annotation with a String value to disambiguate the parameters.
    Person create(PersonName name, Integer age, String email, boolean happy);

}
