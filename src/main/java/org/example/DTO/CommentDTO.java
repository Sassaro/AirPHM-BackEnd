package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CommentDTO {
    Long idComentario;
    Long idCreador;
    String nombreUsuario;
    String apellidoUsuario;
    String fotoURL;
    int puntaje;
    LocalDate fechaComentario;
    String comentario;
    String tituloComentario;

    public CommentDTO(Long id, Long idUser, String name, String surname, String imageURL, int score, LocalDate date, String body, String title) {
        this.idComentario = id;
        this.idCreador = idUser;
        this.nombreUsuario = name;
        this.apellidoUsuario = surname;
        this.fotoURL = imageURL;
        this.puntaje = score;
        this.fechaComentario = date;
        this.comentario = body;
        this.tituloComentario = title;
    }
}
