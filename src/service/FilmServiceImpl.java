package service;

import model.Film;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import repository.TicketRepository;
import repository.TicketRepositoryImpl;
import util.Validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class FilmServiceImpl implements FilmService {
    private static final FilmRepository filmRepository = new FilmRepositoryImpl();
    private static final TicketService ticketService = new TicketServiceImpl();
    private static final TicketRepository ticketRepository = new TicketRepositoryImpl();
    Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();
    String date;
    String time;
    String idString;
    String error = "Invalid data entered, please try again.";

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
        showFilms();
        do {
            System.out.print(System.lineSeparator() + "Enter Id film for delete: ");
            idString = scanner.next();
        }
        while (!validator.numberValid(idString));
        Integer id = Integer.parseInt(idString);
        if (searchIdFilm(id)) {
            ticketRepository.deleteTicketsOfFilm(id);
            System.out.println(System.lineSeparator() + "Film with Id " + id + " deleted.");
            return filmRepository.deleteFilmDb(id);
        } else {
            System.out.println(System.lineSeparator() + "There is no film with id " + id + ".");
            return false;
        }
    }

    @Override
    public boolean editFilm() {
        showFilms();
        Film film = new Film();
        do {
            System.out.print(System.lineSeparator() + "Enter Id film for edit: ");
            idString = scanner.next();
        }
        while (!validator.numberValid(idString));
        Integer id = Integer.parseInt(idString);
        film.setFilmId(id);
        if (searchIdFilm(id)) {
            System.out.print(System.lineSeparator() + """
                    Enter:
                    1 - Edit film name.
                    2 - Edit film date.
                    3 - Edit fim time.
                    >>>\t""");
            String choice = scanner.next();
            switch (choice) {
                case "1" -> editFilmName(film);
                case "2" -> editFilmDate(film);
                case "3" -> editFilmTime(film);
                default -> System.out.println(error);
            }
            return true;
        } else {
            System.out.println(System.lineSeparator() + "There is no film with id: " + id + ".");
            return false;
        }
    }

    private void editFilmName(Film film) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new film name: ");
        String filmName = scanner.nextLine();
        film.setFilmName(filmName);
        filmRepository.editFilmName(film);
        ticketRepository.editFilmNameInTickets(film);
    }

    private void editFilmDate(Film film) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter new date of film (dd-MM-yyyy): ");
            date = scanner.nextLine();
        }
        while (!validator.dateValid(date));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);
        film.setFilmDate(localDate);
        filmRepository.editFilmDate(film);
    }

    private void editFilmTime(Film film) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Enter new time film (HH-mm): ");
            time = scanner.nextLine();
        }
        while (!validator.timeValid(time));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm");
        LocalTime localTime = LocalTime.parse(time, timeFormatter);
        film.setFilmTime(localTime);
        filmRepository.editFilmTime(film);
    }

    @Override
    public boolean searchIdFilm(Integer id) {
        boolean match = filmRepository.getAllFilmsDb().stream()
                .anyMatch(f -> f.getFilmId().equals(id));
        return match;
    }

    @Override
    public void showFilms() {
        if (filmRepository.getAllFilmsDb().isEmpty()) {
            System.out.println("There are no movies available to watch yet.");
        } else {
            Stream<Film> stream = filmRepository.getAllFilmsDb().stream();
            stream
                    .sorted(Comparator.comparing(Film::getFilmDate))
                    .forEach(System.out::println);
        }
    }

}
