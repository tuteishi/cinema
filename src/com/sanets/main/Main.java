package com.sanets.main;

import controller.PersonController;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import service.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        FilmService filmService = new FilmServiceImpl();
        FilmRepository filmRepository = new FilmRepositoryImpl();
        PersonService personService = new PersonServiceImpl();
        PersonController personController = new PersonController();
        TicketService ticketService = new TicketServiceImpl();

//        personService.createPerson();
//        System.out.println(personService.authorizationPerson());


//        personController.enterInSystem();
//        filmService.addFilmWithTickets();
//        System.out.println(filmService.addFilm());
//        filmService.showFilms();
//        System.out.println(filmService.deleteFilm());
//       personController.enterInSystem();
//        ticketService.searchFreeSeatInTicket(20);




    }
}