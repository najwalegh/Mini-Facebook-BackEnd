package com.irisi.facebook.controllers;

import com.irisi.facebook.dto.ImageDto;
import com.irisi.facebook.dto.PosteDto;
import com.irisi.facebook.entities.Image;
import com.irisi.facebook.services.interfaces.ImageService;
import com.irisi.facebook.services.interfaces.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL

@RestController
@RequestMapping("/photos")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private PosteService posteService;

    @PostMapping
    public ResponseEntity<String> uploadPhoto(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file,
            @RequestParam("postId") String postId) {
        try {
            ImageDto image = imageService.addPhoto(title, file, postId);
            System.out.println("l'id de l'image est "+image.getId());
            // Récupérer le poste existant
            PosteDto existingPosteDto = posteService.getPostById(postId);
            existingPosteDto.setImage(image);

            // Mettre à jour le poste avec la nouvelle image
            posteService.updatePoste(postId, existingPosteDto);

            return ResponseEntity.ok("Image uploaded successfully. Image ID: " + image.getTitle());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<byte[]> getPhoto(@PathVariable("id") String id) {
//        Image image = imageService.getPhoto(id);
//
//
//        if (image != null) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            return new ResponseEntity<>(image.getImage().getData(), headers, HttpStatus.OK);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/{postId}")
    public ResponseEntity<byte[]> getPhotoByPostId(@PathVariable("postId") String postId) {
        PosteDto posteDto=posteService.getPoste(postId);
        if (posteDto !=null) {
            if (posteDto.getImage() != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                return new ResponseEntity<>(posteDto.getImage().getImage().getData(), headers, HttpStatus.OK);
            }else {
                return ResponseEntity.notFound().build();
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}