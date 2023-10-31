package com.irisi.facebook.repositories;

import com.irisi.facebook.entities.Poste;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosteRepository extends MongoRepository< Poste , String> {
    List<Poste> findAllByUserId(String userId);

}
