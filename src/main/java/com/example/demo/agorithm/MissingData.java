package com.example.demo.agorithm;

//示例：缺失的区间
//
//        输入: nums = [0, 1, 3, 50, 75], lower = 0 和 upper = 99,
//        输出: [“2”, “4->49”, “51->74”, “76->99”]

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class MissingData {

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 50, 75};
        List<String> missingRange = new ArrayList<>();
        for (int i = 0; i < nums.length -1; i++) {
            int prov = nums[i];
            int nex = nums[i+1];

            System.out.println(prov +"="+nex);
            int divce = nex - prov;
            if (divce > 1) {
                missingRange.add((prov+1)+"到"+(nex-1));
            }
        }

        System.out.println(new Gson().toJson(missingRange));


    }



}
