package com.irisi.facebook.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profil")
@Data @AllArgsConstructor @NoArgsConstructor @ToString @Builder
public class Profile {

    @Id
    private String id;
    private String status;
    private String citation;
    // L'utilisateur qui a créé ce commentaire
    private String userId;

}
