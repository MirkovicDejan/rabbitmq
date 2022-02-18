package com.rabbitmq.demo.repository;

import com.rabbitmq.demo.models.FileDB;
import com.rabbitmq.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB,Integer> {

}