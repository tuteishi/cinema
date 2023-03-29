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
    Person authorizationPerson();

    /**
     * Получение пользователя по логину
     * */
    Person getByUsername(String username);

    /**
     * Получение всех пльзователей
     * */
    public void showAllPersons();

    /**
     * Обновление пользователя по Id
     * */
    boolean updatePersonById ();

    /**
     * Удаление пользователя из базы данных
     * */
    boolean deleteById();

    public boolean searchIdPerson(Integer id);
}
