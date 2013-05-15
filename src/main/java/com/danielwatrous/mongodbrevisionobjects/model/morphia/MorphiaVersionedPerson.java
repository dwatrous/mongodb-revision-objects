/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.model.morphia;

import com.danielwatrous.mongodbrevisionobjects.model.HistoricalPerson;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.Map;
import org.bson.types.ObjectId;

/**
 *
 * @author watrous
 */
@Entity(value = "person", noClassnameStored = true)
public class MorphiaVersionedPerson implements VersionedPerson {
    @Id
    private ObjectId id;
    private Person published;
    private Person draft;
    private Map<String, HistoricalPerson> history;

    @Inject
    public MorphiaVersionedPerson(@Assisted("published") Person published, @Assisted("draft") Person draft, @Assisted("history") Map<String, HistoricalPerson> history) {
        this.published = published;
        this.draft = draft;
        this.history = history;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

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

    public Map<String, HistoricalPerson> getHistory() {
        return history;
    }

    public void setHistory(Map<String, HistoricalPerson> history) {
        this.history = history;
    }

}
