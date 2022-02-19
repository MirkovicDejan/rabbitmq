package com.rabbitmq.demo.repository;

import com.rabbitmq.demo.models.FileDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB,Integer> {
    boolean existsByName(String name);
    FileDB findByName(String name);

}
