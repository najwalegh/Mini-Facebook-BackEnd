package com.irisi.facebook;

import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.services.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@Slf4j
public class FacebookApplication  {


    public static void main(String[] args) {
        SpringApplication.run(FacebookApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserImpl user ){
        return args ->{
            UserDto userDto=new UserDto();
            userDto.setNom("soumia");
            userDto.setPrenom("soumia");
            userDto.setAdresseEmail("soumia@test");
            userDto.setDateNaissance(new Date());
            userDto.setMotDePasse("123.com");
            log.info("User : "+user.saveUser(userDto));
//            log.info("User : "+user.allUsers());
//            log.info("User : "+user.getUser("651bca217ef049489f5083d4"));


        };
    }
}
