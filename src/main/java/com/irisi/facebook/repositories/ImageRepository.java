package com.irisi.facebook.repositories;

import com.irisi.facebook.entities.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImageRepository extends MongoRepository<Image,String> {
    Optional<Image> findImageByPostId(String postId);

}
