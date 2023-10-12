package com.irisi.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.Binary;

@Data@AllArgsConstructor@NoArgsConstructor
public class ImageDto {
    private String id;
    private String title;
    private Binary image;
    //envoyer le id a partir du font
    private String postId;
}
