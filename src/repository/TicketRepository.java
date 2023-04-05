package repository;

import model.Film;
import model.Person;
import model.Ticket;
import java.util.List;

public interface TicketRepository {
     boolean addTicketsDb(Film film,  Integer numberOfSeat);
     boolean deleteTicketsOfFilm(Integer id);
     boolean editFilmNameInTickets(Film film);
     boolean addPersonTicketDb(Person person, Ticket ticket);
     boolean deletePersonTicketDb(Person person, Ticket ticket);
     List<Ticket> getAllTicketsDb();

}
