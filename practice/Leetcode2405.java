package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode2405 {
    public static void main(String[] args) {
        Leetcode2405 q = new Leetcode2405();
        int answer = q.partitionString("abacaba");
        System.out.println("answer = " + answer);
    }

    public int partitionString(String s) {
        int count = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();

        for(; right < s.length(); right++) {
            char ch = s.charAt(right);
            if(map.containsKey(ch)){
                count++;
                map.clear(); // this is needed otherwise there will be an overlap for the same character appearing.
                // Eg. abacaba
            }
            map.put(ch, right);
        }

        // right went outside the limit but there are still some characters left. Last substring
        return count + 1;
    }

    public int partitions(String s) {
        List<String> list = new ArrayList<>();
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();

        for(; right < s.length(); right++) {
            char ch = s.charAt(right);
            if(map.containsKey(ch)) {
                list.add(s.substring(left, right));
                left = right;
                map.clear();
            }
            map.put(ch, right);
        }

        list.add(s.substring(left));

        return list.size();
    }
}
