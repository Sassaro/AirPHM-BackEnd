package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FriendDTO {
    Long id;
    String nombre;
    String apellido;
    String paisDeOrigen;
    String fotoURL;

    public FriendDTO(Long friendId, String friendName, String friendSurname, String friendCountry, String friendURLImage) {
        this.id = friendId;
        this.nombre = friendName;
        this.apellido = friendSurname;
        this.paisDeOrigen = friendCountry;
        this.fotoURL = friendURLImage;
    }
}
