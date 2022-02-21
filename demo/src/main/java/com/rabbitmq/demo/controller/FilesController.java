package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.exception.MyException;
import com.rabbitmq.demo.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


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
    //export without save in Local disc
    @GetMapping("/export-excel")
    public byte[] exportExcel(@RequestParam String name) throws MyException {
        return filesService.exportExcel(name);
    }
    //export csv without save in Local disc
    @GetMapping("/export-csv")
    public void exportCsv(@RequestParam String name, HttpServletResponse response) throws MyException {
         filesService.exportCSV(name,response);
    }

}
