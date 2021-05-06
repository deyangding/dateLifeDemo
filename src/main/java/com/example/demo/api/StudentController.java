package com.example.demo.api;

import com.example.demo.domain.student.Student;
import com.example.demo.service.StudentService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("one")
    public ResponseEntity<Student> findById() throws IOException {
//        Student student = Student.builer();
//        Index index = new Index.Builder(student).index("estest").type("student").build();
//        JestResult result = jestClient.execute(index);
        return ResponseEntity.ok(studentService.findById(2L));
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete() throws IOException {
//        Student student = Student.builer();
//        Index index = new Index.Builder(student).index("estest").type("student").build();
//        JestResult result = jestClient.execute(index);
        studentService.delete(2L);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("put")
    public ResponseEntity<?> put() throws IOException {
//        Student student = Student.builer();
//        Index index = new Index.Builder(student).index("estest").type("student").build();
//        JestResult result = jestClient.execute(index);
        Student student = Student.builer();
        studentService.put(student);
        return ResponseEntity.ok(student);
    }


    @Autowired
    JestClient jestClient;

    @RequestMapping("/all")
    public ResponseEntity<?> findAll() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("name", "a.json"));
        searchSourceBuilder.postFilter(boolQueryBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("estest").addType("student").build();
        try {
            JestResult result = jestClient.execute(search);
            if (result != null && result.isSucceeded()){
                return ResponseEntity.ok(result);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(studentService.findAll());
    }

}
