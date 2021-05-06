package com.example.demo.api;

import com.example.demo.domain.student.Student;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {
    @Autowired
    private JestClient client;

    @Test
    public void createIndex() throws IOException {
        Student student = Student.builer();
        Index index = new Index.Builder(student).index("estest").type("student").id("44").build();
        client.execute(index);
    }

    @Test
    public void search() throws IOException {
        //查询表达式
        String query = "{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"name\" : \"小明\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        Search search = new Search.Builder(query).addIndex("estest").addType("student").build();
//执行
        SearchResult result = client.execute(search);
        System.out.println(result.getJsonString());
    }


}
