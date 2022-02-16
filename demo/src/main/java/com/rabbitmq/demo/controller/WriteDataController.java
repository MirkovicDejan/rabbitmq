package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.exception.MyException;
import com.rabbitmq.demo.models.Student;
import com.rabbitmq.demo.service.WriteDataService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class WriteDataController {

    @Autowired
    public WriteDataService writeDataService;

    @PostMapping("/export-csv")
    public String exportToCSV(@RequestBody Student student) throws MyException {
        return writeDataService.exportCSV(student);
    }



}
