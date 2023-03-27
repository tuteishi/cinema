package service;

import model.Film;
import model.Person;
import model.Ticket;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import repository.TicketRepository;
import repository.TicketRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TicketServiceImpl implements TicketService {
    TicketRepository ticketRepository = new TicketRepositoryImpl();
    FilmService filmService = new FilmServiceImpl();
    FilmRepository filmRepository = new FilmRepositoryImpl();
    Ticket ticket = new Ticket();


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
        Scanner scanner = new Scanner(System.in);
        Integer filmId;
        while (true) {
            System.out.println("Enter the id of the desired film: ");
            String StringId = scanner.next();
            filmId = Integer.parseInt(StringId);
            if (searchIdFilmInTicket(filmId)) {
                ticket.setFilmId(filmId);
                break;
            } else {
                System.out.println("There is no film with id: " + filmId);
            }
        }
        while (true) {
            searchFreeSeatInTicket(filmId);
            System.out.println("Enter seat number: ");
            String StringNumberOfSeat = scanner.next();
            Integer numberOfSeat = Integer.parseInt(StringNumberOfSeat);
            if (searchNumberOfSeatInTicket(numberOfSeat)) {
                ticket.setNumberOfSeat(numberOfSeat);
                break;
            } else {
                System.out.println("There is no film with number seat: " + numberOfSeat);
            }
        }
        return ticketRepository.addPersonTicketDb(person, ticket);
    }


    @Override
    public boolean returnTicket(Person person) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the id of the ticket to be return: ");
            String StringId = scanner.next();
            Integer ticketId = Integer.parseInt(StringId);
            if (searchIdTicket(ticketId)) {
                ticket.setTicketId(ticketId);
                break;
            } else {
                System.out.println("There is no ticket with id: " + ticketId);
            }
        }
        return ticketRepository.deletePersonTicketDb(person, ticket);
    }

    @Override
    public void showTickets(Person person) {
        List<Ticket> personTickets = ticketRepository.getAllTicketsDb().stream()
                .filter(ticket1 -> ticket1.getPersonId().equals(person.getId()))
                .collect(Collectors.toList());
        System.out.println(personTickets);
    }

    @Override
    public void searchFreeSeatInTicket(Integer filmId) {
        List<Integer> freeSeatTickets = ticketRepository.getAllTicketsDb().stream()
                .filter(f -> f.getFilmId().equals(filmId))
                .filter(ticket1 -> (ticket1.getPersonId() == 0))
                .map(f -> f.getNumberOfSeat())
                .collect(Collectors.toList());
        System.out.println("Free seat: " + freeSeatTickets);
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

    public boolean searchIdTicket(Integer ticketId) {
        boolean match = ticketRepository.getAllTicketsDb().stream()
                .anyMatch(f -> f.getTicketId().equals(ticketId));
        return match;

    }

}
