/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjectss.module;

import com.danielwatrous.mongodbrevisionobjectss.factory.PersonFactory;
import com.danielwatrous.mongodbrevisionobjectss.factory.PersonNameFactory;
import com.danielwatrous.mongodbrevisionobjectss.model.DisplayMode;
import com.danielwatrous.mongodbrevisionobjectss.model.Person;
import com.danielwatrous.mongodbrevisionobjectss.model.Person.PersonName;
import com.danielwatrous.mongodbrevisionobjectss.model.mock.MockPerson;
import com.danielwatrous.mongodbrevisionobjectss.model.mock.MockPersonName;
import com.danielwatrous.mongodbrevisionobjectss.persistence.dao.PersonDAO;
import com.danielwatrous.mongodbrevisionobjectss.persistence.dao.mock.MockPersonDAO;
import com.danielwatrous.mongodbrevisionobjectss.state.CurrentDisplayMode;
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
        install(new FactoryModuleBuilder().implement(PersonName.class, MockPersonName.class).build(PersonNameFactory.class));

        bind(PersonDAO.class).to(MockPersonDAO.class).in(Singleton.class);
        bind(DisplayMode.class).to(CurrentDisplayMode.class).in(Singleton.class);
    }
}
