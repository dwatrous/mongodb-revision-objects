mongodb-revision-objects
========================

This project explores mechanisms to manage versioned data. For example, some data is bound by historical retention policies or user rollback functionality. In order to accomplish this, it's necessary to ensure that previous versions of an object are retained.

This work leverages MongoDB and builds on the structure outlined here:

http://software.danielwatrous.com/representing-revision-data-in-mongodb/

The schemaless document structure of MongoDB provides a lot of flexibility with respect to storing multiple versions of the same object.

This implementation focuses on Java using Morphia (https://code.google.com/p/morphia/) as a mapping technology. I also leverage Guice for dependency injection (https://code.google.com/p/google-guice/).

Grab the code and run the unittests or fire up a MongoDB server and run CreateDate.java to see how it works.

Basic usage:

```java
Injector injector = Guice.createInjector(new MorphiaModule());

// get a PersonDAO
PersonDAO personDao = injector.getInstance(PersonDAO.class);

// create a person object
PersonNameFactory personNameFactory = injector.getInstance(PersonNameFactory.class);
PersonFactory personFactory = injector.getInstance(PersonFactory.class);

PersonName name = personNameFactory.create("Danny", "Watrous");
Person publishedPerson = personFactory.create(name, 15, "daniel@beforeinternet.com", true);

// save a new Person
personDao.save(publishedPerson);

// get the person we just saved above
Person retrievedPerson = personDao.getPersonByName(name);

// update and safe Draft
retrievedPerson.getName().setFirstName("Dan");
retrievedPerson.setAge(23);
retrievedPerson.setEmail("daniel@oldschool.com");
personDao.saveDraft(retrievedPerson);

// or publish (this automatically manages history)
personDao.publish(retrievedPerson);
```

Some additional discussion of this project and a description of my design goals is provided here:
http://software.danielwatrous.com/using-java-to-work-with-versioned-data/