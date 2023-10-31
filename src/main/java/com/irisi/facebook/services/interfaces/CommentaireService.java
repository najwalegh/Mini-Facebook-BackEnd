package com.irisi.facebook.services.interfaces;

import com.irisi.facebook.dto.CommentaireDto;
import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.Commentaire;

import java.util.List;

public interface CommentaireService {
    CommentaireDto saveCommentaire(Commentaire commentaire);
    CommentaireDto getCommentaire (String id);
    CommentaireDto updateCommentaire(String id ,CommentaireDto commentaireDto);
    void deleteCommentaire(String id);
    List<CommentaireDto> allCommentaires();
    List<CommentaireDto> getCommentairesByPostId(String id);
}
