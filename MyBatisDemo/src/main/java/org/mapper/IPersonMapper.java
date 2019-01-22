package org.mapper;

import org.pojo.Person;

public interface IPersonMapper {
    Person getPersonById(int id);
    void insertPerson(Person person);
    void updatePerson(Person person);
}
