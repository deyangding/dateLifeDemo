package com.example.demo.service;

import com.example.demo.domain.student.Student;
import com.example.demo.domain.student.StudentRepository;
import com.example.demo.frm.es.EsMessageProducer;
import com.example.demo.frm.kafka.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MessageProducer messageProducer;

    @Autowired
    EsMessageProducer esMessageProducer;

    @Override
    @Cacheable(value = "student", key = "'student'.concat(#id.toString())")
    public Student findById(Long id) {
        return studentRepository.findById(2L).orElse(null);
    }

    @Override
    @CachePut(value = "student", key = "'student'.concat(#student.id.toString())")
//    @CachePut(value = "student", keyGenerator = "keyGenerator")
    public Student put(Student student) {
        student = studentRepository.save(student);
        messageProducer.sendMessage("ddy", student);
        esMessageProducer.createIndex(student);
        return student;
    }

    @Override
    @CacheEvict(value = "student", key = "'student'.concat(#id.toString())")
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findAll() {
        return null;
    }


    public static void main(String[] args) {
        int[] strings = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("dd");
        int a = search(strings, 0, strings.length - 1, 7);
        System.out.println(a);
        System.out.println("dd");
    }

    public static int search(int[] strings, int start, int end, int key) {
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (key == strings[mid]) {
                return mid;
            } else if (key < strings[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
