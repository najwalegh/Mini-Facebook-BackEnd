package com.irisi.facebook.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    private String id;

    private String title;

    private Binary image;

    private String postId;
}