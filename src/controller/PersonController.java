package controller;

import model.Person;
import repository.PersonRepository;
import repository.PersonRepositoryImpl;
import service.*;

import java.util.Optional;
import java.util.Scanner;

public class PersonController {
    PersonService personService = new PersonServiceImpl();
    FilmService filmService = new FilmServiceImpl();
    TicketService ticketService = new TicketServiceImpl();
    String error = "Invalid data entered, please try again.";

    public void enterInSystem() {
        while (true) {
            System.out.println(System.lineSeparator() + """
                    Enter:
                    1 - Register in application.
                    2 - Log into application.
                    3 - Exit.
                                     """);
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            switch (choice) {
                case "1" -> personService.createPerson();
                case "2" -> roleMenu(personService.authorizationPerson());
                case "3" -> System.exit(1);
                default -> System.out.println(error);
            }
        }
    }

    public void roleMenu(Person person) {
        Optional<Person> opt = Optional.ofNullable(person);
        if (opt.isPresent()) {
            switch (person.getRole()) {
                case USER -> userMenu(person);
                case MANAGER -> managerMenu();
                case ADMIN -> adminMenu();
            }
        }else {
            enterInSystem();
        }

    }

    public void userMenu(Person person) {
        while (true) {
            System.out.println(System.lineSeparator() + """
                    Enter:
                    1 - Show movie poster.
                    2 - Buy or return a ticket.
                    3 - View purchased tickets.
                    4 - Exit.
                    """);
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            switch (choice) {
                case "1" -> filmService.showFilms();
                case "2" -> buyReturnTicket(person);
                case "3" -> ticketService.showPersonTickets(person);
                case "4" -> enterInSystem();
                default -> System.out.println(error);
            }
        }
    }

    public void buyReturnTicket(Person person) {
        while (true) {
            System.out.println(System.lineSeparator() + """
                    Enter:
                    1 - Buy ticket.
                    2 - Return ticket.
                    3 - Back to menu.
                    """);
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            switch (choice) {
                case "1" -> ticketService.buyTicket(person);
                case "2" -> ticketService.returnTicket(person);
                case "3" -> userMenu(person);
                default -> System.out.println(error);
            }
        }
    }

    public void managerMenu() {
        while (true) {
            System.out.println(System.lineSeparator() + """
                    Enter:
                    1 - Show movie poster.
                    2 - Edit film.
                    3 - Return user ticket.
                    4 - Exit.
                    """);
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            switch (choice) {
                case "1" -> filmService.showFilms();
                case "2" -> filmService.editFilm();
                case "3" -> ticketService.returnTicketOfPerson();
                case "4" -> enterInSystem();
                default -> System.out.println(error);
            }
        }
    }

    public void adminMenu() {
        while (true) {
            System.out.println(System.lineSeparator() + """
                    Enter:
                    1 - Delete or edit a user.
                    2 - Add, delete or edit film.
                    3 - Exit.
                    """);
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            switch (choice) {
                case "1" -> deleteEditUser();
                case "2" -> deleteEditFilm();
                case "3" -> enterInSystem();
                default -> System.out.println(error);
            }
        }
    }

    public void deleteEditUser() {
        while (true) {
            System.out.println(System.lineSeparator() + """
                    Enter:
                    1 - Delete user.
                    2 - Edit role user.
                    3 - Back to menu.
                    """);
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            switch (choice) {
                case "1" -> personService.deleteById();
                case "2" -> personService.updatePersonById();
                case "3" -> adminMenu();
                default -> System.out.println(error);
            }
        }
    }

    public void deleteEditFilm() {
        while (true) {
            System.out.println(System.lineSeparator() + """
                    Enter:
                    1 - Add film.
                    2 - Delete film.
                    3 - Edit film.
                    4 - Back to menu.
                    """);
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            switch (choice) {
                case "1" -> filmService.addFilmWithTickets();
                case "2" -> filmService.deleteFilm();
                case "3" -> filmService.editFilm();
                case "4" -> adminMenu();
                default -> System.out.println(error);
            }
        }
    }
}
