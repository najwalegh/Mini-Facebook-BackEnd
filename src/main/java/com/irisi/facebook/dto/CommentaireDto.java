package com.irisi.facebook.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString@Builder
public class CommentaireDto {
    private String id;
    private String contenu;
    private Date datePublication;
    private String userId;
}
