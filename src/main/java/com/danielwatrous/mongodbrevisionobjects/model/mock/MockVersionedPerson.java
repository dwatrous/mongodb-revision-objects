/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.model.mock;

import com.danielwatrous.mongodbrevisionobjects.model.HistoricalPerson;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.Map;

/**
 *
 * @author watrous
 */
public class MockVersionedPerson implements VersionedPerson {
    private Person published;
    private Person draft;
    private Map<String, HistoricalPerson> history;

    @Inject
    public MockVersionedPerson(@Assisted("published") Person published, @Assisted("draft") Person draft, @Assisted("history") Map<String, HistoricalPerson> history) {
        this.published = published;
        this.draft = draft;
        this.history = history;
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
