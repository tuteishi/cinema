package service;

import model.Person;
import repository.PersonRepository;
import repository.PersonRepositoryImpl;

import java.util.Scanner;

public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository = new PersonRepositoryImpl();

    @Override
    public boolean create() {
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        System.out.print("Введите логин");
        String username = scanner.nextLine();
        person.setUsername(username);
        System.out.print("Введите пароль");
        String password = scanner.nextLine();
        person.setPassword(password);
        try {
            return personRepository.create(person);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
