package com.rabbitmq.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "FileDB")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileId",unique = true,nullable = false)
    private Integer fileId;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name="type",nullable = false)
    private String type;
    @Lob()
    private byte[] data;

}
