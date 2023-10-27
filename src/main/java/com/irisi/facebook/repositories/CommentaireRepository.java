package com.irisi.facebook.repositories;

import com.irisi.facebook.entities.Commentaire;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends MongoRepository< Commentaire, String > {
    Commentaire findCommentaireByPosteId (String postId);
    List<Commentaire> findCommentairesByPosteId(String postId);

}

