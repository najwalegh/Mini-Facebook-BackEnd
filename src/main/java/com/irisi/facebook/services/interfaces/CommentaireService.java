package com.irisi.facebook.services.interfaces;

import com.irisi.facebook.dto.CommentaireDto;
import com.irisi.facebook.dto.UserDto;

import java.util.List;

public interface CommentaireService {
    CommentaireDto saveCommentaire(CommentaireDto commentaireDto);
    CommentaireDto getCommentaire (String id);
    CommentaireDto updateCommentaire(String id ,CommentaireDto commentaireDto);
    void deleteCommentaire(String id);
    List<CommentaireDto> allCommentaires();
}
