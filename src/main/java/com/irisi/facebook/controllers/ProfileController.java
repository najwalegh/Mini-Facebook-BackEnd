package com.irisi.facebook.controllers;

import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.dto.ProfileDto;
import com.irisi.facebook.services.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    public ProfileService profileService;

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable("postId") String id) {
        ProfileDto profileDto=profileService.getProfil(id);
        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto){
        ProfileDto createdProfileDto = profileService.saveProfil(profileDto);
        return new ResponseEntity<>(createdProfileDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles(){
        List<ProfileDto> profiles = profileService.allProfiles();
        return new ResponseEntity<>(profiles,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable("postId") String id) {
        profileService.deleteProfil(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<ProfileDto> partialUpdateProfile( @PathVariable("postId") String id,
                                                              @RequestBody ProfileDto profileDto) {

        ProfileDto updateProfileDto = profileService.updateProfil(id,profileDto);
        return new ResponseEntity<>(updateProfileDto,HttpStatus.OK);
    }
}
