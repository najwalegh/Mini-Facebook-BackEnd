package com.irisi.facebook.mappers;

import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.entities.Poste;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PosteMapper {
    PosteMapper INSTANCE = Mappers.getMapper(PosteMapper.class);

    default String map(Commentaire commentaire) {
        return commentaire != null ? commentaire.getId() : null;
    }

    @Named("mapToCommentaire")
    default Commentaire mapToCommentaire(String commentaireId) {
        Commentaire commentaire = new Commentaire();
        commentaire.setId(commentaireId);
        return commentaire;
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "contenu", target = "contenu")
    @Mapping(source = "commentaire", target = "commentaireId")
    @Mapping(source = "numberReaction", target = "numberReaction")
    @Mapping(source = "datePublication", target = "datePublication")
    PosteDto posteToPosteDto(Poste poste);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "contenu", target = "contenu")
    @Mapping(source = "commentaireId", target = "commentaire",qualifiedByName = "mapToCommentaire")
    @Mapping(source = "numberReaction", target = "numberReaction")
    @Mapping(source = "datePublication", target = "datePublication")
    Poste posteDtoToPoste(PosteDto posteDto);
}
