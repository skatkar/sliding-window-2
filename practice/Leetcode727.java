package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leetcode727 {
    public static void main(String[] args) {
        Leetcode727 q = new Leetcode727();
        String answer = q.minWindow("cnhczmccqouqadqtmjjzl", "cm");
        System.out.println("answer = " + answer);
    }

    public String minWindow(String s1, String s2) {
        // 1. Find the right place for i variable as soon as we find the match with s2
        // 2. Once we get i, start from that index and try to find all the characters of s2
        // 3. If j covers all the characters of s2, capture the value of as a current start
        // 4. Replace the global start with current start only if it is greater (this might not happen at all)
        List<String> list = new ArrayList<>();

        for(int i=0; i < s1.length();){
            int j=0;
            if(s1.charAt(i) == s2.charAt(j)){
                int pointer=i;
                for(; pointer < s1.length() && j < s2.length(); pointer++){
                    if(s1.charAt(pointer) == s2.charAt(j)){
                       j++;
                    }else
                        i++;
                }
                if(j == s2.length()){
                    list.add(s1.substring(i, pointer));
                    i = pointer;
                }else{
                    i++;
                }
            }else
                i++;
        }

        return list.isEmpty() ? "" : list.stream().min(Comparator.comparingInt(String::length)).get();
    }
}
