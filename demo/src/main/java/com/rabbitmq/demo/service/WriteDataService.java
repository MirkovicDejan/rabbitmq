package com.rabbitmq.demo.service;

import com.rabbitmq.demo.exception.MyException;
import com.rabbitmq.demo.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@RequiredArgsConstructor
public class WriteDataService {


    public String exportCSV(Student student) throws MyException {
        if(student.getId() != null &&!student.getFirstName().equals(null) && !student.getLastName().equals(null) && !student.getNationality().equals(null) && student.getYears() != null){
                try {
                    File file = new File("student.csv");
                    FileWriter fw = new FileWriter(file,true);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(student);
                    pw.close();
                    return "Export is finish !";
                }catch (Exception e){
                    e.printStackTrace();
                }

        } throw new MyException("Object is empty !");
    }
}
