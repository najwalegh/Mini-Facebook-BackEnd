package com.irisi.facebook;

import com.irisi.facebook.mappers.CommentaireMapper;
import com.irisi.facebook.mappers.PosteMapper;
import com.irisi.facebook.mappers.ProfileMapper;
import com.irisi.facebook.mappers.UserMapper;
import org.mapstruct.ap.internal.model.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public CommentaireMapper commentaireMapper(){return Mappers.getMapper(CommentaireMapper.class);
    }

    @Bean
    public ProfileMapper profileMapper(){return Mappers.getMapper(ProfileMapper.class);
    }

    @Bean
    public PosteMapper posteMapper(){return Mappers.getMapper(PosteMapper.class);
    }
}