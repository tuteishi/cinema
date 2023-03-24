package repository;

import model.Film;
import model.Person;
import model.Ticket;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository{
    @Override
    public boolean addTicketsDb(Film film,  Integer numberOfSeat) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "INSERT INTO ticket (person_id,film_id,film_name,number_seat,coast,status) " +
                            "VALUES(?,?,?,?,?,?)");
            statement.setNull(1, 0);
            statement.setInt(2, film.getFilmId());
            statement.setString(3, film.getFilmName());
            statement.setInt(4, numberOfSeat);
            statement.setInt(5, 15);
            statement.setBoolean(6, false);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean addPersonTicketDb(Person person, Ticket ticket) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE ticket SET person_id=?,status=? WHERE film_id=? AND number_seat=?");
            statement.setInt(1, person.getId());
            statement.setBoolean(2, true);
            statement.setInt(3, ticket.getFilmId());
            statement.setInt(4, ticket.getNumberOfSeat());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePersonTicketDb(Person person, Ticket ticket) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement(
                            "UPDATE ticket SET person_id=?,status=? WHERE id=?");
            statement.setNull(1, 0);
            statement.setBoolean(2, false);
            statement.setInt(3, ticket.getTicketId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Ticket> getAllTicketsDb() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ticket");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Integer ticketId = resultSet.getInt("id");
                Integer personId = resultSet.getInt("person_id");
                Integer filmId = resultSet.getInt("film_id");
                String filmName = resultSet.getString("film_name");
                Integer numberOfSeat = resultSet.getInt("number_seat");
                Integer coast = resultSet.getInt("coast");

                Ticket ticket = new Ticket(ticketId, personId, filmId, filmName, numberOfSeat, coast);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tickets;
    }

}
