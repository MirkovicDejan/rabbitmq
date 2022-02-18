package com.rabbitmq.demo.service;
import com.rabbitmq.demo.models.Student;
import com.rabbitmq.demo.repository.FileDBRepository;
import com.rabbitmq.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilesService {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final FileDBRepository fileDBRepository;
    public String createFile(String name) {
        try {
            Workbook workbook = new XSSFWorkbook();
            //Set size for columns
            Sheet sheet = workbook.createSheet("student");
            sheet.setColumnWidth(0, 1000);
            sheet.setColumnWidth(1, 3000);
            sheet.setColumnWidth(2, 6000);
            sheet.setColumnWidth(3, 4000);
            sheet.setColumnWidth(4, 3000);
            sheet.setColumnWidth(5, 3000);
            sheet.setColumnWidth(6, 2000);
            sheet.setColumnWidth(7, 10000);
            sheet.setColumnWidth(8, 2000);
            // created  heading
            String[] column = {"ID","USERNAME","EMAIL","PASSWORD","FIRST NAME", "LAST NAME", "YEARS", "STUDIES", "NATIONALITY"};
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue(column[0]);
            headerRow.createCell(1).setCellValue(column[1]);
            headerRow.createCell(2).setCellValue(column[2]);
            headerRow.createCell(3).setCellValue(column[3]);
            headerRow.createCell(4).setCellValue(column[4]);
            headerRow.createCell(5).setCellValue(column[5]);
            headerRow.createCell(6).setCellValue(column[6]);
            headerRow.createCell(7).setCellValue(column[7]);
            headerRow.createCell(8).setCellValue(column[8]);
            //fill data
            List<Student>listForExcel = studentRepository.findAll();
            int rowNum = 1;
            for (Student s : listForExcel) {
              Row rowData = sheet.createRow(rowNum++);
                rowData.createCell(0).setCellValue(s.getStudentId());
                rowData.createCell(1).setCellValue(s.getUsername());
                rowData.createCell(2).setCellValue(s.getEmail());
                rowData.createCell(3).setCellValue(s.getPassword());
                rowData.createCell(4).setCellValue(s.getFirstName());
                rowData.createCell(5).setCellValue(s.getLastName());
                rowData.createCell(6).setCellValue(s.getYears());
                rowData.createCell(7).setCellValue(s.getStudies());
                rowData.createCell(8).setCellValue(s.getNationality());
            }
            File file = new File(name+".xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            workbook.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Create is Finish !";
    }


    }




