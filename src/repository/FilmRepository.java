package repository;

import model.Film;

import java.util.List;

public interface FilmRepository {
    boolean addFilmToDb(Film film);
    boolean deleteFilmDb(Integer id);
    boolean editFilmName(Film film);
    boolean editFilmDate(Film film);
    boolean editFilmTime(Film film);
    Integer getFilmIdFromDb(Film film);
    public List<Film> getAllFilmsDb();
}
