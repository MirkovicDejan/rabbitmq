package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class FilesController {
    @Autowired
    private final FilesService filesService;

     @PostMapping("/create-excel")
     public String createFile (@RequestParam String name){
     return filesService.createFile(name);
    }

}
