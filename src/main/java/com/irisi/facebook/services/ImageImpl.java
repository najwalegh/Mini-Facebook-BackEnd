package com.irisi.facebook.services;

import com.irisi.facebook.dto.ImageDto;
import com.irisi.facebook.entities.Image;
import com.irisi.facebook.mappers.ImageMapper;
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
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ImageImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageMapper imageMapper;

    @Override
    public ImageDto addPhoto(String title, MultipartFile file, String postId) throws IOException {
        Image photo = new Image();
        photo.setTitle(title);
        photo.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
//            photo = imageRepository.insert(photo);
        photo = imageRepository.save(photo);
        return imageMapper.imageToImageDto(photo);
    }

    @Override
    public Image getPhoto(String id) {
        return imageRepository.findById(id).get();
    }

//        @Override
//        public Image getPhotoByPostId(String postId) {
//            return imageRepository.findImageByPostId(postId);
//        }

    @Override
    public ImageDto getPhotoByPostId(String postId) {
        Optional<Image> optionalPost = imageRepository.findImageByPostId(postId);

        if (optionalPost.isPresent()) {
            Image user = optionalPost.get();
            return imageMapper.imageToImageDto(user);
        } else {
            return null; // Ou vous pouvez lever une exception utilisateur non trouvé
        }
    }
}
