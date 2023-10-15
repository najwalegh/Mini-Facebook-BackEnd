package com.irisi.facebook;

import com.irisi.facebook.mappers.*;
import com.mongodb.client.MongoClient;
import org.mapstruct.ap.internal.model.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


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

    @Bean
    public ImageMapper imageMapper(){return Mappers.getMapper(ImageMapper.class);
    }
//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        return new MongoTemplate(new MongoClient("localhost"), "mini-facebook");
//    }
}