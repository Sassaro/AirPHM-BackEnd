package org.example.utils;

import org.example.domain.Reservation;

import java.time.LocalDate;
import java.util.List;

public class OverlappingManager {

    public static boolean canBeReserved(LocalDate startDate, LocalDate endDate , List<Reservation> reservationList) {

        return overlappingDates(startDate,endDate,reservationList);
    }

    static boolean overlappingDates(LocalDate startDate, LocalDate endDate, List<Reservation> reservationList) {

        return reservationList.stream().anyMatch( (it) -> dateComparison(it,startDate,endDate) );
    }

    static boolean dateComparison(Reservation reservation1, LocalDate reservation2StartDate, LocalDate reservation2EndDate) {
        return reservation1.startDate == reservation2StartDate ||            //compara si estan completamente solapadas
                reservation1.endDate == reservation2EndDate ||
                reservation1.startDate.isBefore(reservation2StartDate) && reservation1.endDate.isAfter(reservation2EndDate) ||
                reservation1.startDate.isAfter(reservation2StartDate) && reservation1.endDate.isBefore(reservation2EndDate) ||
                partlyOverlapping(reservation1, reservation2StartDate, reservation2EndDate);
    }

    static boolean partlyOverlapping(Reservation reservation1, LocalDate reservation2StartDate, LocalDate reservation2EndDate) {

        return isInBetween(reservation1.startDate, reservation1.endDate, reservation2StartDate) ||
                isInBetween(reservation1.startDate, reservation1.endDate, reservation2EndDate);

    }

    static boolean isInBetween(LocalDate start, LocalDate end, LocalDate value) {

        return value.isAfter(start) && value.isBefore(end);
    }

}
