package org.example.services;

import org.apache.catalina.User;
import org.example.BusinessException;
import org.example.DTO.*;
import org.example.NotFoundException;
import org.example.Serializers.QueryUserFriends;
import org.example.dataObjects.UserNeo4jDataObject;
import org.example.domain.ClickLog;
import org.example.domain.Comment;
import org.example.domain.Reservation;
import org.example.domain.UserEntity;
import org.example.domain.lodging.Lodging;
import org.example.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserNeo4jRepository userNeo4jRepository;
    @Autowired
    LodgingRepository lodgingRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ClickLogRepository clickLogRepository;

    public List<UserDTO> getUser() {
        return userRepository.findAll().stream().map ( (usuario) -> convertToUserDTO(usuario) ).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {

        UserEntity user = userRepository.findById(id).orElseThrow ( () -> new NotFoundException("No se encontro el usuario con ID: " + id) );

        return convertToUserDTO(user);
    }

    @Transactional
    public Long loginUser(CredentialsDTO loginInfo) {

        UserEntity user = this.userRepository.findByUsernameAndPassword(loginInfo.usuario, loginInfo.contrasenia).orElseThrow ( () -> new BusinessException("El usuario o contraseña son incorrectos") );

        return user.id;
    }

    @Transactional
    public void updateUser(UserDTO userDTO) {

        UserEntity user = userRepository.findById(userDTO.id).get();

        //dado el getByID, el usuario existe en el repo
        user.birthday = userDTO.fechaDeNacimiento;
        user.country = userDTO.paisDeOrigen;
        user.money = userDTO.saldo;

        userRepository.save(user);
    }

    public List<FriendDTO> getUserFriends(Long id) {

        List<QueryUserFriends> friendList = this.userRepository.findUserFriendsByid(id);

        return friendList.stream().map ( friend ->
                new FriendDTO(
                        friend.getFriendId(),
                        friend.getFriendName(),
                        friend.getFriendSurname(),
                        friend.getFriendCountry(),
                        friend.getFriendURLImage()
                )
        ).collect(Collectors.toList());
    }

    public List<BasicLodgingDTO> getUserPublications(Long id) {
        List<Lodging> userLodgings = this.lodgingRepository.findByCreator_IdAndActiveTrue(id);
        return userLodgings.stream().map (hospedaje -> LodgingService.MapToBasicLodgingDTO(hospedaje) ).collect(Collectors.toList());
    }

    @Transactional
    public void removeUserFriend(Long idUser, Long idFriend) {
        UserEntity usuario = this.userRepository.findById(idUser).orElseThrow ( () -> new NotFoundException("No se encontro el usuario con id: " + idUser) );
        UserEntity amigo = this.userRepository.findById(idFriend).orElseThrow ( () -> new NotFoundException("No se encontro el amigo con id: " + idFriend) );
        usuario.removeFriend(amigo);

        this.userNeo4jRepository.deleteFriendship(idUser,idFriend);
        this.userRepository.save(usuario);
    }

    public List<LodgingCommentDTO> getUserComments(Long id) {
        List<Comment> userComment = this.commentRepository.findCommentByCreator_Id(id);

        List<LodgingCommentDTO> lodgingCommentDTO = userComment.stream().map (it -> {
                Lodging lodging = lodgingRepository.findById(it.getLodgingId()).orElseThrow (() -> new NotFoundException("No se ha encontrado el hospedaje de id: " + it.getLodgingId()) );
            return new LodgingCommentDTO(
                    it.id,
                    lodging.getName(),
                    lodging.creator.name + " " + lodging.creator.surname,
                    lodging.creator.imageURL,
                    lodging.avgScore,
                    lodging.location,
                    it.body
            );}
        ).collect(Collectors.toList());
        return lodgingCommentDTO;
    }

    public List<BoughtReservationDTO> getUserReservations(Long id) {

        List<Reservation> userReservations = this.reservationRepository.findReservationByUser_Id(id);

        return userReservations.stream().map ( it -> {

            boolean comentarioDelUsuarioEnElHospedaje = !this.commentRepository.findCommentByLodgingIdAndCreator_Id(it.lodgingId, it.user.id).isEmpty();

            Lodging lodging = this.lodgingRepository.findById(it.lodgingId).orElseThrow (
                    () -> new NotFoundException("No se ha encontrado el hospedaje con id: " + it.lodgingId)
            );

            return new BoughtReservationDTO(
                    it.id,
                    it.lodgingId,
                    lodging.avgScore,
                    lodging.amtScores,
                    lodging.imageURL,
                    lodging.location,
                    lodging.totalPrice(),
                    lodging.getName(),
                    lodging.description,
                    lodging.totalPrice(),
                    comentarioDelUsuarioEnElHospedaje
            );
        }).collect(Collectors.toList());
    }

    public Page<ClickLog> getUserClicks(Long idUser, int pagina) {
        return this.clickLogRepository.findClickLogByIdUsuarioOrderByFechaDesc(idUser, PageRequest.of(pagina,10));
    }

    public static UserNeo4jDataObject convertUsuarioToUsuarioNeo4jDataObject(UserEntity user){

        return new UserNeo4jDataObject(user.id,user.username,
                user.friendList.stream().map( (friend) -> convertUsuarioToUsuarioNeo4jDataObject(friend) ).collect(Collectors.toList()));
    }

    UserDTO convertToUserDTO(UserEntity user) {
        return new UserDTO(
                user.id,
                user.name,
                user.surname,
                user.birthday,
                user.country,
                user.money,
                user.getUserAge(),
                user.imageURL
        );
    }

}
