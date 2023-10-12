package com.irisi.facebook.mappers;

import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = { CommentaireMapper.class, PosteMapper.class , ProfileMapper.class})
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
