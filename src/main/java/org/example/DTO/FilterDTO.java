package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FilterDTO {
    public String destino = "";
    public LocalDate fechaDesde = null;
    public LocalDate fechaHasta = null;
    public int pasajeros = 0;
    public int puntaje = 0;
    public PageDTO paginado;
    PageDTO pageDTO;
}
