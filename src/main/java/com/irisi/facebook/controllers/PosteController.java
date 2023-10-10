package com.irisi.facebook.controllers;

import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.services.interfaces.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
@RestController
@RequestMapping("/postes")
public class PosteController {
    @Autowired
    public PosteService posteService;

    @GetMapping("/{postId}")
    public ResponseEntity<PosteDto> getUtilisateur(@PathVariable("postId") String id) {
        PosteDto posteDto=posteService.getPoste(id);
        return new ResponseEntity<>(posteDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PosteDto> createUtilisateur(@RequestBody PosteDto posteDto){
        PosteDto createdPosteDto = posteService.savePoste(posteDto);
        return new ResponseEntity<>(createdPosteDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PosteDto>> getAllUtilisateurs(){
        List<PosteDto> postes = posteService.allPostes();
        return new ResponseEntity<>(postes,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable("postId") String id) {
        posteService.deletePoste(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PosteDto> partialUpdateUtilisateur( @PathVariable("postId") String id,
                                                             @RequestBody PosteDto posteDto) {

        PosteDto updatePosteDto = posteService.updatePoste(id,posteDto);
        return new ResponseEntity<>(updatePosteDto,HttpStatus.OK);
    }

}
