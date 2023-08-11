package org.example.controllers;

import org.example.DTO.CreateCommentDTO;
import org.example.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
class CommentController {

    @Autowired
    CommentService commentService;

    @DeleteMapping("/comentario/borrar/{id}")
    void removeComment(@PathVariable Long id){
        commentService.removeUserComment(id);
    }

    @PostMapping("/comentario/usuario/{id}/crear-comentario/{idHospedaje}")
    void createComment(@PathVariable Long id, @PathVariable String idHospedaje, @RequestBody CreateCommentDTO comentario ){
        commentService.addComment(id, idHospedaje, comentario);
    }

}