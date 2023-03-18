package service;

import model.Person;
import repository.PersonRepository;
import repository.PersonRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class PersonServiceImpl implements PersonService {
    Person person = new Person();

    private static final PersonRepository personRepository = new PersonRepositoryImpl();

    @Override
    public boolean createPerson() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (personRepository.getPersonByUsernameFromDb(username) != null) {
            System.out.println("This username is already taken, enter a different username.");
            createPerson();
        }
        person.setUsername(username);
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        person.setPassword(password);
        return personRepository.addPersonToDb(person);
    }

    @Override
    public Person authorizationPerson() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        person.setUsername(username);
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        person.setPassword(password);
        if (personRepository.getPersonByUsernameFromDb(person.getUsername()) != null ){
            String passwordDb = personRepository.getPersonByUsernameFromDb(person.getUsername()).getPassword();
            if(passwordDb.equals(password)){
                return person;
            }
        }else {
            System.out.println("This user does not exist, please check your username or password.");
        }
        return null;
    }


    @Override
    public Person getByUsername(String username) {
        return null;
    }

    @Override
    public List<Person> getAllPersons() {
        return null;
    }

    @Override
    public boolean updatePersonById(Long id, Person person) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }


}
