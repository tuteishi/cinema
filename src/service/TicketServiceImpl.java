package service;

import model.Film;
import model.Person;
import model.Ticket;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import repository.TicketRepository;
import repository.TicketRepositoryImpl;
import util.Validator;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {
    private static final TicketRepository ticketRepository = new TicketRepositoryImpl();
    private static final FilmService filmService = new FilmServiceImpl();
    private static final FilmRepository filmRepository = new FilmRepositoryImpl();
    private static final PersonService personService = new PersonServiceImpl();
    Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();
    Ticket ticket = new Ticket();
    Integer filmId;
    String stringId;
    String StringNumberOfSeat;

    @Override
    public boolean addTicketsDB(Film film) {
        int count = 1;
        film.setFilmId(filmRepository.getFilmIdFromDb(film));
        while (count <= 10) {
            ticketRepository.addTicketsDb(film, count);
            count++;
        }
        return false;
    }

    @Override
    public boolean buyTicket(Person person) {
        filmService.showFilms();
        if (idForBuyTicket() != 0) {
            if (numberOfSeatForBuyTicket() != 0) {
                return ticketRepository.addPersonTicketDb(person, ticket);
            }
        }
        return false;
    }

    private Integer idForBuyTicket() {
        do {
            System.out.println(System.lineSeparator() + "Enter the id of the desired film: ");
            stringId = scanner.next();
        } while (!validator.numberValid(stringId));
        filmId = Integer.parseInt(stringId);
        if (searchIdFilmInTicket(filmId)) {
            searchFreeSeatInTicket(filmId);
            ticket.setFilmId(filmId);
            return filmId;
        } else {
            System.out.println(System.lineSeparator() + "There is no film with id: " + filmId + ".");
            return 0;
        }
    }

    private Integer numberOfSeatForBuyTicket() {
        do {
            System.out.println(System.lineSeparator() + "Enter seat number: ");
            StringNumberOfSeat = scanner.next();
        } while (!validator.numberValid(StringNumberOfSeat));
        Integer numberOfSeat = Integer.parseInt(StringNumberOfSeat);
        if (searchNumberOfSeatInTicket(numberOfSeat)) {
            ticket.setNumberOfSeat(numberOfSeat);
            System.out.println("Ticket purchased.");
            return numberOfSeat;
        } else {
            System.out.println(System.lineSeparator() + "There is no film with number seat: " + numberOfSeat + ".");
            return 0;
        }
    }


    @Override
    public boolean returnTicket(Person person) {
        Scanner scanner = new Scanner(System.in);
        while (!showPersonTickets(person).isEmpty()) {
            do {
                System.out.print(System.lineSeparator() + "Enter the id of the ticket to be return: ");
                stringId = scanner.next();
            } while (!validator.numberValid(stringId));
            Integer ticketId = Integer.parseInt(stringId);
            if (searchIdTicketInPersonTicket(person, ticketId)) {
                    ticket.setTicketId(ticketId);
                    System.out.println(System.lineSeparator() + "Ticket return.");
                    return ticketRepository.deletePersonTicketDb(person, ticket);
            } else {
                System.out.println(System.lineSeparator() + "There is no ticket with id: " + ticketId + ".");
                break;
            }
        }
        return false;
    }

    private boolean searchIdTicketInPersonTicket(Person person, Integer ticketId) {
        boolean match = searchPersonTicket(person).stream()
                .anyMatch(f -> f.getTicketId().equals(ticketId));
        return match;
    }

    @Override
    public boolean returnTicketOfPerson() {
        personService.showAllPersons();
        String idString;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print(System.lineSeparator() + "Enter user Id for show user tickets: ");
            idString = scanner.next();
        }
        while (!validator.numberValid(idString));
        Integer id = Integer.parseInt(idString);
        Person person = new Person();
        person.setId(id);
        if (personService.searchIdPerson(id)) {
            if (searchPersonTicket(person).isEmpty()) {
                return false;
            } else {
                return returnTicket(person);
            }
        } else {
            System.out.println(System.lineSeparator() + "There is no user with Id: " + id + ".");
            return false;
        }
    }

    @Override
    public List<Ticket> showPersonTickets(Person person) {
        List<Ticket> personTickets = ticketRepository.getAllTicketsDb().stream()
                .filter(ticket1 -> ticket1.getPersonId().equals(person.getId()))
                .collect(Collectors.toList());
        if (personTickets.isEmpty()) {
            System.out.println(System.lineSeparator() + "No tickets purchased...");
        } else {
            System.out.println(System.lineSeparator());
            personTickets.stream()
                    .forEach(System.out::println);
        }
        return personTickets;
    }

    @Override
    public void searchFreeSeatInTicket(Integer filmId) {
        List<Integer> freeSeatTickets = ticketRepository.getAllTicketsDb().stream()
                .filter(f -> f.getFilmId().equals(filmId))
                .filter(ticket1 -> (ticket1.getPersonId() == 0))
                .map(f -> f.getNumberOfSeat())
                .collect(Collectors.toList());
        if (freeSeatTickets.isEmpty()) {
            System.out.println(System.lineSeparator() + "Tickets are over.");
        } else {
            System.out.println("Free seat: " + freeSeatTickets);
        }
    }

    public boolean searchIdFilmInTicket(Integer filmId) {
        boolean match = ticketRepository.getAllTicketsDb().stream()
                .anyMatch(f -> f.getFilmId().equals(filmId));
        return match;
    }

    public boolean searchNumberOfSeatInTicket(Integer numberOfSeat) {
        boolean match = ticketRepository.getAllTicketsDb().stream()
                .anyMatch(f -> f.getNumberOfSeat().equals(numberOfSeat));
        return match;
    }

    public List<Ticket> searchPersonTicket(Person person) {
        List<Ticket> personTickets = ticketRepository.getAllTicketsDb().stream()
                .filter(ticket1 -> ticket1.getPersonId().equals(person.getId()))
                .collect(Collectors.toList());
        if (personTickets.isEmpty()) {
            System.out.println(System.lineSeparator() + "No tickets purchased...");
        }
            return personTickets;
    }

}
