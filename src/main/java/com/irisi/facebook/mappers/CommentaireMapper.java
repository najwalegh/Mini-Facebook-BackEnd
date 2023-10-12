package com.irisi.facebook.mappers;

import com.irisi.facebook.dto.CommentaireDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.repositories.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CommentaireMapper {
    CommentaireDto commentaireToCommentaireDto(Commentaire commentaire);
    Commentaire commentaireDtoToCommentaire(CommentaireDto commentaireDto);
}
