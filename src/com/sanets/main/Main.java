package com.sanets.main;

import controller.PersonController;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import service.FilmService;
import service.FilmServiceImpl;
import service.PersonService;
import service.PersonServiceImpl;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        FilmService filmService = new FilmServiceImpl();
        FilmRepository filmRepository = new FilmRepositoryImpl();
        PersonService personService = new PersonServiceImpl();
        PersonController personController = new PersonController();

//        personService.createPerson();
//        personService.authorizationPerson();

//        personController.enterInSystem();
        filmService.addFilmToDb();
//        filmService.showFilms();
//        System.out.println(filmService.deleteFilm());
//       personController.enterInSystem();


//        filmService.addFilmToDb();

    }
}