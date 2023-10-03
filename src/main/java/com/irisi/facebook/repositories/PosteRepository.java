package com.irisi.facebook.repositories;

import com.irisi.facebook.entities.Poste;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteRepository extends MongoRepository<Poste, String> {
}
