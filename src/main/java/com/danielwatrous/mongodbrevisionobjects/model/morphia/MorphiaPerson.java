/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjectss.model.morphia;

import com.danielwatrous.mongodbrevisionobjectss.model.Person;

/**
 *
 * @author watrous
 */
public class MorphiaPerson {
    private Person.PersonName name;
    private Integer age;
    private String email;
    private boolean happy;

    class MorphiaPersonName implements Person.PersonName {
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    public Person.PersonName getName() {
        return name;
    }

    public void setName(Person.PersonName name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHappy() {
        return happy;
    }

    public void setHappy(boolean happy) {
        this.happy = happy;
    }
}
