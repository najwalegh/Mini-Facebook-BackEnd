package com.irisi.facebook.controllers;

import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.services.interfaces.UserService;
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





}
