package org.example.controllers;

import org.example.domain.ClickLog;
import org.example.services.ClickLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clickLogger")
@CrossOrigin("*")
class LogController {

    @Autowired
    ClickLogService clickLogService;

    @PostMapping("/create")
    void createLog(@RequestBody ClickLog clickLog){

        clickLogService.createLog(clickLog);
    }
}
