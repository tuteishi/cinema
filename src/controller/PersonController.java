package controller;

import repository.PersonRepository;
import repository.PersonRepositoryImpl;
import service.PersonService;
import service.PersonServiceImpl;

import java.util.Scanner;

public class PersonController {
    PersonService personService = new PersonServiceImpl();
    PersonRepository personRepository = new PersonRepositoryImpl();

    public void enterInSystem(){
        System.out.println("""
                Enter:
                1 - Register in the application.
                2 - Log into the app.
                """);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch (choice){
            case "1" -> personService.createPerson();
            case "2" -> roleMenu(personService.authorizationPerson());
            default -> System.out.println("Invalid data entered, please try again.");
        }
    }

//    public void case2(){
//        String a = personService.authorizationPerson();
//        roleMenu(personService.authorizationPerson());
//    }

    public void roleMenu(String role) {
        switch (role){
            case "USER" -> userMenu();
            case "MANAGER" -> managerMenu();
            case "ADMIN" -> adminMenu();

        }

    }

    public void userMenu() {
        System.out.println("""
                Enter:
                1 - Show movie poster.
                2 - Buy or return a ticket.
                3 - View purchased tickets.
                """);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

    }
    public void managerMenu() {

    }

    public void adminMenu() {

    }



}
