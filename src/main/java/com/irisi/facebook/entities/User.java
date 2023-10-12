package com.irisi.facebook.entities;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor@NoArgsConstructor@ToString
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

    // Liste des commentaires créés par cet utilisateur
    @DBRef
    private List<Commentaire> comments ;

    // Liste des postes créés par cet utilisateur
    @DBRef
    private List<Poste> posts;

    // Référence au profil
    @DBRef
    private Profile profile;

}
