package org.example.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PageDTO {
    public int pagina = 1;
    public int elementos = 12;
}
