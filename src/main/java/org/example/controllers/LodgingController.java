package org.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.DTO.BasicLodgingDTO;
import org.example.DTO.CompleteLodgingDTO;
import org.example.DTO.FilterDTO;
import org.example.DTO.PageDTO;
import org.example.services.LodgingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospedajes")
@CrossOrigin(origins = "*")
class LodgingController {

    @Autowired
    LodgingService lodgingService;

    @PostMapping
    Page<BasicLodgingDTO> GetHospedajes(@RequestBody FilterDTO filter) throws JsonProcessingException {
        return lodgingService.findLodgingsByFilter(filter);
    }

    @PostMapping("/crear/{idUsuario}")
    void Post(@PathVariable Long idUsuario, @RequestBody CompleteLodgingDTO lodging) throws Exception {
        lodgingService.create(idUsuario, lodging);
    }

    @DeleteMapping("/eliminar/{id}")
    void Delete(@PathVariable  String id ) {
        lodgingService.delete(id);
    }

    @GetMapping("/{id}")
    CompleteLodgingDTO GetHospedaje(@PathVariable String id) {
        return lodgingService.obtenerDetalleHospedaje(id);
    }

    @PostMapping("/de-amigos/{idUsuario}")
    Page<BasicLodgingDTO> GetHospedajesAmigos(@PathVariable Long idUsuario , @RequestBody PageDTO paginado) {
        return lodgingService.getFriendLodgings(idUsuario,paginado);
    }

}
