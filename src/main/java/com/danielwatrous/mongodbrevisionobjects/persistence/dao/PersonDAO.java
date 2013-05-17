/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.persistence.dao;

import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.Person.PersonName;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author watrous
 */
public interface PersonDAO {
    /**
     * This assumes no existing person object and creates a new VersionedPerson object
     * and then adds it to the datastore
     * 
     * @param person 
     */
    void save(Person person);
    
    /**
     * this will overwrite the current draft document with the provided Person object
     * 
     * @param person 
     */
    void saveDraft(Person person);
    
    /**
     * publish will save the currently published document in history and then 
     * overwrite the published document with the document passed in
     * 
     * @param person 
     */
    void publish(Person person);
    
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
    Person getPersonByName(PersonName name, Integer historyMarker);
    
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
