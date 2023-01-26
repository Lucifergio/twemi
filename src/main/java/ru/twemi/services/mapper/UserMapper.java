package ru.twemi.services.mapper;

import org.mapstruct.Mapper;
import ru.twemi.dto.security.UserDto;
import ru.twemi.entity.User;

@Mapper
public interface UserMapper {
    User toUser(UserDto userDto);
    UserDto toUserDto(User accountUser);
}
