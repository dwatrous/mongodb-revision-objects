/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjectss.model;

import java.util.Map;

/**
 *
 * @author watrous
 */
public interface VersionedPerson {
    Person getPublished();
    void setPublished(Person published);
    Person getDraft();
    void setDraft(Person draft);
    Map<String, Person> getHistory();
    void setHistory(Map<String, Person> historicalPersons);
}
