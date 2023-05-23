import java.util.HashMap;
import java.util.Map;

public class Leetcode76 {
    public static void main(String[] args) {
        Leetcode76 q = new Leetcode76();
        String s = q.minWindow("ADOBECODEBANG", "ABC");
    }

    public String minWindow(String s, String t) {
        if(s == null || s.length() == 0) return s;

        Map<Character, Integer> map = new HashMap<>();
        for(char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int start = 0;
        int end = 0;
        int minLen = Integer.MAX_VALUE;
        int charToLeft = t.length();
        int minStart = 0;

        while(end < s.length()) {
            char eChar = s.charAt(end);

            // Incoming character
            if(map.containsKey(eChar)) {
                int count = map.get(eChar);
                if(count > 0) {
                    charToLeft--;
                }
                map.put(eChar, count - 1);
            }
            end++;

            // Outgoing character as long as charToLeft is zero
            while(charToLeft == 0){
                if(minLen > end - start){
                    minLen = end - start;
                    minStart = start;
                }

                char sChar = s.charAt(start);
                if(map.containsKey(sChar)) {
                    int count = map.get(sChar);
                    if(count == 0) {
                        charToLeft++;
                    }
                    map.put(sChar, count + 1);
                }
                start++;
            }

        }
        return minStart == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
