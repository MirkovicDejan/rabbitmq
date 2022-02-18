package com.rabbitmq.demo.controller;

import com.rabbitmq.demo.exception.MyException;
import com.rabbitmq.demo.models.Student;
import com.rabbitmq.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private final StudentService studentService;

    @PostMapping("/add-student")
    public Student saveStudent(@RequestBody Student student) throws MyException {
        return studentService.saveStudentInDB(student);
    }
    @GetMapping("/get-all-student")
    public List<Student> allStudents() throws MyException{
        return  studentService.getAll();
    }
    @GetMapping("/get-one-student")
    public Student getOne(@RequestParam Integer id) throws MyException {
        return studentService.getOneStudent(id);
    }
    @DeleteMapping("/delete-student")
    public String deleteStudent(@RequestParam Integer id) throws MyException {
        return studentService.deleteStudent(id);
    }
}
