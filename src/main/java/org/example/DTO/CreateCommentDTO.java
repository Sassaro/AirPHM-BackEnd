package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreateCommentDTO {
    int puntaje;
    String comentario;
    String tituloComentario;

    public String getComment() {
        return comentario;
    }

    public int getScore() {
        return puntaje;
    }

    public String getTitle() {
        return tituloComentario;
    }
}
