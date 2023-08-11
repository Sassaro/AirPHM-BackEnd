package org.example.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(collection = "clickLog")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClickLog {
    @Id
    String id;
    LocalDateTime fecha;
    Long idUsuario;
    String idHospedaje;
    String nombreHospedaje;

    public String getLodgingId() {
        return idHospedaje;
    }

    public Long getUserId(){
        return idUsuario;
    }

    public void setLodgingName(String lodgingName) {
        this.nombreHospedaje = lodgingName;
    }
}