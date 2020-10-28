package com.springbootdemo.springbootdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringStudentRepository extends JpaRepository<Student,Integer>{

}
