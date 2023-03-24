package model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Film {
    Integer filmId;
    String filmName;
    LocalDate filmDate;
    LocalTime filmTime;
    List <Ticket> tickets;

    public Film(Integer filmId, String filmName, LocalDate filmDate, LocalTime filmTime) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.filmDate = filmDate;
        this.filmTime = filmTime;
    }

    public Film(String filmName, LocalDate filmDate, LocalTime filmTime) {
        this.filmName = filmName;
        this.filmDate = filmDate;
        this.filmTime = filmTime;
    }

    public Film() {
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer id) {
        this.filmId = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public LocalDate getFilmDate() {
        return filmDate;
    }

    public void setFilmDate(LocalDate filmDate) {
        this.filmDate = filmDate;
    }

    public LocalTime getFilmTime() {
        return filmTime;
    }

    public void setFilmTime(LocalTime filmTime) {
        this.filmTime = filmTime;
    }

    @Override
    public String toString() {
        return  "Id: " + filmId + " | "+
                " Film name: " + '\"' + filmName + '\"' +
                " Data show: " + filmDate +
                " Time show: " + filmTime;
    }

}
