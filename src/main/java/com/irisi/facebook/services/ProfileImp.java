package com.irisi.facebook.services;

import com.irisi.facebook.dto.ProfileDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.entities.Poste;
import com.irisi.facebook.entities.Profile;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.mappers.ProfileMapper;
import com.irisi.facebook.mappers.UserMapper;
import com.irisi.facebook.repositories.ProfileRepository;
import com.irisi.facebook.services.interfaces.ProfileService;
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
public class ProfileImp  implements ProfileService {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;
    private final UserMapper userMapper;

    @Override
    public ProfileDto getProfilByUserId(String userId) {
        Profile profile = profileRepository.findByUserId(userId);
        return profileMapper.profileToProfileDto(profile);
    }

    @Override
    public ProfileDto saveProfil(Profile profile) {
//        Profile profile = profileMapper.profileDtoToProfile(profileDto);
        Profile savedProfile= profileRepository.save(profile);
        return profileMapper.profileToProfileDto(savedProfile);
    }

    @Override
    public ProfileDto getProfil(String id) {
        Optional<Profile> optionalProfil = profileRepository.findById(id);
        return optionalProfil.map(profileMapper::profileToProfileDto).orElse(null);
    }

    @Override
    public ProfileDto updateProfil(String id,ProfileDto profileDto) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);

        if (optionalProfile.isPresent()) {
            Profile existingProfile = optionalProfile.get();

            // Mettez à jour les champs en fonction de userDto
            if (profileDto.getCitation() != null) {
                existingProfile.setCitation(profileDto.getCitation());
            }
            if (profileDto.getStatus() != null) {
                existingProfile.setStatus(profileDto.getStatus());
            }

            // a revoir
            if (profileDto.getUserId()!= null) {
                existingProfile.setUserId(profileDto.getUserId());
            }
            // Enregistrez les modifications dans la base de données
            Profile updatedProfile = profileRepository.save(existingProfile);

            return profileMapper.profileToProfileDto(updatedProfile);
        }

        return null;
    }

    @Override
    public void deleteProfil(String id) {
        profileRepository.deleteById(id);
    }

    @Override
    public List<ProfileDto> allProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(profileMapper::profileToProfileDto)
                .collect(Collectors.toList());
    }
}
