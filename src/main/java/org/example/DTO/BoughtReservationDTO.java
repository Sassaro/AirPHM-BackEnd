package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.example.domain.Location;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BoughtReservationDTO {
    Long reservaId;
    String id;
    Double promedioPuntaje;
    int cantidadPuntajes;
    String urlImagen;
    Location ubicacion;
    Double costoNoche;
    String nombre;
    String descripcion;
    Double costoTotal;
    Boolean calificado;

    public BoughtReservationDTO(Long id, String lodgingId, float avgScore, int amtScores, String imageURL, Location location, Double totalPrice, String name, String description, Double totalPrice1, boolean commented) {
    this.reservaId = id;
    this.id = lodgingId;
    this.promedioPuntaje = (double) avgScore;
    this.cantidadPuntajes = amtScores;
    this.urlImagen = imageURL;
    this.ubicacion = location;
    this.costoNoche = totalPrice;
    this.nombre = name;
    this.descripcion = description;
    this.costoTotal = totalPrice1;
    this.calificado = commented;
    }
}
