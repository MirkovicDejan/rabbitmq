package com.rabbitmq.demo.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Entity
@Table(name = "Student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentId",unique = true,nullable = false)
    private Integer studentId;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "firstName",nullable = false)
    private String firstName;
    @Column(name = "lastName",nullable = false)
    private String lastName;
    @Column(name = "years",nullable = false)
    private Integer years;
    @Column(name = "studies",nullable = false)
    private String studies;
    @Column(name = "nationality",nullable = false)
    private String nationality;
}
