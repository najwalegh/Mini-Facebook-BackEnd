package com.irisi.facebook.controllers;

import com.irisi.facebook.entities.Image;
import com.irisi.facebook.services.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/photos")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<String> uploadPhoto(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file,
            @RequestParam("postId") String postId) {
        try {
            String imageId = imageService.addPhoto(title, file, postId);
            return ResponseEntity.ok("Image uploaded successfully. Image ID: " + imageId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("id") String id) {
        Image image = imageService.getPhoto(id);

        if (image != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(image.getImage().getData(), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}