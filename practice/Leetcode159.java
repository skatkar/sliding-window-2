package practice;

import java.util.HashMap;
import java.util.Map;

public class Leetcode159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) return 0;

        int maxSize = Integer.MIN_VALUE;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int right=0; right < s.length(); right++){
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right),0) + 1);

            while(map.size() > 2){
                char ch = s.charAt(left);
                map.put(ch, map.get(ch) - 1);
                map.remove(ch,0);
                left++;
            }
            maxSize = Math.max(maxSize, right - left + 1);
        }

        return maxSize;
    }
}
