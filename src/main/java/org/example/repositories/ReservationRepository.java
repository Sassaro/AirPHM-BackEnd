package org.example.repositories;

import org.example.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation,Long> {

    List<Reservation> findReservationByLodgingId(String lodgingId);

    List<Reservation> findReservationByUser_Id(Long user_id);

    List<Reservation> findReservationByLodgingIdAndUser_Id(String lodgingId, Long user_id);

    @Query(""" 
        SELECT r FROM Reservation r WHERE 
        r.startDate = :startDate OR r.endDate = :endDate OR
        :startDate BETWEEN r.startDate AND r.endDate OR 
        :endDate BETWEEN r.startDate AND r.endDate OR 
        r.startDate BETWEEN :startDate AND :endDate OR 
        r.endDate BETWEEN :startDate AND :endDate
    """)
    List<Reservation> findReservationByOverlapping(LocalDate startDate,LocalDate endDate);
}
