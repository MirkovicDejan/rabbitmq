package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.exception.MyException;
import com.rabbitmq.demo.models.Student;
import com.rabbitmq.demo.service.WriteDataService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class WriteDataController {
    public static final  List<Student> listStudent = Arrays.asList(
            new Student(1,"Dejan","Mirkovic",24,"IT","SRB"),
            new Student(2,"Marko","Milosevic",30,"LAYER","SRB"),
            new Student(3,"Aleksandar","Bosancic",35,"ELECTRICAL AND SOFTWARE ENGINEERING","SRB"),
            new Student(4,"Nikolina","Dubocanin",22,"DOCTOR OD MEDICINE","SRB")
            );


    @Autowired
    public WriteDataService writeDataService;

    @PostMapping("/export-csv")
    public String exportToCSV(@RequestBody Student student) throws MyException {
        return writeDataService.exportCSV(student);
    }

    @PostMapping("/export-excel")
    public void exportToEXCEL(){
        writeDataService.exportEXCEL(listStudent);
    }



}
