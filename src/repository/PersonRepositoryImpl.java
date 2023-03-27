package repository;

import model.Person;
import model.Role;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepository{
    @Override
    public boolean addPersonToDb(Person person){
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO person (username, password, role) VALUES(?,?,?)");
            statement.setString(1, person.getUsername());
            statement.setString(2, person.getPassword());
            statement.setString(3, Role.USER.name());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
    }

    @Override
    public List<Person> getAllPersonsFromDb() {
        List <Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getString("role"));
                Person person = new Person(id, username, password, role);
                persons.add(person);
            }
            System.out.println(persons);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }


    @Override
    public Person getPersonByUsernameFromDb(String username) {
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getString("role"));
                Person personBd = new Person(id, username, password, role);
                return personBd;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deletePersonByIdFromDb(int id) {
        try (Connection connection = ConnectionManager.open()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM person WHERE id=?");
            statement.setInt(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
