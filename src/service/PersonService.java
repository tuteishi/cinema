package service;

import model.Person;

import java.util.List;

/**
 * Сервис для работы с пользователем
 */
public interface PersonService {
    /**
     * Сохранение нового пользователя в базу данных
     * */
    boolean createPerson();

    /**
     * Авторизация пользователя
     * */
    String authorizationPerson();

    /**
     * Получение пользователя по логину
     * */
    Person getByUsername(String username);

    /**
     * Получение всех пльзователей
     * */
    List <Person> getAllPersons();

    /**
     * Обновление пользователя по Id
     * */
    boolean updatePersonById (Long id, Person person);

    /**
     * Удаление пользователя из базы данных
     * */
    boolean deleteById(int id);
}
