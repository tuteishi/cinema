package service;

import model.Film;
import model.Person;
import model.Ticket;
import java.util.List;

public interface TicketService {

    /**
     * Добавление билетов к фильму
     */
    boolean addTicketsDB(Film film);

    /**
     * Покупка билетов
     */
    boolean buyTicket(Person person);

    /**
     * Возврат билетов
     */
    boolean returnTicket(Person person);

    /**
     * Возврат билетов менеджером
     */
    boolean returnTicketOfPerson();

    /**
     * Демонстрация билетов пользователя
     */
    List<Ticket> showPersonTickets(Person person);

    /**
     * Поиск Id фильма в билетах
     */
    boolean searchIdFilmInTicket(Integer filmId);

    /**
     * Поиск номера места в билете
     */
    boolean searchNumberOfSeatInTicket(Integer numberOfSeat);

    /**
     * Поиск свободных мест в билете
     */
    void searchFreeSeatInTicket(Integer filmId);
}
