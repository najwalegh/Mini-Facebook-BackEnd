package com.irisi.facebook.controllers;

import com.irisi.facebook.dto.CommentaireDto;
import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.services.interfaces.CommentaireService;
import com.irisi.facebook.services.interfaces.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentaires")
public class CommentaireController {
    @Autowired
    public CommentaireService commentaireService;

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentaireDto> getUtilisateur(@PathVariable("commentId") String id) {
        CommentaireDto commentaireDto=commentaireService.getCommentaire(id);
        return new ResponseEntity<>(commentaireDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentaireDto> createUtilisateur(@RequestBody CommentaireDto commentaireDto){
        CommentaireDto createdCommentDto = commentaireService.saveCommentaire(commentaireDto);
        return new ResponseEntity<>(createdCommentDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentaireDto>> getAllUtilisateurs(){
        List<CommentaireDto> commentaires = commentaireService.allCommentaires();
        return new ResponseEntity<>(commentaires,HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable("commentId") String id) {
        commentaireService.deleteCommentaire(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentaireDto> partialUpdateUtilisateur( @PathVariable("commentId") String id,
                                                              @RequestBody CommentaireDto commentaireDto) {

        CommentaireDto updateCommentDto = commentaireService.updateCommentaire(id,commentaireDto);
        return new ResponseEntity<>(updateCommentDto,HttpStatus.OK);
    }


}
