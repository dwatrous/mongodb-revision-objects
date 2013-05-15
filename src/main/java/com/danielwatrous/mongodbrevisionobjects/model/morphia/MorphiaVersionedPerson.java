/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.model.morphia;

import com.danielwatrous.mongodbrevisionobjects.factory.HistoricalPersonFactory;
import com.danielwatrous.mongodbrevisionobjects.model.HistoricalPerson;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Transient;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.HashMap;
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
    private Map<Integer, HistoricalPerson> history;
    @Transient private HistoricalPersonFactory historicalPersonFactory;

    @Inject
    public MorphiaVersionedPerson(@Assisted("published") Person published, HistoricalPersonFactory historicalPersonFactory) {
        this.published = published;
        this.historicalPersonFactory = historicalPersonFactory;
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

    public Map<Integer, HistoricalPerson> getHistory() {
        return history;
    }

    public void addToHistory(Person person) {
        // create HistoricalPerson
        HistoricalPerson newHistoricalPerson = historicalPersonFactory.create(person);
        // ensure at least an empty Map
        if (this.history == null) {
            this.history = new HashMap<Integer, HistoricalPerson>();
        }
        // find next history value
        Integer historicalMarker = 0;
        if (this.history.isEmpty()) {
            historicalMarker = 1;
        } else {
            for (Map.Entry<Integer, HistoricalPerson> entry : this.history.entrySet()) {
                if (entry.getKey() >= historicalMarker) historicalMarker = entry.getKey() + 1;
            }
        }
        // add to Map with next history value
        this.history.put(historicalMarker, newHistoricalPerson);
    }

}
