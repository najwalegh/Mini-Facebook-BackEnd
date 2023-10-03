package com.irisi.facebook.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor@NoArgsConstructor@ToString@Builder
public class User {

    @Id
    private String id;
    private String nom;
    private String prenom;

    @Field(name = "date_naissance")
    private Date dateNaissance;

    @Field(name = "adresse_email")
    private String adresseEmail;

    @Field(name = "mot_de_passe")
    private String motDePasse;

    // Référence au profil
    private Profile profile;

    // Liste des commentaires créés par cet utilisateur
    @DBRef
    private List<Commentaire> comments;

    // Liste des postes créés par cet utilisateur
    @DBRef
    private List<Poste> posts;

}
