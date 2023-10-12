package com.irisi.facebook.repositories;

import com.irisi.facebook.entities.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image,String> {
}
