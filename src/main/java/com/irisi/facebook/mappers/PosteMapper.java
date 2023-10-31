package com.irisi.facebook.mappers;

import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.entities.Poste;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring",uses = ImageMapper.class)
public interface PosteMapper {
    PosteDto posteToPosteDto(Poste poste);
    Poste posteDtoToPoste(PosteDto posteDto);
}
