package com.irisi.facebook.services;

import com.irisi.facebook.entities.Image;
import com.irisi.facebook.repositories.ImageRepository;
import com.irisi.facebook.services.interfaces.ImageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ImageImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public String addPhoto(String title, MultipartFile file, String postId) throws IOException {
        Image photo = new Image();
        photo.setTitle(title);
        photo.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
//            photo = imageRepository.insert(photo);
        photo = imageRepository.save(photo);
        return photo.getId();
    }

    @Override
    public Image getPhoto(String id) {
        return imageRepository.findById(id).get();
    }

}
