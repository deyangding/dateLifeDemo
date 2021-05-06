package com.example.demo.agorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//示例 1：
//
//        输入：nums = [1,2,3,1]
//        输出：2
//        解释：3 是峰值元素，你的函数应该返回其索引 2。
//
//        示例 2：
//
//        输入：nums = [1,2,1,3,5,6,4]
//        输出：1 或 5
//        解释：你的函数可以返回索引 1，其峰值元素为 2；
//        或者返回索引 5， 其峰值元素为 6。
public class findPeakElement {

    public static void main(String[] args) {
        int[] nums = {1,2,1,3,5,6,4};

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length-1; i++) {
            int mid = nums[i];
            if (nums[i == 0 ? i : i-1] <mid && mid > nums[i+1]) {
                result.add(mid);
            }
        }

        JsonUtil.printJson(result);
    }

}
