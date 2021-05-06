package com.example.demo.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class JestClientConfig {

    @Autowired
    private Environment env;

    @Value("${spring.elasticsearch.jest.uris}")
    String serverUri;

    @Bean
    public JestClient jestClient() {
//        String serverUri = this.env.getProperty("spring.elasticsearch.jest.uris");
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(serverUri)
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                .defaultMaxTotalConnectionPerRoute(5)
                // and no more 20 connections in total
                .maxTotalConnection(10)
                .build());
        return factory.getObject();
    }
}
