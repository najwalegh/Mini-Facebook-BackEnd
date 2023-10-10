package com.irisi.facebook.services;

import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.mappers.UserMapper;
import com.irisi.facebook.repositories.UserRepository;
import com.irisi.facebook.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public UserDto getUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(userMapper::userToUserDto).orElse(null);
    }

    @Override
    public UserDto updateUser(String userId,UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Mettez à jour les champs en fonction de userDto
            if (userDto.getNom() != null) {
                existingUser.setNom(userDto.getNom());
            }
            if (userDto.getPrenom() != null) {
                existingUser.setPrenom(userDto.getPrenom());
            }
            if (userDto.getAdresseEmail() != null) {
                existingUser.setAdresseEmail(userDto.getAdresseEmail());
            }
            if (userDto.getDateNaissance() != null) {
                existingUser.setDateNaissance(userDto.getDateNaissance());
            }
            if (userDto.getPosts() != null) {
                existingUser.setPosts(userDto.getPosts());
            }
            if (userDto.getMotDePasse() != null) {
                existingUser.setMotDePasse(userDto.getMotDePasse());
            }
            if (userDto.getProfile() != null) {
                existingUser.setProfile(userDto.getProfile());
            }
            if (userDto.getComments() != null) {
                existingUser.setComments(userDto.getComments());
            }
            // Enregistrez les modifications dans la base de données
            User updatedUser = userRepository.save(existingUser);

            return userMapper.userToUserDto(updatedUser);
        }
        return null;
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> allUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }
}
