package org.example.controllers;

import org.example.DTO.*;
import org.example.domain.ClickLog;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/usuarios")
    List<UserDTO> getAllUsers()  {
        return userService.getUser();
    }

    @GetMapping("/usuario/{id}")
    UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/usuario/{id}/reservas-compradas")
    List<BoughtReservationDTO> getUserReservations(@PathVariable Long id) {
        return userService.getUserReservations(id);
    }

    @GetMapping("/usuario/{id}/amigos")
    List<FriendDTO> getUserFriends(@PathVariable Long id){
        return userService.getUserFriends(id);
    }

    @GetMapping("/usuario/{id}/comentarios")
    List<LodgingCommentDTO> getUserComments(@PathVariable Long id) {
        return userService.getUserComments(id);
    }

    @GetMapping("/usuario/{id}/publicaciones")
    List<BasicLodgingDTO> getUserPublications(@PathVariable Long id){
        return userService.getUserPublications(id);
    }

    @DeleteMapping("/usuario/{id}/amigos/eliminar-amigo/{idAmigo}")
    void removeFriend(@PathVariable Long id,@PathVariable Long idAmigo){
        userService.removeUserFriend(id, idAmigo);
    }

    @PostMapping("/login")
    Long loginUser(@RequestBody CredentialsDTO userToLogin) {
        return userService.loginUser(userToLogin);
    }

    @PatchMapping("/actualizarUsuario")
    void updateUser(@RequestBody UserDTO userToUpdate){
        userService.updateUser(userToUpdate);
    }

    @GetMapping("/clicks/{id}/{pagina}")
    Page<ClickLog> getUserClicks(@PathVariable Long id, @PathVariable int pagina) {
        return userService.getUserClicks(id,pagina);
    }
}