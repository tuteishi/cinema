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
        String password1;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter username: ");
            username = scanner.next();
            if (personRepository.getPersonByUsernameFromDb(username) != null) {
                System.out.println(System.lineSeparator() + "This username is already taken, enter a different username.");
            } else break;
        }
        person.setUsername(username);
        while (true) {
            System.out.print("Enter password: ");
            password1 = scanner.next();
            System.out.print("Repeat the entered password: ");
            String password2 = scanner.next();
            if (password1.equals(password2)){
                break;
            }else {
                System.out.println(System.lineSeparator() + "The entered passwords are not equal...");
            }
        }
        person.setPassword(password1);
        System.out.println(System.lineSeparator() + "User " + username + " is registered.");
        return personRepository.addPersonToDb(person);
    }

    @Override
    public Person authorizationPerson() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        person.setUsername(username);
        if (personRepository.getPersonByUsernameFromDb(person.getUsername()) != null) {
            String passwordDb = personRepository.getPersonByUsernameFromDb(person.getUsername()).getPassword();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            person.setPassword(password);
            if (passwordDb.equals(password)) {
                System.out.println(System.lineSeparator() + "User " + username + " is authorized.");
                return personRepository.getPersonByUsernameFromDb(person.getUsername());
            }else {
                System.out.println(System.lineSeparator() + "Incorrect password.");
            }
        } else {
            System.out.println(System.lineSeparator() + "This user does not exist, please check your username.");
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
            System.out.print(System.lineSeparator() + "Enter user ID for edit role: ");
            idString = scanner.next();
        }
        while (!validator.numberValid(idString));
        Integer id = Integer.parseInt(idString);
        if (searchIdPerson(id)) {
            roleSelection(id);
            System.out.println("Role user with Id: " + id + " is modified");
        } else {
            System.out.println(System.lineSeparator() + "There is no user with id " + id + ".");
            return false;
        }
        return false;
    }

    private void roleSelection(Integer id) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(System.lineSeparator() + """
                Enter:
                1 - Assign a manager role.
                2 - Assign a admin role.
                3 - Assign a user role.
                """);
        String choice = scanner.next();
        switch (choice) {
            case "1" -> personRepository.editRoleManager(id);
            case "2" -> personRepository.editRoleAdmin(id);
            case "3" -> personRepository.editRoleUser(id);
            default -> System.out.println(System.lineSeparator() + error);
        }
    }

    @Override
    public boolean deleteById() {
        showAllPersons();
        String idString;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print(System.lineSeparator() + "Enter user ID for delete: ");
            idString = scanner.next();
        }
        while (!validator.numberValid(idString));
        Integer id = Integer.parseInt(idString);
        if (searchIdPerson(id)) {
            System.out.println(System.lineSeparator() + "User with Id:" + id + "  deleted.");
            return personRepository.deletePersonByIdFromDb(id);
        } else {
            System.out.println(System.lineSeparator() + "There is no user with id" + id + ".");
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
