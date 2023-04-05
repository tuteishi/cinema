package repository;

import model.Person;

import java.util.List;

public interface PersonRepository {
    boolean addPersonToDb(Person person);
     List <Person> getAllPersonsFromDb();
     Person getPersonByUsernameFromDb(String username);
     boolean deletePersonByIdFromDb(int id);
     boolean editRoleManager(Integer id);
     boolean editRoleAdmin(Integer id);
     boolean editRoleUser(Integer id);
}
