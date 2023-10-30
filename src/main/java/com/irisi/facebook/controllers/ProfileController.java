package com.irisi.facebook.controllers;

import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.dto.ProfileDto;
import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.Profile;
import com.irisi.facebook.services.interfaces.ProfileService;
import com.irisi.facebook.services.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    public ProfileService profileService;

    @Autowired
    public UserService userService;

    @Autowired
    public HttpSession httpSession;

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable("postId") String id) {
        ProfileDto profileDto=profileService.getProfil(id);
        return new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto){
        // Retrieve userId from the session
        String userId = (String) httpSession.getAttribute("authenticatedUser");

        UserDto existingUserDto = userService.getUserById(userId);
        if (existingUserDto != null) {
            Profile profile = new Profile();
            profile.setCitation(profileDto.getCitation());
            profile.setStatus(profileDto.getStatus());
            profile.setUserId(userId);
            ProfileDto createdProfileDto = profileService.saveProfil(profile);

            existingUserDto.setProfile(createdProfileDto);

            // Mettre à jour l'utilisateur avec la nouvelle liste de commentaires
            userService.updateUser(userId, existingUserDto);
            return new ResponseEntity<>(createdProfileDto,HttpStatus.OK);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileDto> getProfileByUserId(@PathVariable("userId") String userId) {
        ProfileDto profileDto = profileService.getProfilByUserId(userId);
        return new ResponseEntity<>(profileDto, HttpStatus.OK);
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
