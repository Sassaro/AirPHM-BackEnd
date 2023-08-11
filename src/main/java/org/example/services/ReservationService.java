package org.example.services;

import org.example.BusinessException;
import org.example.DTO.ReservationDTO;
import org.example.NotFoundException;
import org.example.dataObjects.ReservationNeo4jDataObject;
import org.example.domain.Reservation;
import org.example.domain.UserEntity;
import org.example.domain.lodging.Lodging;
import org.example.repositories.LodgingRepository;
import org.example.repositories.ReservationNeo4jRepository;
import org.example.repositories.ReservationRepository;
import org.example.repositories.UserRepository;
import org.example.utils.OverlappingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationNeo4jRepository reservationNeo4jRepository;

    @Autowired
    LodgingRepository lodgingRepository;

    @Transactional
    public void reserveLodging(ReservationDTO reservationDTO) {

        if(reservationDTO.fechaDesde.isAfter(reservationDTO.fechaHasta)){
            throw new BusinessException("Las fechas son incorrectas");
        }

        List<Reservation> reservasDelHospedaje = this.reservationRepository.findReservationByLodgingId(reservationDTO.idAlojamiento);

        if(!OverlappingManager.canBeReserved(reservationDTO.fechaDesde,reservationDTO.fechaHasta,reservasDelHospedaje)){

            UserEntity user = this.userRepository.findById(reservationDTO.idUsuario).orElseThrow( () -> new NotFoundException("No se ha encontrado el usuario con id: " + reservationDTO.idUsuario) );
            Lodging lodging = this.lodgingRepository.findById(reservationDTO.idAlojamiento).orElseThrow( () -> new NotFoundException("No se ha encontrado el hospedaje con id: " + reservationDTO.idAlojamiento) );

            user.payLodging(lodging, reservationDTO.fechaDesde,reservationDTO.fechaHasta);

            Reservation reservation = new Reservation(reservationDTO.fechaDesde,reservationDTO.fechaHasta,user,reservationDTO.idAlojamiento);

            //guarda la reserva en postgres
            this.reservationRepository.save(reservation);

            //guarda la reserva en neo4j
            this.reservationNeo4jRepository.save(convertReservaToReservaNeo4jDataObject(reservation));

        }else{
            throw new BusinessException("No se puede reservar el alojamiento, ya esta reservado");
        }
    }

    public static ReservationNeo4jDataObject convertReservaToReservaNeo4jDataObject(Reservation reservation) {

        return new ReservationNeo4jDataObject(reservation.id,reservation.startDate,reservation.endDate,
                UserService.convertUsuarioToUsuarioNeo4jDataObject( reservation.user),reservation.lodgingId);
    }

}
