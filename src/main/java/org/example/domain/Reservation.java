package org.example.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.example.domain.lodging.Lodging;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public
    Long id;
    public LocalDate startDate;
    public LocalDate endDate;
    @ManyToOne
    public
    UserEntity user;
    public String lodgingId;

    public Reservation(
            LocalDate startDate,
            LocalDate endDate,
            UserEntity user,
            String lodgingId){
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.lodgingId = lodgingId;
    }

    public Reservation() {
    }
}
