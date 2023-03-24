package repository;

import com.mysql.jdbc.MysqlDataTruncation;
import model.Film;
import model.Person;
import model.Ticket;
import util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FilmRepositoryImpl implements FilmRepository {
    @Override
    public boolean addFilmToDb(Film film) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO film (film_name,film_date,film_time) VALUES(?,?,?)");
            statement.setString(1, film.getFilmName());

            Date filmDate = Date.valueOf(film.getFilmDate());
            statement.setDate(2, filmDate);

            Time filmTime = Time.valueOf(film.getFilmTime());
            statement.setTime(3, filmTime);

            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteFilmDb(Integer id) {
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM film WHERE id=?");
            statement.setInt(1,id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Integer getFilmIdFromDb(Film film) {
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id FROM film WHERE film_name=? AND film_date=? AND film_time=?");
            statement.setString(1, film.getFilmName());
            statement.setDate(2,Date.valueOf(film.getFilmDate()));
            statement.setTime(3, Time.valueOf(film.getFilmTime()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                System.out.println(id);
                return id;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }



    @Override
    public List<Film> getAllFilmsDb() {
        List<Film> films = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM film");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Integer filmId = resultSet.getInt("id");
                String filmName = resultSet.getString("film_name");
                Date filmDate = resultSet.getDate("film_date");
                Time filmTime = resultSet.getTime("film_time");

                Film film = new Film(filmId, filmName, filmDate.toLocalDate(), filmTime.toLocalTime());
                films.add(film);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return films;
    }


}
