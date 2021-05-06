package com.example.demo.agorithm;

import com.google.gson.Gson;

public class JsonUtil {
    public static void printJson(Object o){
        System.out.println(new Gson().toJson(o));
    }
}
