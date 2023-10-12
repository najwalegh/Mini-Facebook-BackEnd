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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PosteDto {
    private String id;
    private String contenu;
    private int likes;
    private int dislikes;
    private Date datePublication;
    private String userId;
    private List<CommentaireDto> commentaireList;
    private ImageDto image;
}
