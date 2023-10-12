package com.irisi.facebook.services;

import com.irisi.facebook.dto.CommentaireDto;
import com.irisi.facebook.dto.UserDto;
import com.irisi.facebook.entities.Commentaire;
import com.irisi.facebook.entities.Poste;
import com.irisi.facebook.entities.Profile;
import com.irisi.facebook.entities.User;
import com.irisi.facebook.mappers.CommentaireMapper;
import com.irisi.facebook.mappers.PosteMapper;
import com.irisi.facebook.mappers.ProfileMapper;
import com.irisi.facebook.mappers.UserMapper;
import com.irisi.facebook.repositories.UserRepository;
import com.irisi.facebook.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PosteMapper posteMapper;
    private CommentaireMapper commentaireMapper;
    private ProfileMapper profileMapper;

    @Autowired
    private CommentaireImpl commentaireService;

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
            if (userDto.getMotDePasse() != null) {
                existingUser.setMotDePasse(userDto.getMotDePasse());
            }
            if (userDto.getProfile() != null) {
                Profile profile = profileMapper.profileDtoToProfile(userDto.getProfile());
                existingUser.setProfile(profile);
            }
            if (userDto.getPosts() != null) {
                List<Poste> posts = userDto.getPosts().stream()
                        .map(posteDto -> posteMapper.posteDtoToPoste(posteDto))
                        .collect(Collectors.toList());
                existingUser.setPosts(posts);
            }
            if (userDto.getComments() != null) {
                List<Commentaire> commentaires = userDto.getComments().stream()
                        .map(commentaireDto -> commentaireMapper.commentaireDtoToCommentaire(commentaireDto))
                        .collect(Collectors.toList());
                existingUser.setComments(commentaires);
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

    @Override
    public UserDto getUserById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return userMapper.userToUserDto(user);
        } else {
            return null; // Ou vous pouvez lever une exception utilisateur non trouvé
        }
    }

    @Override
    public void createCommentaireForUser(UserDto userDto, CommentaireDto commentaireDto) {
        Commentaire commentaire = new Commentaire();
        commentaire.setContenu(commentaireDto.getContenu());
        commentaire.setDatePublication(commentaireDto.getDatePublication());
        commentaire.setUserId(userDto.getId());
        commentaire.setPosteId(commentaireDto.getPosteId());

        // Enregistrez le commentaire dans la base de données
        commentaireService.saveCommentaire(commentaire);

        // Ajoutez le commentaire à la liste des commentaires de l'utilisateur
        userDto.getComments().add(commentaireDto);
    }
}
