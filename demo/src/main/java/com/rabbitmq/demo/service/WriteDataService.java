package com.rabbitmq.demo.service;

import com.rabbitmq.demo.exception.MyException;
import com.rabbitmq.demo.models.Student;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WriteDataService {


    public String exportCSV(Student student) throws MyException {
        if (student.getId() != null && !student.getFirstName().equals(null) && !student.getLastName().equals(null) && !student.getNationality().equals(null) && student.getYears() != null) {
            try {
                File file = new File("student.csv");
                FileWriter fw = new FileWriter(file, true);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(student);
                pw.close();
                return "Export is finish !";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new MyException("Object is empty !");
    }

    public void exportEXCEL(List<Student> listStudent) {
        try {
            Workbook workbook = new XSSFWorkbook();
            //Set size for columns
            Sheet sheet = workbook.createSheet("student");
            sheet.setColumnWidth(0, 1000);
            sheet.setColumnWidth(1, 3500);
            sheet.setColumnWidth(2, 3500);
            sheet.setColumnWidth(3, 2000);
            sheet.setColumnWidth(4, 10000);
            sheet.setColumnWidth(5, 3000);
            // created  heading
            String[] column = {"ID", "FIRST NAME", "LAST NAME", "YEARS", "STUDIES", "NATIONALITY"};
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue(column[0]);
            headerRow.createCell(1).setCellValue(column[1]);
            headerRow.createCell(2).setCellValue(column[2]);
            headerRow.createCell(3).setCellValue(column[3]);
            headerRow.createCell(4).setCellValue(column[4]);
            headerRow.createCell(5).setCellValue(column[5]);
            //fill data
            int rowNum = 1;
            for (Student s : listStudent) {
                Row rowData = sheet.createRow(rowNum++);
                rowData.createCell(0).setCellValue(s.getId());
                rowData.createCell(1).setCellValue(s.getFirstName());
                rowData.createCell(2).setCellValue(s.getLastName());
                rowData.createCell(3).setCellValue(s.getYears());
                rowData.createCell(4).setCellValue(s.getStudies());
                rowData.createCell(5).setCellValue(s.getNationality());
            }
            FileOutputStream fos = new FileOutputStream(new File("proba.xlsx"), true);
            workbook.write(fos);
            fos.close();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


