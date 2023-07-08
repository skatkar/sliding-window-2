import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Leetcode76 {
    public static void main(String[] args) {
        Leetcode76 q = new Leetcode76();
        String s = q.minWindow("ADOBECODEBANC", "ABC");
        System.out.println("s = " + s);
    }

    public String minWindow(String s, String t) {
        if(t == null || t.length() == 0) return "";

        // To track characters matched so far and their count
        Map<Character, Integer> windowMap = new HashMap<>();

        // To track characters in t and their count
        Map<Character, Integer> countTMap = new HashMap<>();
        for(char ch : t.toCharArray()) {
            countTMap.put(ch, countTMap.getOrDefault(ch,0) + 1);
        }

        int have = 0, need = countTMap.size();

        int minLen = Integer.MAX_VALUE, minStart = 0;

        int left = 0, right = 0;
        for(; right < s.length(); right++) {
            char ch = s.charAt(right);

            if(countTMap.containsKey(ch)) {
                windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);

                if(Objects.equals(windowMap.get(ch), countTMap.get(ch)))
                    have++;

                while(have == need){
                    // Update our result
                    if(minLen > right - left + 1) {
                        minLen = right - left + 1;
                        minStart = left;
                    }

                    // Pop out the character from left
                    char charToRemove = s.charAt(left);
                    if(windowMap.containsKey(charToRemove)) {
                        windowMap.put(charToRemove, windowMap.get(charToRemove) - 1);
                        // making it != instead of < will fail the below test case
                        // s = "aaaaaaaaaaaabbbbbcdd", t = "abcdd"
                        // there might be a situation where windowMap count is greater than countTMap even though we are decrementing it.
                        if(windowMap.get(charToRemove) < countTMap.get(charToRemove))
                            have--;
                    }
                    left++;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }

    public String minWindow2(String s, String t) {
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
