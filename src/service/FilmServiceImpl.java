package service;

import model.Film;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import util.Validator;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class FilmServiceImpl implements FilmService {
    private static final FilmRepository filmRepository = new FilmRepositoryImpl();
    private static final TicketService ticketService = new TicketServiceImpl();
    Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();
    String date;
    String time;
    String idString;

    @Override
    public Film addFilm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter film name: ");
        String filmName = scanner.nextLine();
        Film film = new Film(filmName, addDate(), addTime());
        filmRepository.addFilmToDb(film);
        return film;
    }

    private LocalDate addDate() {
        do {
            System.out.print("Enter date of film (dd-MM-yyyy): ");
            date = scanner.nextLine();
        }
        while (!validator.dateValid(date));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        return localDate;
    }

    private LocalTime addTime() {
        do {
            System.out.print("Enter time film (HH-mm): ");
            time = scanner.nextLine();
        }
        while (!validator.timeValid(time));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm");
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        return localTime;
    }


    @Override
    public void addFilmWithTickets() {
        ticketService.addTicketsDB(addFilm());
    }

    @Override
    public boolean deleteFilm() {
        do {
            System.out.print("Enter Id film for delete: ");
            idString = scanner.next();
        }
        while (!validator.numberValid(idString));
        Integer id = Integer.parseInt(idString);
        if (searchIdFilm(id)) {
            return filmRepository.deleteFilmDb(id);
        } else {
            System.out.println("There is no film with id " + id);
            return false;
        }
    }

    @Override
    public boolean searchIdFilm(Integer id) {
        boolean match = filmRepository.getAllFilmsDb().stream()
                .anyMatch(f -> f.getFilmId().equals(id));
        return match;
    }

    @Override
    public void showFilms() {
        Stream<Film> stream = filmRepository.getAllFilmsDb().stream();
        stream
                .sorted(Comparator.comparing(Film::getFilmDate))
                .forEach(System.out::println);
    }
}
