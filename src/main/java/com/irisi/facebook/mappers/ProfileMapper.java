package com.irisi.facebook.mappers;

import com.irisi.facebook.dto.ProfileDto;
import com.irisi.facebook.entities.Profile;
import com.irisi.facebook.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDto profileToProfileDto(Profile profile);
    Profile profileDtoToProfile(ProfileDto profileDto);
}
