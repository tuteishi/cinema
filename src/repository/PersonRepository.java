package repository;

import model.Person;

import java.util.List;

public interface PersonRepository {
    boolean addPersonToDb(Person person) throws ClassNotFoundException;
    List <Person> getAllPersons();
    Person getPerson(Integer id);
    Person update(Person person, Integer id);
}
