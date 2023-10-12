package com.irisi.facebook.services.interfaces;

import com.irisi.facebook.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String addPhoto(String title, MultipartFile file, String postId) throws IOException;

    Image getPhoto(String id);
}