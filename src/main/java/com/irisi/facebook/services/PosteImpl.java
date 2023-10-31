package com.irisi.facebook.services;

import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.entities.*;
import com.irisi.facebook.mappers.CommentaireMapper;
import com.irisi.facebook.mappers.ImageMapper;
import com.irisi.facebook.mappers.PosteMapper;
import com.irisi.facebook.repositories.CommentaireRepository;
import com.irisi.facebook.repositories.PosteRepository;
import com.irisi.facebook.services.interfaces.PosteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor @Transactional
@Slf4j
public class PosteImpl implements PosteService {

    private final PosteMapper posteMapper;
    private final PosteRepository posteRepository;
    private CommentaireMapper commentaireMapper;
    private ImageMapper imageMapper;

    @Override
    public List<PosteDto> allUserPostes(String userId) {
        List<Poste> postes = posteRepository.findAllByUserId(userId);
        return postes.stream()
                .map(posteMapper::posteToPosteDto)
                .collect(Collectors.toList());
    }

    @Override
    public PosteDto savePoste(Poste poste) {
        Poste savedPoste= posteRepository.save(poste);
        return posteMapper.posteToPosteDto(savedPoste);
    }

    @Override
    public PosteDto getPoste(String id) {
        Optional<Poste> optionalPoste= posteRepository.findById(id);
        return optionalPoste.map(posteMapper::posteToPosteDto).orElse(null);
    }

    @Override
    public PosteDto updatePoste(String postId ,PosteDto posteDto) {
        Optional<Poste> optionalPoste = posteRepository.findById(postId);

        if (optionalPoste.isPresent()) {
            Poste existingPoste = optionalPoste.get();

            // Mettez à jour les champs en fonction de userDto
            if (posteDto.getContenu() != null) {
                existingPoste.setContenu(posteDto.getContenu());
            }
            if (posteDto.getDatePublication() != null) {
                existingPoste.setDatePublication(posteDto.getDatePublication());
            }
            if (posteDto.getLikes() != 0) {
                existingPoste.setLikes(posteDto.getLikes());
            }
            if (posteDto.getDislikes() != 0) {
                existingPoste.setDislikes(posteDto.getDislikes());
            }
            if (posteDto.getUserId() != null) {
                existingPoste.setUserId(posteDto.getUserId());
            }
            if (posteDto.getImage() != null) {
                Image image= imageMapper.imageDtoToImage(posteDto.getImage());
                existingPoste.setImage(image);
            }
            if (posteDto.getCommentaireList() != null) {
                List<Commentaire> commentaires = posteDto.getCommentaireList().stream()
                        .map(commentaireDto -> commentaireMapper.commentaireDtoToCommentaire(commentaireDto))
                        .collect(Collectors.toList());
                // Associez le commentaire à votre poste
                existingPoste.setCommentaires(commentaires);
            }

            Poste updatedPoste = posteRepository.save(existingPoste);
            return posteMapper.posteToPosteDto(updatedPoste);
        }
        return null;
    }
    @Override
    public void deletePoste(String id) {
        posteRepository.deleteById(id);
    }

    @Override
    public List<PosteDto> allPostes() {
        List<Poste> postes = posteRepository.findAll();
        return postes.stream()
                .map(posteMapper::posteToPosteDto)
                .collect(Collectors.toList());
    }

    @Override
    public PosteDto getPostById(String postId) {
        Optional<Poste> optionalPoste = posteRepository.findById(postId);

        if (optionalPoste.isPresent()) {
            Poste poste = optionalPoste.get();
            return posteMapper.posteToPosteDto(poste);
        } else {
            return null; // Ou vous pouvez lever une exception utilisateur non trouvé
        }
    }
}
