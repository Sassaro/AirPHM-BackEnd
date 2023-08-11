package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CredentialsDTO {

    public String usuario;
    public String contrasenia;

}
