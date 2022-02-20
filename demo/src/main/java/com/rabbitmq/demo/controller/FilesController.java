package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.exception.MyException;
import com.rabbitmq.demo.models.FileDB;
import com.rabbitmq.demo.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class FilesController {
    @Autowired
    private final FilesService filesService;

    @GetMapping("/create-and-export-excel")
     public byte[] createAndExportExcelFile (@RequestParam String name) throws MyException {
     return filesService.createAndExportExcel(name);
    }
    @GetMapping("/read-excel-from-db-export")
    public byte[] readExcelFileFromDBAndExport(@RequestParam String name) throws MyException{
        return filesService.readExcelFromDBAndExport(name);
    }
    @GetMapping("/create-and-export-csv")
    public byte[] createAndExportCSVFile(@RequestParam String name) throws MyException {
        return filesService.createAndExportCSV(name);
    }
    @GetMapping("/read-csv-from-db")
    public byte[] readCSVFileFromDB(@RequestParam String name) throws MyException {
        return filesService.readCSVFromDB(name);
    }
}
