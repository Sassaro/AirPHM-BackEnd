package org.example.services;

import org.example.BusinessException;
import org.example.NotFoundException;
import org.example.domain.ClickLog;
import org.example.domain.lodging.Lodging;
import org.example.repositories.ClickLogRepository;
import org.example.repositories.LodgingRepository;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClickLogService {

    @Autowired
    ClickLogRepository clickLogRepository;
    @Autowired
    LodgingRepository lodgingRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void createLog(ClickLog clickLog){
        //Busca los elementos para validar que el log es correcto.
        Lodging lodging = lodgingRepository.findById(clickLog.getLodgingId()).orElseThrow( () -> new NotFoundException("No se ha encontrado el hospedaje con id: " + clickLog.getLodgingId()));

        userRepository.findById(clickLog.getUserId()).orElseThrow( () -> new NotFoundException("El usuario con id: "+ clickLog.getUserId() + "no existe") );

        //le asigna a el nombre al click log.
        clickLog.setLodgingName(lodging.getName());
        clickLogRepository.save(clickLog);
    }
}