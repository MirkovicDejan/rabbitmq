package com.rabbitmq.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.*;
import java.io.FileOutputStream;

@Entity
@Table(name = "FileDB")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDB{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileId",unique = true,nullable = false)
    private Integer fileId;
    @Column(name = "name",nullable = false)
    private String name;
    @Lob
    private byte [] content;

}
