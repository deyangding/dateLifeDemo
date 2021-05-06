package com.example.demo.domain.student;

import com.example.demo.frm.kafka.AbstactDomin;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
@Entity
public class Student extends AbstactDomin implements Serializable  {
    private static final long serialVersionUID = 1L;
    private String name;
    private String sex;
    private Long age;

    public static Student builer() {
        Student student = new Student();
        student.setId((long) (Math.random()*Math.random()*10000));
        student.setAge(121L);
        student.setName("小明a");
        student.setSex("男a");
        return student;
    }
}
