/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjectss.persistence.dao;

import com.danielwatrous.mongodbrevisionobjectss.model.Person;
import com.danielwatrous.mongodbrevisionobjectss.model.Person.PersonName;
import java.util.List;

/**
 *
 * @author watrous
 */
public interface PersonDAO {
    /**
     * this will overwrite the current draft document with the provided Person object
     * @param person 
     */
    void saveAsDraft(Person person);
    
    /**
     * publish will save the currently published document in history and then 
     * overwrite the published document with the document currently found in 
     * draft
     */
    void publish();
    
    /**
     * return a Person matching the PersonName provided. If multiple Person objects
     * match, the first is returned.
     * 
     * This should be annotated as @Previewable
     * 
     * @param name
     * @return the first person with name matching PersonName
     */
    Person getPersonByName(PersonName name);
    
    /**
     * return a Person object matching the PersonName provided and located in the
     * history by historyMarker. If multiple Person objects match, the first is returned
     * 
     * @param name
     * @param historyMarker
     * @return 
     */
    Person getPersonByName(PersonName name, String historyMarker);
    
    /**
     * return a List of Person objects matching the lastName provided
     * 
     * This should be annotated as @Previewable
     * 
     * @param lastName
     * @return 
     */
    List<Person> getPersonsByLastName(String lastName);
}
