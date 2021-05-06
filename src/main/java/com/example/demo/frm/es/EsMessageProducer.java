package com.example.demo.frm.es;

import com.example.demo.frm.kafka.AbstactDomin;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Service
public class EsMessageProducer {
    @Autowired
    JestClient jestClient;

    public JestResult createIndex(AbstactDomin domin) {
        JestResult result = null;
        try {
            Index index = new Index.Builder(domin).index("estest").type(domin.getClass().getName().substring(domin.getClass().getName().lastIndexOf(".")+1).toLowerCase()).build();
            result = jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
