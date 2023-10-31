package com.irisi.facebook.services.interfaces;

import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.dto.ProfileDto;
import com.irisi.facebook.entities.Poste;

import java.util.List;


public interface PosteService {
    List<PosteDto> allUserPostes(String userId);
    PosteDto savePoste(Poste poste);
    PosteDto getPoste (String id);
    PosteDto updatePoste(String id,PosteDto posteDto);
    void deletePoste(String id);
    List<PosteDto> allPostes();
    PosteDto getPostById(String postId);
}
