package model;

public class Ticket {
    Integer ticketId;
    Integer personId;
    Integer filmId;
    String filmName;
    Integer numberOfSeat;
    Integer coast;
    boolean sail;

    public Ticket(Integer ticketId, Integer personId, Integer filmId, String fimName,
                  Integer numberOfSeat, Integer coast, boolean sail) {
        this.ticketId = ticketId;
        this.personId = personId;
        this.filmId = filmId;
        this.filmName = fimName;
        this.numberOfSeat = numberOfSeat;
        this.coast = coast;
        this.sail = sail;
    }

    public Ticket(Integer ticketId, Integer personId, Integer filmId, String fimName,
                  Integer numberOfSeat, Integer coast) {
        this.ticketId = ticketId;
        this.personId = personId;
        this.filmId = filmId;
        this.filmName = fimName;
        this.numberOfSeat = numberOfSeat;
        this.coast = coast;
    }


    public Ticket() {
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getFimName() {
        return filmName;
    }

    public void setFimName(String fimName) {
        this.filmName = fimName;
    }

    public Integer getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(Integer numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public Integer getCoast() {
        return coast;
    }

    public void setCoast(Integer coast) {
        this.coast = coast;
    }

    public boolean isSail() {
        return sail;
    }

    public void setSail(boolean sail) {
        this.sail = sail;
    }

    @Override
    public String toString() {
        return "Ticket Id = " + ticketId +
               ", person Id = " + personId +
               ", film Id = " + filmId +
               ", filmName = '" + filmName + '\'' +
               ", number of seat = " + numberOfSeat +
               ", coast = " + coast;
    }
}
