package org.example.domain;

import org.example.BusinessException;
import org.example.DTO.CompleteLodgingDTO;
import org.example.domain.lodging.Apartment;
import org.example.domain.lodging.Cabin;
import org.example.domain.lodging.House;
import org.example.domain.lodging.Lodging;

public class LodgingFactory {

    CompleteLodgingDTO lodgingDTO;
    UserEntity user;

    public void setLodgingDTO(CompleteLodgingDTO lodgingDTO) {
        this.lodgingDTO = lodgingDTO;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Lodging create() throws Exception {

        if(user == null || lodgingDTO == null) throw new Exception("Error en la creación del hospedaje");

        switch (lodgingDTO.tipo.toLowerCase()){

            case "cabania":
                return new Cabin(
                        lodgingDTO.nombre,
                        lodgingDTO.descripcion,
                        lodgingDTO.cantidadHuespedes,
                        lodgingDTO.cantidadDormitorios,
                        lodgingDTO.cantidadBanios,
                        lodgingDTO.alojamientoDescripcion,
                        lodgingDTO.comodidades,
                        lodgingDTO.tieneServiciosLimpieza,
                        lodgingDTO.costoNoche.floatValue(),
                        lodgingDTO.ubicacion,
                        user,
                        lodgingDTO.urlImagen);
            case "departamento":
                return new Apartment(
                        lodgingDTO.nombre,
                        lodgingDTO.descripcion,
                        lodgingDTO.cantidadHuespedes,
                        lodgingDTO.cantidadDormitorios,
                        lodgingDTO.cantidadBanios,
                        lodgingDTO.alojamientoDescripcion,
                        lodgingDTO.comodidades,
                        lodgingDTO.tieneServiciosLimpieza,
                        lodgingDTO.costoNoche.floatValue(),
                        lodgingDTO.ubicacion,
                        user,
                        lodgingDTO.urlImagen);
            case "casa":
                return new House(
                        lodgingDTO.nombre,
                        lodgingDTO.descripcion,
                        lodgingDTO.cantidadHuespedes,
                        lodgingDTO.cantidadDormitorios,
                        lodgingDTO.cantidadBanios,
                        lodgingDTO.alojamientoDescripcion,
                        lodgingDTO.comodidades,
                        lodgingDTO.tieneServiciosLimpieza,
                        lodgingDTO.costoNoche.floatValue(),
                        lodgingDTO.ubicacion,
                        user,
                        lodgingDTO.urlImagen);
        }

        throw new BusinessException("Tipo de hospedaje inexistente");
    }
}
