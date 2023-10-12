package com.irisi.facebook.mappers;

import com.irisi.facebook.dto.ImageDto;
import com.irisi.facebook.entities.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface ImageMapper {
    ImageDto imageToImageDto(Image image);
    Image imageDtoToImage(ImageDto imageDto);
}
