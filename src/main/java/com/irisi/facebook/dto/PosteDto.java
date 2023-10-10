package com.irisi.facebook.dto;

import com.irisi.facebook.entities.Commentaire;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PosteDto {
    private String id;
    private String contenu;
    private String commentaireId;  // L'ID du commentaire associé
    private int numberReaction;
    private Date datePublication;
}
