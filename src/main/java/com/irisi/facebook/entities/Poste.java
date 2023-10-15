package com.irisi.facebook.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "postes")
@Data @AllArgsConstructor @NoArgsConstructor @ToString@Builder
public class Poste {

    @Id
    private String id;

    private String contenu;

    @Field(name = "nombre_des_likes")
    private int likes;

    @Field(name = "nombre_des_dislikes")
    private int dislikes;

    @Field(name = "date_publication")
    private Date datePublication;

    private String userId;

    @DBRef
    private List<Commentaire> commentaires;

    @DBRef
    private Image image;
}
