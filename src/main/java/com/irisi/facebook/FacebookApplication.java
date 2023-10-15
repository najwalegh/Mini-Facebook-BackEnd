package com.irisi.facebook;

import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.repositories.UserRepository;
import com.irisi.facebook.services.UserImpl;
import org.bson.types.ObjectId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@Slf4j
public class FacebookApplication  {


    public static void main(String[] args) {
        SpringApplication.run(FacebookApplication.class, args);
    }

//        @Bean
//        CommandLineRunner start(UserImpl user ){
//        return args ->{
////            UserDto userDto=new UserDto();
////            userDto.setNom("najwa");
////            userDto.setPrenom("NAJWA");
////            userDto.setAdresseEmail("najwa@test");
////            userDto.setDateNaissance(new Date());
////            userDto.setMotDePasse("123.com");
////            log.info("User : "+user.saveUser(userDto));
////            log.info("User : "+user.allUsers());
////            log.info("User : "+user.getUser("651bca217ef049489f5083d4"));
////            user.deleteUser("651bd1366d9a2b5330eae4de");
//
//            UserDto userDto = new UserDto();
//            userDto.setId("123");  // Remplacez "123" par l'ID spécifique que vous souhaitez utiliser
//            userDto.setNom("John");
//            userDto.setPrenom("Doe");
//            userDto.setAdresseEmail("john.doe@example.com");
//
//            // Appel de la méthode pour mettre à jour l'utilisateur
////            log.info("Updated User : " + user.updateUser(userDto));
//        };
//    }
}
