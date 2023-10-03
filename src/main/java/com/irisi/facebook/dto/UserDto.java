package com.irisi.facebook.dto;

import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.entities.Poste;
import com.irisi.facebook.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private String id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String adresseEmail;
    private String motDePasse;
    private Profile profile;
    private List<Commentaire> comments;
    private List<Poste> posts;
}
