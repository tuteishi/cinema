package repository;

import model.Film;

import java.util.List;

public interface FilmRepository {
    boolean addFilmToDb(Film film);
    boolean deleteFilmDb(Integer id);
    Integer getFilmIdFromDb(Film film);
    public List<Film> getAllFilmsDb();
}
