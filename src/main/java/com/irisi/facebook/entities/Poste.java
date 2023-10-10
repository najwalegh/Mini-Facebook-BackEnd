package com.irisi.facebook.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "poste")
@Data @AllArgsConstructor @NoArgsConstructor @ToString@Builder
public class Poste {

    @Id
    private String id;

    private String contenu;

    @DBRef
    private Commentaire commentaire;

    @Field(name = "number_reaction")
    private int numberReaction;

    @Field(name = "date_publication")
    private Date datePublication;

    @DBRef
    private User user;
}
