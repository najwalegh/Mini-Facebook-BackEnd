package com.irisi.facebook.services;

import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.entities.Poste;
import com.irisi.facebook.entities.Profile;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.mappers.PosteMapper;
import com.irisi.facebook.repositories.CommentaireRepository;
import com.irisi.facebook.repositories.PosteRepository;
import com.irisi.facebook.services.interfaces.PosteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor @Transactional
@Slf4j
public class PosteImpl implements PosteService {

    private final PosteMapper posteMapper;
    private final PosteRepository posteRepository;
    private CommentaireRepository commentaireRepository;
    @Override
    public PosteDto savePoste(PosteDto posteDto) {
        Poste poste = posteMapper.posteDtoToPoste(posteDto);
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
            if (posteDto.getNumberReaction() != 0) {
                existingPoste.setNumberReaction(posteDto.getNumberReaction());
            }
            if (posteDto.getCommentaireId() != null) {
                // Récupérez le commentaire à partir de l'identifiant
                Commentaire commentaire = commentaireRepository.findById(posteDto.getCommentaireId())
                        .orElseThrow(() -> new EntityNotFoundException("Commentaire introuvable avec l'ID : " + posteDto.getCommentaireId()));
                // Associez le commentaire à votre poste
                existingPoste.setCommentaire(commentaire);
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
}
