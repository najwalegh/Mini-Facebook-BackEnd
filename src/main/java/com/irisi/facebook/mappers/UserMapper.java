package com.irisi.facebook.mappers;

import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "prenom", target = "prenom")
    @Mapping(source = "dateNaissance", target = "dateNaissance")
    @Mapping(source = "adresseEmail", target = "adresseEmail")
    @Mapping(source = "motDePasse", target = "motDePasse")
    @Mapping(source = "profile", target = "profile")
    @Mapping(source = "comments", target = "comments")
    @Mapping(source = "posts", target = "posts")
    UserDto userToUserDto(User user);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "nom", target = "nom")
    @Mapping(source = "prenom", target = "prenom")
    @Mapping(source = "dateNaissance", target = "dateNaissance")
    @Mapping(source = "adresseEmail", target = "adresseEmail")
    @Mapping(source = "motDePasse", target = "motDePasse")
    @Mapping(source = "profile", target = "profile")
    @Mapping(source = "comments", target = "comments")
    @Mapping(source = "posts", target = "posts")
    User userDtoToUser(UserDto userDto);
}
