package service;

import model.Film;

public interface FilmService {
    void addFilmWithTickets();
    boolean deleteFilm();
    boolean editFilm();
    boolean searchIdFilm (Integer id);
    public  void showFilms();


}
