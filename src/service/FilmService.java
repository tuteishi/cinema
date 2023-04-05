package service;

public interface FilmService {

    /**
     * Добавление фильма с билетами
     */
    void addFilmWithTickets();

    /**
     * Удаление фильма с билетами
     */
    boolean deleteFilm();

    /**
     * Редактирование фильма
     */
    boolean editFilm();

    /**
     * Поиск фильма по Id
     */
    boolean searchIdFilm (Integer id);

    /**
     * Вывод на экран списка со всеми фильмами (афиша)
     */
    public  void showFilms();

}
