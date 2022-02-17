package com.rabbitmq.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Student{

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer years;
    private String studies;
    private String nationality;


}
