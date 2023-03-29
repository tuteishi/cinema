package com.sanets.main;

import controller.PersonController;
import repository.FilmRepository;
import repository.FilmRepositoryImpl;
import repository.PersonRepository;
import repository.PersonRepositoryImpl;
import service.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        FilmService filmService = new FilmServiceImpl();
        FilmRepository filmRepository = new FilmRepositoryImpl();
        PersonService personService = new PersonServiceImpl();
        PersonRepository personRepository = new PersonRepositoryImpl();
        PersonController personController = new PersonController();
        TicketService ticketService = new TicketServiceImpl();

//        personService.createPerson();
//        System.out.println(personService.authorizationPerson());


        personController.enterInSystem();


//        filmService.deleteFilm();
//        filmService.addFilmWithTickets();
//        System.out.println(filmService.addFilm());
//        filmService.showFilms();
//        System.out.println(filmService.deleteFilm());
//       personController.enterInSystem();
//        ticketService.searchFreeSeatInTicket(20);
//        filmService.editFilm();
//        personRepository.getAllPersonsFromDb();
//            personService.deleteById();
//        ticketService.returnTicketOfPerson();



    }
}