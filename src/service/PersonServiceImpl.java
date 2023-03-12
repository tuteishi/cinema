package service;

import model.Person;
import repository.PersonRepository;
import repository.PersonRepositoryImpl;

import java.util.Scanner;

public class PersonServiceImpl implements PersonService {

    private static final PersonRepository personRepository = new PersonRepositoryImpl();

    static {
        createManager();
    }

    @Override
    public boolean createPerson() {
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        System.out.print("Введите логин");
        String username = scanner.nextLine();
        person.setUsername(username);
        System.out.print("Введите пароль");
        String password = scanner.nextLine();
        person.setPassword(password);
        try {
            return personRepository.addPersonToDb(person);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static void createManager(){
        Person manager = new Person("manager", "1111");
        try {
            personRepository.addPersonToDb(manager);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
