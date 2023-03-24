package service;

import model.Film;
import model.Person;
import model.Ticket;

import java.util.List;

public interface TicketService {

    boolean addTicketsDB(Film film);
    boolean buyTicket(Person person);
    boolean returnTicket(Person person);
    void showTickets(Person person);
    boolean searchIdFilmInTicket(Integer filmId);
    boolean searchNumberOfSeatInTicket(Integer numberOfSeat);
    public void searchFreeSeatInTicket(Integer filmId);
}
