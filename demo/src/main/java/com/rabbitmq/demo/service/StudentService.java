package com.rabbitmq.demo.service;

import com.rabbitmq.demo.exception.MyException;
import com.rabbitmq.demo.models.Student;
import com.rabbitmq.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public Student saveStudentInDB(Student student) throws MyException{
       if(studentRepository.existsByUsername(student.getUsername())){
           throw new MyException("Username is already used !!! Please insert another ! ");
       }else if(studentRepository.existsByEmail(student.getEmail())){
           throw new MyException("Email is already used !!! Please insert another ! ");
       }else if(student.getYears() <= 0) {
           throw new MyException("Years is : " + student.getYears() + " must be more than 0 !!!");
       }else if(studentRepository.existsByPassword(student.getPassword())) {
           throw new MyException("Password is already used !!! Please insert another !!!");
       } else {
           return studentRepository.save(student);
       }
       }

    public List<Student> getAll() throws MyException {
        List<Student> all = studentRepository.findAll();
        if(all.isEmpty()){
            throw new MyException("List is empty !");
        }else{
            return all;
        }
    }

    public String deleteStudent(Integer id) throws MyException {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return "Student with id : "+id+" is deleted !";
        }else{
            throw new MyException("Student with id : "+id+" doesn't exist in database !!!");
        }
    }

    public Student getOneStudent(Integer id) throws MyException {
        if(studentRepository.existsById(id)){
            return studentRepository.findById(id).get();
        }
        throw new MyException("Student with id : "+id+" doesn't exist in database !!!");
    }
}

