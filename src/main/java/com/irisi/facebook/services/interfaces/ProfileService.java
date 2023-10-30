package com.irisi.facebook.services.interfaces;

import com.irisi.facebook.dto.ProfileDto;
import com.irisi.facebook.entities.Profile;

import java.util.List;

public interface ProfileService {
    ProfileDto saveProfil(Profile profile);
    ProfileDto getProfil (String id);
    ProfileDto updateProfil(String id,ProfileDto profileDto);
    void deleteProfil(String id);
    List<ProfileDto> allProfiles();
    ProfileDto getProfilByUserId (String userId);

}
