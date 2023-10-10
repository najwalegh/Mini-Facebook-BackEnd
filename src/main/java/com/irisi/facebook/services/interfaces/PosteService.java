package com.irisi.facebook.services.interfaces;

import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.dto.ProfileDto;

import java.util.List;

public interface PosteService {

    PosteDto savePoste(PosteDto posteDto);
    PosteDto getPoste (String id);
    PosteDto updatePoste(String id,PosteDto posteDto);
    void deletePoste(String id);
    List<PosteDto> allPostes();
}
