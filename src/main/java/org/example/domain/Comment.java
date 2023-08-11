package org.example.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    public
    Long id;
    public String title;
    public String body;
    public int score;
    @ManyToOne(fetch = FetchType.LAZY)
    public
    UserEntity creator;
    String lodgingId;
    public LocalDate date;


    public Comment(
            String title,
            String body,
            int score,
            UserEntity creator,
            String lodgingId,
            LocalDate date
            ){
        this.title = title;
        this.body = body;
        this.score = score;
        this.creator = creator;
        this.lodgingId = lodgingId;
        this.date = date;
    }

    public Comment() {

    }

    public String getLodgingId() {
        return lodgingId;
    }
}
