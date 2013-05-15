/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.module;

import com.danielwatrous.mongodbrevisionobjects.factory.*;
import com.danielwatrous.mongodbrevisionobjects.model.DisplayMode;
import com.danielwatrous.mongodbrevisionobjects.model.HistoricalPerson;
import com.danielwatrous.mongodbrevisionobjects.model.Person;
import com.danielwatrous.mongodbrevisionobjects.model.Person.PersonName;
import com.danielwatrous.mongodbrevisionobjects.model.VersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.model.mock.MockHistoricalPerson;
import com.danielwatrous.mongodbrevisionobjects.model.mock.MockPerson;
import com.danielwatrous.mongodbrevisionobjects.model.mock.MockPersonName;
import com.danielwatrous.mongodbrevisionobjects.model.mock.MockVersionedPerson;
import com.danielwatrous.mongodbrevisionobjects.persistence.dao.PersonDAO;
import com.danielwatrous.mongodbrevisionobjects.persistence.dao.mock.MockPersonDAO;
import com.danielwatrous.mongodbrevisionobjects.state.CurrentDisplayMode;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 *
 * @author watrous
 */
public class TestModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(Person.class, MockPerson.class).build(PersonFactory.class));
        install(new FactoryModuleBuilder().implement(HistoricalPerson.class, MockHistoricalPerson.class).build(HistoricalPersonFactory.class));
        install(new FactoryModuleBuilder().implement(PersonName.class, MockPersonName.class).build(PersonNameFactory.class));
        install(new FactoryModuleBuilder().implement(VersionedPerson.class, MockVersionedPerson.class).build(VersionedPersonFactory.class));

        bind(PersonDAO.class).to(MockPersonDAO.class).in(Singleton.class);
        bind(DisplayMode.class).to(CurrentDisplayMode.class).in(Singleton.class);
    }
}
