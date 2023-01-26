package ru.twemi.services;

import ru.twemi.dto.security.UserDto;
import ru.twemi.entity.User;

import java.util.List;

public interface UserService {
    UserDto register(UserDto userDto);
    UserDto update(UserDto userDto);
    User findByUsername(String username);
    //    AccountUser update(AccountUser userDto);
    UserDto findById(Long id);
    List<UserDto> findAll();
    void deleteById(Long id);
}
