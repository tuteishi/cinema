package controller;

import model.Person;
import repository.PersonRepository;
import repository.PersonRepositoryImpl;
import service.FilmService;
import service.FilmServiceImpl;
import service.PersonService;
import service.PersonServiceImpl;

import java.util.Scanner;

public class PersonController {
    PersonService personService = new PersonServiceImpl();
    FilmService filmService = new FilmServiceImpl();

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


    public void roleMenu(Person person) {
        switch (person.getRole()){
            case "USER" -> userMenu(person);
            case "MANAGER" -> managerMenu();
            case "ADMIN" -> adminMenu();

        }

    }

    public void userMenu(Person person) {
        System.out.println("""
                Enter:
                1 - Show movie poster.
                2 - Buy or return a ticket.
                3 - View purchased tickets.
                """);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch (choice){
            case "1" -> filmService.showFilms();
            case "2" ->
            case "3" ->
        }

    }
    public void managerMenu() {

    }

    public void adminMenu() {

    }



}
