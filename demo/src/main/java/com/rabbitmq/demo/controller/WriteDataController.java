package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.models.Student;
import com.rabbitmq.demo.service.WriteDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.util.List;


@RestController
public class WriteDataController {
    @Autowired
    public WriteDataService writeDataService;

   @GetMapping("/export-excel")
    public FileInputStream FileOutputStream (){
     return writeDataService.exportEXCEL();
    }

    @GetMapping("/add-to-list")
    public List<Student> addToList(@RequestBody Student student){
     return     writeDataService.addToList(student);
    }



}
