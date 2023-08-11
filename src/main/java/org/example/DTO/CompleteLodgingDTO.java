package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.example.domain.Location;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CompleteLodgingDTO {
    String id;
    Double promedioPuntaje;
    int cantidadPuntajes;
    public String urlImagen;
    public Location ubicacion;
    public Double costoNoche;
    public String nombre;
    public String descripcion;
    public String alojamientoDescripcion;
    public int cantidadHuespedes;
    public Boolean tieneServiciosLimpieza;
    public int cantidadDormitorios;
    public int cantidadBanios;
    List<CommentDTO> comentarios;
    public List<String> comodidades;
    List<Long> usuariosQueReservaron;
    public String tipo;
    Boolean active;
    Long idCreador;

    public CompleteLodgingDTO(
            String id,
            float avgScore,
            int amtScores,
            String imageURL,
            Location location,
            Double totalPrice,
            String name,
            String description,
            String detail,
            int capacity,
            boolean cleanService,
            int rooms,
            int bathrooms,
            List<CommentDTO> comentariosDto,
            List<String> tags,
            List<Long> userWithReservations,
            String type,
            boolean active,
            Long idUser) {
        this.id = id;
        this.promedioPuntaje = (double) avgScore;
        this.cantidadPuntajes = amtScores;
        this.urlImagen = imageURL;
        this.ubicacion = location;
        this.costoNoche = totalPrice;
        this.nombre = name;
        this.descripcion = description;
        this.alojamientoDescripcion = detail;
        this.cantidadHuespedes = capacity;
        this.tieneServiciosLimpieza = cleanService;
        this.cantidadHuespedes = rooms;
        this.cantidadBanios = bathrooms;
        this.comentarios = comentariosDto;
        this.comodidades = tags;
        this.usuariosQueReservaron = userWithReservations;
        this.tipo = type;
        this.active = active;
        this.idCreador = idUser;
    }
}
