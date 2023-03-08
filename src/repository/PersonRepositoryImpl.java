package repository;

import model.Person;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository{
    @Override
    public boolean create(Person person){
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement statement =
                    connection.prepareCall("INSERT INTO person (username,password) VALUES(?,?)");
            statement.setString(1, person.getUsername());
            statement.setString(2, person.getPassword());
            statement.execute();
            return true;
        } catch (SQLException e) {
            return  false;
        }


    }

    @Override
    public List<Person> getAllPersons() {
        return null;
    }

    @Override
    public Person getPerson(Integer id) {
        return null;
    }

    @Override
    public Person update(Person person, Integer id) {
        return null;
    }
}
