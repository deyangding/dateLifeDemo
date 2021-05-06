package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CacheValue {

    private String name;
    private String ttl;


    public static List<CacheValue> readAllConfig(ApplicationContext context) throws IOException {
        YAMLFactory factory = new YAMLFactory();
        ObjectMapper yamlMapper = new ObjectMapper(factory);
        yamlMapper.registerModule(new JavaTimeModule());
        CollectionType listType = yamlMapper.getTypeFactory().constructCollectionType(ArrayList.class, CacheValue.class);
        Resource[] resources = context.getResources("classpath*:cache.yaml");
        Map<String, Object> configMap = Maps.newHashMap();
        Resource[] var6 = resources;
        int var7 = resources.length;
        List<CacheValue> re = new ArrayList<>();
        for (int var8 = 0; var8 < var7; ++var8) {
            Resource resource = var6[var8];
            InputStream is = resource.getInputStream();
            List<CacheValue> configList = (List) yamlMapper.readValue(is, listType);
            re.addAll(configList);
        }

        return re;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
}
