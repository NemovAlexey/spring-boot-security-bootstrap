package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    User findUserByEmail(String email);
    List<User> findAll();
    User findById(long id);
    void save(User user);
    void deleteById(long id);
}
