package com.irisi.facebook.services.interfaces;

import com.irisi.facebook.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    ProfileDto saveProfil(ProfileDto profileDto);
    ProfileDto getProfil (String id);
    ProfileDto updateProfil(String id,ProfileDto profileDto);
    void deleteProfil(String id);
    List<ProfileDto> allProfiles();
}
