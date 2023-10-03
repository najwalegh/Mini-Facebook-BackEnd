package com.irisi.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
