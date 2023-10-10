package com.irisi.facebook.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "commentaire")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString@Builder
public class Commentaire {

    @Id
    private String id;

    private String contenu;

    @Field(name = "date_publication")
    private Date datePublication;

    // L'utilisateur qui a créé ce commentaire
    private User user;

}
