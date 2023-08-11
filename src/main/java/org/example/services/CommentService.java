package org.example.services;

import org.example.BusinessException;
import org.example.DTO.CreateCommentDTO;
import org.example.NotFoundException;
import org.example.domain.Comment;
import org.example.domain.UserEntity;
import org.example.domain.lodging.Lodging;
import org.example.repositories.CommentRepository;
import org.example.repositories.LodgingRepository;
import org.example.repositories.ReservationRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository usuarioRepository;

    @Autowired
    LodgingRepository lodgingRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Transactional
    public void removeUserComment(Long commentId) {

        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("No se encontro el comentario con id " + commentId));
        this.commentRepository.deleteById(commentId);
        updateLodgingScore(comment.getLodgingId());
    }

    @Transactional
    public void addComment(Long idUser , String idLodging, CreateCommentDTO commentDTO) {

        boolean userComment = this.commentRepository.findCommentByLodgingIdAndCreator_Id(idLodging,idUser).isEmpty();
        boolean userReservations = !this.reservationRepository.findReservationByLodgingIdAndUser_Id(idLodging,idUser).isEmpty();

        if(userComment){
            if(userReservations){

                UserEntity usuario = this.usuarioRepository.findById(idUser).get();
                Comment comentario = new Comment(commentDTO.getTitle(),commentDTO.getComment(), commentDTO.getScore(), usuario,idLodging, LocalDate.now());
                this.commentRepository.save(comentario);

                updateLodgingScore(idLodging);

            }else{
                throw new BusinessException("No se puede comentar un hospedaje no reservado");
            }
        }else{
            throw new BusinessException("No se puede comentario un hospedaje ya comentado");
        }
    }

    @Transactional
    public void updateLodgingScore(String idLodging){

        Lodging lodging = lodgingRepository.findById(idLodging).orElseThrow ( () -> new NotFoundException("No se ha encontrado el hospedaje con Id: " + idLodging));

        /*
        Este try catch esta porque si el resultado de una query es vacio, devuelve un error
        */

        Optional<Float> score = commentRepository.findAverageScoreById(idLodging);

        if(score.isPresent()){
            lodging.setAvgScore(score.get());

        }else {
            lodging.setAvgScore(0.0F);
        }

        this.lodgingRepository.save(lodging);
    }
}
