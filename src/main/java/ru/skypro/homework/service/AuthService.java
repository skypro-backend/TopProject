package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.User;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(User user);
}
