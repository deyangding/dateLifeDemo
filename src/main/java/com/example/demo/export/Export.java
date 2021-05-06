package com.example.demo.export;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
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

@Component
public class Export {



    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd-HH-mm-ss";


    public void writeWithoutHead(boolean isJs) throws IOException {
        LinkedHashMap<String, String> propMap = readProperties(isJs);
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String name = sdf1.format(date);
        try (
                OutputStream out = new FileOutputStream(name + "_message_zh.xlsx")) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, false);
            Sheet sheet1 = new Sheet(1, 0);
            sheet1.setSheetName("sheet1");
            List<List<String>> data = new ArrayList<>();
            propMap.forEach((k, v) -> {
                List<String> item = new ArrayList<>();
                item.add(k);
                item.add(v);
                data.add(item);
            });
            writer.write0(data, sheet1);
            writer.finish();
        }
    }

    private LinkedHashMap<String, String> readProperties(boolean isJs) {
        LinkedHashMap<String, String> propMap = new LinkedHashMap<String, String>();
        File propertiesFile = new File("messages_zh_CN.properties");
        if (propertiesFile.isFile()) {
            try {
                FileInputStream fisProp = new FileInputStream(propertiesFile);
                InputStreamReader reader = new InputStreamReader(fisProp, "UTF-8");
                BufferedReader bReader = new BufferedReader(reader);
                String s = "";
                System.out.println("##############:isJs"+isJs);
                if (isJs) {
                    while ((s = bReader.readLine()) != null) {
                        String[] keys = s.split(":");
                        String key = keys[0];
                        String value = keys.length > 1 ? keys[1] : " ";
                        propMap.put(key, value);
                    }
                } else {
                    while ((s = bReader.readLine()) != null) {
                        if (!s.startsWith("#")) {
                            String[] keys = s.split("=");
                            String key = keys[0];
                            String value = keys.length > 1 ? keys[1] : " ";
                            propMap.put(key, value);
                        } else {
                            propMap.put(s, " ");
                        }
                    }
                }

                System.out.println("Properties Map ... \n" + propMap);
                fisProp.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return propMap;
    }
}
