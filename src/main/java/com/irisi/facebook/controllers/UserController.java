package com.irisi.facebook.controllers;

import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.services.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/utilisateurs")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public HttpSession httpSession;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUtilisateur(@PathVariable("userId") String id) {
        UserDto userDto=userService.getUser(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUtilisateur(@RequestBody UserDto userDto){
        UserDto createdUserDto = userService.saveUser(userDto);
        return new ResponseEntity<>(createdUserDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUtilisateurs(){
        List<UserDto> utilsateurs = userService.allUsers();
        return new ResponseEntity<>(utilsateurs,HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable("userId") String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDto> partialUpdateUtilisateur( @PathVariable("userId") String id,
                                                             @RequestBody UserDto userDto) {

        UserDto updatedUserDto = userService.updateUser(id,userDto);
        return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody UserDto userDto) {
        // Vérifier les données du formulaire par rapport à la base de données et recuperer le id de l'utilisateur authentifié
        String authenticationSuccessful_UserId = userService.authenticateUser(userDto.getAdresseEmail(), userDto.getMotDePasse());

        System.out.println("email="+userDto.getAdresseEmail()+"  "+userDto.getMotDePasse());


        if (authenticationSuccessful_UserId !=null) {
            // L'authentification réussit, vous pouvez retourner une réponse appropriée

            // Store authenticated userId information in the session
            httpSession.setAttribute("authenticatedUser", authenticationSuccessful_UserId);

            return new ResponseEntity<>("Authentication successful", HttpStatus.OK);
        } else {
            // L'authentification échoue, retournez une réponse avec un statut 401 Unauthorized
            return new ResponseEntity<>("Authentication failed", HttpStatus.NOT_FOUND);
        }
    }


}

