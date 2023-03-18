package repository;

import model.Person;

import java.util.List;

public interface PersonRepository {
    boolean addPersonToDb(Person person);
    public List <Person> getAllPersonsFromDb();
    public Person getPersonByUsernameFromDb(String username);
    public boolean deletePersonByIdFromDb(int id);
}
