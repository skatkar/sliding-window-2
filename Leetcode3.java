import java.util.HashMap;
import java.util.Map;

public class Leetcode3 {
    // TC : O(n)
    // SC : O(1)
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;

        int slow, fast;
        slow = fast = 0;
        int max = Integer.MIN_VALUE;

        Map<Character,Integer> map = new HashMap<>();

        while(fast < s.length()) {
            char ch = s.charAt(fast);

            if(map.containsKey(ch)) {
                // This is needed as map.get(c) might return a value which is smaller than the current slow index
                // This will also avoid going in the opposite direction than the current slow pointer position
                slow = Math.max(slow, map.get(ch));
            }

            max = Math.max(max, fast - slow + 1);
            map.put(ch, fast + 1);
            fast++;
        }

        return max;
    }
}
