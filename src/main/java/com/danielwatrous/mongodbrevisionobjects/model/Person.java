/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.model;

/**
 *
 * @author watrous
 */
public interface Person {
    PersonName getName();
    void setName(PersonName name);
    Integer getAge();
    void setAge(Integer age);
    String getEmail();
    void setEmail(String email);
    boolean isHappy();
    void setHappy(boolean happy);
    public interface PersonName {
        String getFirstName();
        void setFirstName(String firstName);
        String getLastName();
        void setLastName(String lastName);
    }
}
