package com.example.demo.service;

import com.example.demo.domain.student.Student;

import java.util.List;

public interface StudentService {
    Student findById(Long id);

    Student put(Student student);

    void delete(Long id);

    List<Student> findAll();


}
