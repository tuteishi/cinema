package controller;

import model.Person;
import repository.PersonRepository;
import repository.PersonRepositoryImpl;
import service.*;

import java.util.Scanner;

public class PersonController {
    PersonService personService = new PersonServiceImpl();
    FilmService filmService = new FilmServiceImpl();
    TicketService ticketService = new TicketServiceImpl();
    String error = "Invalid data entered, please try again.";

    public void enterInSystem(){
        System.out.print("""
                Enter:
                1 - Register in the application.
                2 - Log into the app.
                3 - Exit.
                """ + System.lineSeparator());
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch (choice){
            case "1" -> personService.createPerson();
            case "2" -> roleMenu(personService.authorizationPerson());
            case "3" -> System.exit(1);
            default -> System.out.println(error);
        }
    }


    public void roleMenu(Person person) {
        switch (person.getRole()){
            case USER -> userMenu(person);
            case MANAGER-> managerMenu();
            case ADMIN -> adminMenu();
        }

    }

    public void userMenu(Person person) {
        System.out.println("""
                Enter:
                1 - Show movie poster.
                2 - Buy or return a ticket.
                3 - View purchased tickets.
                4 - Exit.
                """);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch (choice){
            case "1" -> filmService.showFilms();
            case "2" -> buyReturnTicket(person);
            case "3" -> ticketService.showTickets(person);
            case "4" -> enterInSystem();
            default -> System.out.println(error);
        }
    }

    public void buyReturnTicket(Person person){
        System.out.println("""
                Enter:
                1 - Buy ticket.
                2 - Return ticket.
                """);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch (choice){
            case "1" -> ticketService.buyTicket(person);
            case "2" -> ticketService.returnTicket(person);
            default -> System.out.println(error);
        }
    }
    public void managerMenu() {

    }

    public void adminMenu() {

    }
}
