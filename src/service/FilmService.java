package service;

import model.Film;

public interface FilmService {
    Film addFilm();
    void addFilmWithTickets();
    boolean deleteFilm();
    boolean searchIdFilm (Integer id);
    public  void showFilms();


}
