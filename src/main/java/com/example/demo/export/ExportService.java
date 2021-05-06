package com.example.demo.export;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;

@Component
public class ExportService {

    public static BiConsumer<String,String> st(){
        return (x,y)->{
            System.out.println("洪俊");
            System.out.println(x+y);
        };
    }


    public static void aa (BiConsumer function){
        function.accept("D","dd");
    }

    public static void dd (BiConsumer function){
        function.accept("D","dd");
    }

    public static void main(String[] args) {
        aa(st());
    }


}
