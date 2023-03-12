package controller;

import service.PersonService;
import service.PersonServiceImpl;

import java.util.Scanner;

public class PersonController {
    PersonService personService = new PersonServiceImpl();
    public void startMenu() throws ClassNotFoundException {
        System.out.println("""
                1 - зарегистрироваться 
                2 - войти 
                0 - выйти
                
                """);
        Scanner scanner = new Scanner(System.in);
        String step = scanner.nextLine();
        switch (step){
            case "1" -> {
                System.out.println("Меню регистрации");
                personService.createPerson();
            }
            case "2" -> System.out.println("Меню входа");
            case "0" -> System.out.println("Меню выхода");
            default -> System.out.println("Некорректные данные");
        }
    }
}
