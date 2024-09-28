package com.warrioracademy.user.controllers;

import com.warrioracademy.user.dtos.UserDto;
import com.warrioracademy.user.responses.MessageResponse;
import com.warrioracademy.user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Utilisateur", description = "Les APIs des utilisateurs")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Crée un nouvel utilisateur.
     *
     * @param userDto les informations de l'utilisateur
     * @return une réponse avec un message et les données de l'utilisateur créé
     */
    @Tag(name = "Utilisateur", description = "Les APIs des utilisateurs")
    @PostMapping
    public ResponseEntity<MessageResponse> createUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    /**
     * Récupère un utilisateur par son ID.
     *
     * @param id l'identifiant de l'utilisateur
     * @return une réponse avec un message et les données de l'utilisateur
     */
   // @ApiOperation(value = "Get user by ID", response = MessageResponse.class)
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Récupère tous les utilisateurs avec pagination.
     *
     * @param page      le numéro de la page
     * @param size      la taille de la page
     * @param sortBy    le champ de tri
     * @param sortOrder l'ordre de tri (asc ou desc)
     * @return une réponse avec un message et les données paginées des utilisateurs
     */
    @GetMapping
    @Operation(summary = "Obtenir tous les utilisateurs", description = "Retourne la liste de tous les utilisateurs")
    public ResponseEntity<MessageResponse> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        return ResponseEntity.ok(userService.getAllUsers(page, size, sortBy, sortOrder));
    }

    /**
     * Met à jour un utilisateur existant.
     *
     * @param id      l'identifiant de l'utilisateur
     * @param userDto les nouvelles informations de l'utilisateur
     * @return une réponse avec un message et les données de l'utilisateur mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    /**
     * Supprime un utilisateur par son ID.
     *
     * @param id l'identifiant de l'utilisateur
     * @return une réponse sans contenu
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}