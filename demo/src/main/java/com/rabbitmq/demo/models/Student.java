package com.rabbitmq.demo.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Data
public class Student implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer years;
    private String studies;
    private String nationality;


}
