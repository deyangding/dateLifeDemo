package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

public class Producer {
    List<Color> colorList = new ArrayList<>();

    public void regist(Color color){
        colorList.add(color);
    }

    public void notifiy(String aaa){
        colorList.forEach(color -> color.update(aaa));
    }
}
