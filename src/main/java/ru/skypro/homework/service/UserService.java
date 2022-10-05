package ru.skypro.homework.service;

import ru.skypro.homework.entity.User;

import java.util.Collection;
import java.util.Collections;

public interface UserService {
    User createUser(User user);

    Collection<User> getUsers();

    User update(User user);

    User getUserById(long id);
}