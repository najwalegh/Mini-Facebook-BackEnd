package com.irisi.facebook.services;

import com.irisi.facebook.dto.CommentaireDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.mappers.CommentaireMapper;
import com.irisi.facebook.repositories.CommentaireRepository;
import com.irisi.facebook.services.interfaces.CommentaireService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CommentaireImpl implements CommentaireService {
    private final CommentaireRepository commentaireRepository;
    private final CommentaireMapper commentaireMapper;

    @Override
    public CommentaireDto saveCommentaire(CommentaireDto commentaireDto) {
        Commentaire commentaire = commentaireMapper.commentaireDtoToCommentaire(commentaireDto);
        Commentaire updatedCommentaire = commentaireRepository.save(commentaire);
        return commentaireMapper.commentaireToCommentaireDto(updatedCommentaire);
    }

    @Override
    public CommentaireDto getCommentaire(String id) {
        Optional<Commentaire> optionalCommentaire = commentaireRepository.findById(id);
        return optionalCommentaire.map(commentaireMapper::commentaireToCommentaireDto).orElse(null);
    }

    @Override
    public CommentaireDto updateCommentaire(String id,CommentaireDto commentaireDto) {

        Optional<Commentaire> optionalComment = commentaireRepository.findById(id);

        if (optionalComment.isPresent()) {
            Commentaire existingComment = optionalComment.get();

            // Mettez à jour les champs en fonction de userDto
            if (commentaireDto.getContenu() != null) {
                existingComment.setContenu(commentaireDto.getContenu());
            }
            if (commentaireDto.getDatePublication() != null) {
                existingComment.setDatePublication(commentaireDto.getDatePublication());
            }
            if (commentaireDto.getUserId() != null) {
                existingComment.setId(commentaireDto.getUserId());
            }

            // Enregistrez les modifications dans la base de données
            Commentaire updatedComment = commentaireRepository.save(existingComment);

            return commentaireMapper.commentaireToCommentaireDto(updatedComment);
        }
        return null;
    }

    @Override
    public void deleteCommentaire(String id) {
        commentaireRepository.deleteById(id);
    }

    @Override
    public List<CommentaireDto> allCommentaires() {
        List<Commentaire> commentaires = commentaireRepository.findAll();
        return commentaires.stream()
                .map(commentaireMapper::commentaireToCommentaireDto)
                .collect(Collectors.toList());
    }
}
