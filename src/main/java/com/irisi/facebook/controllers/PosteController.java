package com.irisi.facebook.controllers;

import com.irisi.facebook.dto.CommentaireDto;
import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.entities.Poste;
import com.irisi.facebook.mappers.ImageMapper;
import com.irisi.facebook.services.interfaces.CommentaireService;
import com.irisi.facebook.services.interfaces.PosteService;
import com.irisi.facebook.services.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
@RestController
@RequestMapping("/postes")
public class PosteController {
    @Autowired
    public PosteService posteService;

    @Autowired
    public UserService userService;

    @Autowired
    public ImageMapper imageMapper;

    @Autowired
    public CommentaireService commentaireService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/{postId}")
    public ResponseEntity<PosteDto> getPoste(@PathVariable("postId") String id) {
        PosteDto posteDto=posteService.getPoste(id);
        return new ResponseEntity<>(posteDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PosteDto> createPoste( @RequestBody PosteDto posteDto) {

<<<<<<< Updated upstream
        // Retrieve userId from the session
        String userId = (String) httpSession.getAttribute("authenticatedUser");

        UserDto existingUserDto = userService.getUserById(userId);
=======
        System.out.println("ma userID is "+posteDto.getUserId());
        System.out.println("mon contenu is "+posteDto.getContenu());
        String userId = posteDto.getUserId();
        //  String userId ="651c871a8c1da97cad48d4df";
        UserDto existingUserDto = userService.getUserById(userId);
        System.out.println("ma userID is "+userId);

>>>>>>> Stashed changes
        if (existingUserDto != null) {
            Poste poste = new Poste();
            poste.setId(posteDto.getId());
            poste.setContenu(posteDto.getContenu());
            poste.setLikes(posteDto.getLikes());
            poste.setDislikes(posteDto.getDislikes());
            poste.setDatePublication(posteDto.getDatePublication());
            poste.setUserId(userId);
            poste.setImage(imageMapper.imageDtoToImage(posteDto.getImage()));

            // Convertir la liste de CommentaireDto en une liste de Commentaire
            List<CommentaireDto> commentaireDtoList = posteDto.getCommentaireList();

            if (commentaireDtoList == null) {
                commentaireDtoList = new ArrayList<>();
            }
            List<Commentaire> commentaireList = new ArrayList<>();
            for (CommentaireDto commentaireDto : commentaireDtoList) {
                Commentaire commentaire = new Commentaire();
                commentaire.setContenu(commentaireDto.getContenu());
                commentaire.setDatePublication(commentaireDto.getDatePublication());
                commentaire.setUserId(commentaireDto.getUserId());
                commentaire.setPosteId(commentaireDto.getPosteId());
                commentaireList.add(commentaire);
            }
            poste.setCommentaires(commentaireList);

            // Enregistrer le Poste
            PosteDto createdPosteDto = posteService.savePoste(poste);


            // Récupérer la liste des postes de l'utilisateur
            List<PosteDto> postes = existingUserDto.getPosts();
            if (postes == null) {
                postes = new ArrayList<>();
            }

            // Ajouter le nouveau commentaire à la liste
            postes.add(createdPosteDto);
            existingUserDto.setPosts(postes);

            // Mettre à jour l'utilisateur avec la nouvelle liste de commentaires
            userService.updateUser(userId, existingUserDto);
            return new ResponseEntity<>(createdPosteDto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PosteDto>> getAllPostes(){
        List<PosteDto> postes = posteService.allPostes();
        return new ResponseEntity<>(postes,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePoste(@PathVariable("postId") String id) {
        posteService.deletePoste(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PosteDto> partialUpdatePoste( @PathVariable("postId") String id,
                                                        @RequestBody PosteDto posteDto) {

        PosteDto updatePosteDto = posteService.updatePoste(id,posteDto);
        return new ResponseEntity<>(updatePosteDto,HttpStatus.OK);
    }

}
