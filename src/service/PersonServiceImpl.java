package service;

import model.Film;
import model.Person;
import repository.PersonRepository;
import repository.PersonRepositoryImpl;
import util.Validator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class PersonServiceImpl implements PersonService {
    Person person = new Person();
    private static final PersonRepository personRepository = new PersonRepositoryImpl();
    Validator validator = new Validator();
    String error = "Invalid data entered, please try again.";

    @Override
    public boolean createPerson() {
        String username;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter username: ");
            username = scanner.next();
            if (personRepository.getPersonByUsernameFromDb(username) != null) {
                System.out.println("This username is already taken, enter a different username.");
            } else break;
        }
        person.setUsername(username);
        System.out.print("Enter password: ");
        String password = scanner.next();
        person.setPassword(password);
        System.out.println("User " + username + " registered.");
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
        if (personRepository.getPersonByUsernameFromDb(person.getUsername()) != null) {
            String passwordDb = personRepository.getPersonByUsernameFromDb(person.getUsername()).getPassword();
            if (passwordDb.equals(password)) {
                return personRepository.getPersonByUsernameFromDb(person.getUsername());
            }
        } else {
            System.out.println("This user does not exist, please check your username or password.");
        }
        return null;
    }


    @Override
    public Person getByUsername(String username) {
        return null;
    }

    @Override
    public void showAllPersons() {
        Stream<Person> stream = personRepository.getAllPersonsFromDb().stream();
        stream
                .sorted(Comparator.comparing(Person::getUsername))
                .forEach(System.out::println);
    }

    @Override
    public boolean updatePersonById() {
        showAllPersons();
        String idString;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter user ID for edit role: ");
            idString = scanner.next();
        }
        while (!validator.numberValid(idString));
        Integer id = Integer.parseInt(idString);
        if (searchIdPerson(id)) {
            roleSelection(id);
        } else {
            System.out.println("There is no film with id " + id);
            return false;
        }
        return false;
    }

    private void roleSelection(Integer id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Enter:
                1 - Assign a manager role.
                2 - Assign a admin role.
                """);
        String choice = scanner.next();
        switch (choice) {
            case "1" -> personRepository.editRoleManager(id);
            case "2" -> personRepository.editRoleAdmin(id);
            default -> System.out.println(error);
        }
    }

    @Override
    public boolean deleteById() {
        showAllPersons();
        String idString;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter user ID for delete: ");
            idString = scanner.next();
        }
        while (!validator.numberValid(idString));
        Integer id = Integer.parseInt(idString);
        if (searchIdPerson(id)) {
            personRepository.deletePersonByIdFromDb(id);
            return personRepository.deletePersonByIdFromDb(id);
        } else {
            System.out.println("There is no film with id " + id);
            return false;
        }
    }
    @Override
    public boolean searchIdPerson(Integer id) {
        boolean match = personRepository.getAllPersonsFromDb().stream()
                .anyMatch(f -> f.getId().equals(id));
        return match;
    }


}
