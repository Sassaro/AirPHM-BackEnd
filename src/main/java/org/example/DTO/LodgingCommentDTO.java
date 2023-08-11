package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.example.domain.Location;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LodgingCommentDTO {
    Long idComentario;
    String nombreHospedaje;
    String creadorHospedaje;
    String fotoCreadorHospedaje;
    Double calificacion;
    Location ubicacionHospedaje;
    String cuerpoDelComentario;

    public LodgingCommentDTO(Long id, String name, String creator, String imageURL, float avgScore, Location location, String body) {
        this.idComentario = id;
        this.nombreHospedaje = name;
        this.creadorHospedaje = creator;
        this.fotoCreadorHospedaje = imageURL;
        this.calificacion = (double) avgScore;
        this.ubicacionHospedaje = location;
        this.cuerpoDelComentario = body;
    }
}
