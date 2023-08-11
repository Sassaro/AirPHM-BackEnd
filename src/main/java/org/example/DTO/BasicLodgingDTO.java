package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.example.domain.Location;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BasicLodgingDTO {
    String id;
    Double promedioPuntaje;
    int cantidadPuntajes;
    String urlImagen;
    Location ubicacion;
    Double costoNoche;
    String nombre;
    String descripcion;
    Long idCreador;

    public BasicLodgingDTO(String id, float avgScore, int amtScores, String imageURL, Location location, Double totalPrice, String name, String description, Long userId) {
        this.id = id;
        this.promedioPuntaje = (double) avgScore;
        this.cantidadPuntajes = amtScores;
        this.urlImagen = imageURL;
        this.ubicacion = location;
        this.costoNoche = totalPrice;
        this.nombre = name;
        this.descripcion = description;
        this.idCreador = userId;
    }
}
