/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjectss.model.morphia;

import com.danielwatrous.mongodbrevisionobjectss.model.Person;
import com.danielwatrous.mongodbrevisionobjectss.model.VersionedPerson;
import com.google.code.morphia.annotations.Entity;
import java.util.Map;

/**
 *
 * @author watrous
 */
@Entity(value = "person", noClassnameStored = true)
public class MorphiaVersionedPerson implements VersionedPerson {
    private Person published;
    private Person draft;
    private Map<String, Person> history;

    public Person getPublished() {
        return published;
    }

    public void setPublished(Person published) {
        this.published = published;
    }

    public Person getDraft() {
        return draft;
    }

    public void setDraft(Person draft) {
        this.draft = draft;
    }

    public Map<String, Person> getHistory() {
        return history;
    }

    public void setHistory(Map<String, Person> history) {
        this.history = history;
    }

}
