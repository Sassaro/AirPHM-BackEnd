package org.example.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.DTO.*;
import org.example.NotFoundException;
import org.example.domain.Comment;
import org.example.domain.LodgingFactory;
import org.example.domain.LodgingSearch;
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
public class LodgingService {

    @Autowired
    LodgingRepository lodgingRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserNeo4jRepository userNeo4jRepository;

    @Autowired
    ReservationRepository reservationRepository;

    public Page<BasicLodgingDTO> getFriendLodgings(Long userId, PageDTO pageDTO) {
        List<String> idsHospedajesAmigos = userNeo4jRepository.friendsLodgings(userId);
        Page<Lodging> hospedajesAmigos = lodgingRepository.findLodgingByIdInAndActiveTrue(idsHospedajesAmigos, PageRequest.of(pageDTO.pagina,pageDTO.elementos));

        return hospedajesAmigos.map(lodging -> MapToBasicLodgingDTO(lodging));
    }

    String convertFilterToRedisKey(FilterDTO filter){
        return filter.puntaje + ',' + filter.destino + ',' + filter.fechaDesde + ',' + filter.fechaHasta + ',' + filter.pasajeros + ',' + filter.paginado.pagina + ',' + filter.paginado.elementos;
    }

    public Page<BasicLodgingDTO> findLodgingsByFilter(FilterDTO filter) {

        List<String> lodgingsWithReservation = reservationRepository.findReservationByOverlapping(filter.fechaDesde,filter.fechaHasta).stream().map(it-> it.lodgingId).collect(Collectors.toList());

        Page<Lodging> posibleLodgings = lodgingRepository.findLodgingByLocation_CountryLikeAndCapacityGreaterThanEqualAndAvgScoreGreaterThanEqualAndIdNotInAndActiveTrue(filter.destino,filter.pasajeros,filter.puntaje,lodgingsWithReservation,PageRequest.of(filter.paginado.pagina,filter.paginado.elementos));

        Page<BasicLodgingDTO> hospedajesDto = posibleLodgings.map ( it-> MapToBasicLodgingDTO(it) );

        return hospedajesDto;

    }

    public CompleteLodgingDTO obtenerDetalleHospedaje(String id) {

        Lodging lodging = lodgingRepository.findById(id).orElseThrow ( () -> new NotFoundException("No se encontró el hospedaje de id: " + id ) );

        List<Comment> comentariosDelHospedaje = commentRepository.findCommentByLodgingId(lodging.id);

        List<Long> userWithReservations = reservationRepository.findReservationByLodgingId(lodging.id).stream().map (it -> it.user.id ).collect(Collectors.toList());
        return MapToHospedajeDetalleDTO(lodging,comentariosDelHospedaje, userWithReservations);
    }

    @Transactional
    public void create(Long userId, CompleteLodgingDTO lodgingDTO) throws Exception {
        Lodging lodging = MapToEntity(userId, lodgingDTO);

        this.lodgingRepository.save(lodging);
    }

    @Transactional
    public void delete(String id) {
        Lodging lodging =  lodgingRepository.findById(id).orElseThrow (() -> new NotFoundException("No se ha encontrado el hospedaje con id: " + id) );
        lodging.active = false;

        this.lodgingRepository.save(lodging);
    }

    public static BasicLodgingDTO MapToBasicLodgingDTO(Lodging lodging)  {
        return new BasicLodgingDTO(
                lodging.id,
                lodging.avgScore,
                lodging.amtScores,
                lodging.imageURL,
                lodging.location,
                lodging.totalPrice(),
                lodging.getName(),
                lodging.description,
                lodging.creator.id
        );
    }
    public static CompleteLodgingDTO MapToHospedajeDetalleDTO(Lodging lodging,List<Comment> comments,List<Long> userWithReservations)  {

        List<CommentDTO> comentariosDto = comments.stream().map ( it -> MapToCommentDTO(it) ).collect(Collectors.toList());

        return new CompleteLodgingDTO(
                lodging.id,
                lodging.avgScore,
                lodging.amtScores,
                lodging.imageURL,
                lodging.location,
                lodging.totalPrice(),
                lodging.getName(),
                lodging.description,
                lodging.detail,
                lodging.capacity,
                lodging.cleanService,
                lodging.rooms,
                lodging.bathrooms,
                comentariosDto,
                lodging.tags,
                userWithReservations,
                lodging.type,
                lodging.active,
                lodging.creator.id
        );
    }

    static CommentDTO MapToCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.id,
                comment.creator.id,
                comment.creator.name,
                comment.creator.surname,
                comment.creator.imageURL,
                comment.score,
                comment.date,
                comment.body,
                comment.title
        );
    }

    private Lodging MapToEntity(Long userId, CompleteLodgingDTO lodgingDTO) throws Exception {
        UserEntity user = this.userRepository.findById(userId).orElseThrow( () -> new NotFoundException("No se encontró el usuario de id: " + userId) );

        LodgingFactory factory = new LodgingFactory();
        factory.setUser(user);
        factory.setLodgingDTO(lodgingDTO);
        return factory.create();
    }
}
