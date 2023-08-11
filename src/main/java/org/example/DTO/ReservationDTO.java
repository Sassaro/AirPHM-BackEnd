package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReservationDTO {
    public String idAlojamiento;
    public LocalDate fechaDesde;
    public LocalDate fechaHasta;
    int pasajeros;
    public Long idUsuario;
}
