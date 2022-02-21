package com.rabbitmq.demo.service;
import com.opencsv.CSVWriter;
import com.rabbitmq.demo.exception.MyException;
import com.rabbitmq.demo.models.FileDB;
import com.rabbitmq.demo.models.Student;
import com.rabbitmq.demo.repository.FileDBRepository;
import com.rabbitmq.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
@RequiredArgsConstructor
public class FilesService {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final FileDBRepository fileDBRepository;

    public byte[] createAndExportExcel(String name) throws MyException {
        if (!fileDBRepository.existsByName(name+".xlsx")) {
            try {
                Workbook workbook = new XSSFWorkbook();
                //Set size for columns
                Sheet sheet = workbook.createSheet(name);
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
                String[] column = {"ID", "USERNAME", "EMAIL", "PASSWORD", "FIRST NAME", "LAST NAME", "YEARS", "STUDIES", "NATIONALITY"};
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
                List<Student> listForExcel = studentRepository.findAll();
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
                FileOutputStream fileOutputStream = new FileOutputStream(file,true);
                workbook.write(fileOutputStream);
                fileOutputStream.close();
                workbook.close();
                byte[] bytes = Files.readAllBytes(file.toPath());
                FileDB fileDB = new FileDB();
                fileDB.setName(name);
                fileDB.setContent(bytes);
                fileDBRepository.save(fileDB);
                return bytes;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new MyException("Files with name : " + name + " exist in database !");
    }

    public byte [] readExcelFromDBAndExport(String name) throws MyException {
        if(fileDBRepository.existsByName(name+".xlsx")){
            FileDB fileFromDatabase = fileDBRepository.findByName(name+".xlsx");
            return fileFromDatabase.getContent();
        }throw new MyException("Files with name : " + name + " does not exist in database !");
    }

    public byte[] createAndExportCSV(String name) throws MyException {
        List<Student> listStudentCSV = studentRepository.findAll();
        if(!fileDBRepository.existsByName(name+".csv")) {
            try {
                File csvFile = new File(name+".csv");
                FileWriter fw = new FileWriter(csvFile, true);
                CSVWriter writer = new CSVWriter(fw);
                for (Student student : listStudentCSV) {
                    writer.writeNext(new String[]{student.getStudentId() + "," + student.getUsername() + "," + student.getEmail() + "," + student.getPassword() + ","
                            + student.getFirstName() + "," + student.getLastName() + "," + student.getYears() + "," + student.getStudies() + "," + student.getNationality() + ","});
                }
                writer.flush();
                fw.close();
                byte[] readCsvFile = Files.readAllBytes(csvFile.toPath());
                FileDB fileDB = new FileDB();
                fileDB.setName(name);
                fileDB.setContent(readCsvFile);
                fileDBRepository.save(fileDB);
                return readCsvFile;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } throw new MyException("File with name : "+name+" exist in database !");
    }

    public byte[] readCSVFromDB(String name) throws MyException {
        if(fileDBRepository.existsByName(name+".csv")){
            FileDB csv = fileDBRepository.findByName(name+".csv");
            return csv.getContent();
        }throw new MyException("CSV file with name : "+name+" does not exist in database !");
    }

    //export excel without save excel on disc
    public byte[] exportExcel(String name) throws MyException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(name);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<Student> listStudent;
        if(!fileDBRepository.existsByName(name+".xlsx")){
            try {
                sheet.setColumnWidth(0, 1000);
                sheet.setColumnWidth(1, 3000);
                sheet.setColumnWidth(2, 6000);
                sheet.setColumnWidth(3, 4000);
                sheet.setColumnWidth(4, 3000);
                sheet.setColumnWidth(5, 3000);
                sheet.setColumnWidth(6, 2000);
                sheet.setColumnWidth(7, 10000);
                sheet.setColumnWidth(8, 2000);
                String[] column = {"ID", "USERNAME", "EMAIL", "PASSWORD", "FIRST NAME", "LAST NAME", "YEARS", "STUDIES", "NATIONALITY"};
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
                listStudent = studentRepository.findAll();
                CellStyle style = workbook.createCellStyle();
                XSSFFont font2 = workbook.createFont();
                font2.setFontHeight(11);
                style.setFont(font2);
                int rowCount = 1;
                for (Student s : listStudent) {
                    Row row2 = sheet.createRow(rowCount++);
                    int columnCount = 0;
                    createCell(row2, columnCount++, String.valueOf(s.getStudentId()), style);
                    createCell(row2, columnCount++, s.getUsername(), style);
                    createCell(row2, columnCount++, s.getEmail(), style);
                    createCell(row2, columnCount++, s.getPassword(), style);
                    createCell(row2, columnCount++, s.getFirstName(), style);
                    createCell(row2, columnCount++, s.getLastName(), style);
                    createCell(row2, columnCount++, String.valueOf(s.getYears()), style);
                    createCell(row2, columnCount++, s.getStudies(), style);
                    createCell(row2, columnCount++, s.getNationality(), style);
                }
                workbook.write(outputStream);
                workbook.close();
                return outputStream.toByteArray();

            }catch (Exception e){
                e.printStackTrace();
            }
        }throw new MyException("File with name : "+name+" exist in db!");
    }

    public void exportCSV(String name, HttpServletResponse response){
        List<Student>studentList = studentRepository.findAll();
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+name+"_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        try {
                ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
                String[] csvHeader = {"ID","USERNAME","EMAIL","PASSWORD","FIRST NAME","LAST NAME","YEARS","STUDIES","NATIONALITY"};
                String[] nameMapping = {"studentId","username","email","password","firstName","lastName","years","studies","nationality"};
                csvWriter.writeHeader(csvHeader);
                for(Student student:studentList){
                    csvWriter.write(student, nameMapping);
                }
                csvWriter.close();

            }catch (Exception e){
                e.printStackTrace();
            }
    }
}




