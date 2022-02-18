package com.rabbitmq.demo.service;
import com.rabbitmq.demo.models.Student;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class WriteDataService {

    public static List<Student> InitlistStudent = new ArrayList<Student>();

    public FileInputStream exportEXCEL() {
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

        /*      InitlistStudent = init();
            for (Student s : InitlistStudent) {
              Row rowData = sheet.createRow(rowNum++);
                rowData.createCell(0).setCellValue(s.getId());
                rowData.createCell(1).setCellValue(s.getFirstName());
                rowData.createCell(2).setCellValue(s.getLastName());
                rowData.createCell(3).setCellValue(s.getYears());
                rowData.createCell(4).setCellValue(s.getStudies());
                rowData.createCell(5).setCellValue(s.getNationality());

            } */
            File file = new File("proba.xlsx");
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
            workbook.close();
            FileInputStream fileInputStream = new FileInputStream("D:\\SourceTreeGitHubProjects\\rabbitmq\\proba.xlsx");
            return fileInputStream;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Student> addToList(Student student) {
        for(Student s : InitlistStudent){
            if(InitlistStudent.equals(s)){
                return InitlistStudent;
            }else {
                InitlistStudent.add(student);
                return InitlistStudent;
            }
        }
        return null;
    }


  /*  private List<Student> init(){
        InitlistStudent.add(new Student(1, "Dejan", "Mirkovic", 24, "IT", "SRB"));
        InitlistStudent.add(new Student(2, "Marko", "Milosevic", 30, "LAYER", "SRB"));
        InitlistStudent.add(new Student(3, "Aleksandar", "Bosancic", 35, "ELECTRICAL AND SOFTWARE ENGINEERING", "SRB"));
        InitlistStudent.add(new Student(4, "Nikolina", "Dubocanin", 22, "DOCTOR OD MEDICINE", "SRB"));
        return InitlistStudent;
    }*/
}


