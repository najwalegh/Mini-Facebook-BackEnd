package com.irisi.facebook.mappers;

import com.irisi.facebook.dto.CommentaireDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper
public interface CommentaireMapper {

    @Autowired
    UserRepository userRepository = null;

    default String map(User user) {
        return user != null ? user.getId() : null;
    }

    default User map(String userId) {
        if (userId == null) {
            return null;
        }
        return userRepository.findById(userId).orElse(null);

    }
    @Mapping(source = "id", target = "id")
    @Mapping(source = "contenu", target = "contenu")
    @Mapping(source = "datePublication", target = "datePublication")
    @Mapping(source = "user", target = "userId")
    CommentaireDto commentaireToCommentaireDto(Commentaire commentaire);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "contenu", target = "contenu")
    @Mapping(source = "datePublication", target = "datePublication")
    @Mapping(source = "userId", target = "user")
    Commentaire commentaireDtoToCommentaire(CommentaireDto commentaireDto);
}
