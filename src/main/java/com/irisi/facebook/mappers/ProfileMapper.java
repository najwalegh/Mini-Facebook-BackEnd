package com.irisi.facebook.mappers;

import com.irisi.facebook.dto.ProfileDto;
import com.irisi.facebook.entities.Profile;
import com.irisi.facebook.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    default String map(User user) {
        return user != null ? user.getId() : null;
    }

    @Named("mapToUser")
    default User mapToUser(String userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "citation", target = "citation")
    @Mapping(source = "user", target = "userId")
    ProfileDto profileToProfileDto(Profile profile);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "citation", target = "citation")
    @Mapping(source = "userId", target = "user",qualifiedByName = "mapToUser")
    Profile profileDtoToProfile(ProfileDto profileDto);
}
