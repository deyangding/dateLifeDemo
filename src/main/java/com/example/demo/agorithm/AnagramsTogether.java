package com.example.demo.agorithm;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import lombok.val;
import org.springframework.util.StringUtils;

//Input: strs = ["eat","tea","tan","ate","nat","bat"]
//Output: [["bat"],["nat","tan"],["ate","eat","tea"]]


//    class Solution {
//        public List<List<String>> groupAnagrams(String[] strs) {
//            if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
//            Map<String, List<String>> map = new HashMap<String, List<String>>();
//            //处理技巧，同一个类别中string的char数组排序后是一样的，以这个作为map的key
//            for (String s : strs) {
//                char[] ca = s.toCharArray();
//                Arrays.sort(ca);
//                String keyStr = String.valueOf(ca);
//
//                if (!map.containsKey(keyStr))
//                    map.put(keyStr, new ArrayList<String>());
//
//                map.get(keyStr).add(s);
//            }
//            return new ArrayList<List<String>>(map.values());
//        }
//
//    }
public class AnagramsTogether {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<String> strings = new ArrayList<>(Arrays.asList(strs));


        List<Set<String>> lists = new ArrayList<>();

        int i = 0;
        for (String string : strings) {
            if (!con(string,lists)) {
                Set<String> aaa = new HashSet<>();
                findsame(string,strings,aaa,i+1);
                lists.add(aaa);
            }

            i++;
        }


        System.out.println(new Gson().toJson(lists));
    }

    public static boolean con(String next,List<Set<String>> lists){
        for (Set<String> list : lists) {
            if (list.contains(next)) {
                return true;
            }
        }

        return false;

    }

    public static void findsame(String next, List<String> strings,  Set<String> liu,int i) {
        String sortInput = sortC(next);
        liu.add(next);
        for (; i < strings.size()-1; i++) {
            if (sortInput.equals(sortC(strings.get(i)))){
                liu.add(strings.get(i));
            }
        }
    }

    public static String sortC(String next) {
        char[] ar = next.toCharArray();
        Arrays.sort(ar);

//        System.out.println("######################");
//        System.out.println(next);
//        System.out.println(String.valueOf(ar));
//        System.out.println("######################");
        return String.valueOf(ar);
    }



}
