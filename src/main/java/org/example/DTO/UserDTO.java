package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDTO {
    public Long id;
    String nombre;
    String apellido;
    public LocalDate fechaDeNacimiento;
    public String paisDeOrigen;
    public Double saldo;
    int edad;
    String fotoURL;

    public UserDTO(Long id, String name, String surname, LocalDate birthday, String country, Double money, int userAge, String imageURL) {
        this.id = id;
        this.nombre = name;
        this.apellido = surname;
        this.fechaDeNacimiento = birthday;
        this.paisDeOrigen = country;
        this.saldo = money;
        this.edad = userAge;
        this.fotoURL = imageURL;
    }
}
