package com.warrioracademy.user.services.implementations;

import com.warrioracademy.user.responses.MessageResponse;
import com.warrioracademy.user.dtos.UserDto;
import com.warrioracademy.user.entities.User;
import com.warrioracademy.user.exceptions.ResourceNotFoundException;
import com.warrioracademy.user.mappers.UserMapper;
import com.warrioracademy.user.reporisoties.UserRepository;
import com.warrioracademy.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public MessageResponse createUser(UserDto userDto) {
        logger.info("Creating user with email: {}", userDto.getEmail());
        // Mapper le DTO vers l'entité User
        User user = userMapper.toEntity(userDto);
        // Sauvegarder l'utilisateur en base de données
        User savedUser = userRepository.save(user);
        user.setPassword("0000");
        return new MessageResponse("Utilisateur créé avec succès", userMapper.toDto(savedUser));
    }

    @Override
    public MessageResponse getUserById(Long id) {
        logger.info("Fetching user with ID: {}", id);
        // Rechercher l'utilisateur par son ID
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec ID: " + id));
        return new MessageResponse("Utilisateur récupéré", userMapper.toDto(user));
    }

    @Override
    public MessageResponse updateUser(Long id, UserDto userDto) {
        logger.info("Updating user with ID: {}", id);
        // Vérifier si l'utilisateur existe
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec ID: " + id));

        // Mettre à jour les informations de l'utilisateur
        User updatedUser = userMapper.toEntity(userDto);
        updatedUser.setId(existingUser.getId());

        User savedUser = userRepository.save(updatedUser);
        return new MessageResponse("Utilisateur mis à jour avec succès", userMapper.toDto(savedUser));
    }

    @Override
    public MessageResponse deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);
        // Vérifier si l'utilisateur existe
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        // Supprimer l'utilisateur
        userRepository.delete(existingUser);
        return new MessageResponse("Utilisateur supprimé avec succès");
    }

    @Override
    public MessageResponse getAllUsers(int page, int size, String sortBy, String sortOrder) {
        // Gérer la pagination et le tri
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> userPage = userRepository.findAll(pageable);

        if (userPage.isEmpty()) {
            throw new ResourceNotFoundException("Aucun utilisateur trouvé.");
        }

        return new MessageResponse("Liste des utilisateurs récupérée", userPage.map(userMapper::toDto));
    }
}