package ru.repository.dao;

import ru.repository.model.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    List<User> viewAll();

    User findById(Long id);

    void redact(User user);

    void delete(Long id);
}
