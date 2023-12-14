package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode30 {
    public static void main(String[] args) {
        Leetcode30 question = new Leetcode30();
        List<Integer> indexes = question.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo","barr","wing","ding","wing"});
        System.out.println("indexes = " + indexes);
    }

    // 1. create a map of words and their count
    // 2. Decide the jump length using the length of each word
    // 3. Use two pointers - right pointer will keep on expanding till words[0] * words.length
    // 4. Find the substrings each having the length words[0] and maintain their count in a map
    // 5. Compare the maps and if it equals then capture left pointer.
    // 6. Increment the left pointer by words[0] * words.length
    // 7. Repeat
    public List<Integer> findSubstring(String s, String[] words) {
        int jumpLength = words[0].length();
        int totalLength = jumpLength * words.length;
        int left = 0, right = 0;
        List<Integer> result = new ArrayList<>();

        Map<String, Integer> referenceMap = generateWordCountMap(words);

        for(; right < s.length(); right++) {
            if(right - left + 1 == totalLength){
                if(isCountMatch2(s, left, right, jumpLength, referenceMap)) {
                    result.add(left);
                }
                // left needs to be updated by 1 everytime. If we jump it by jumpLength then we might miss a few strings
                // Because the given input string can't be split into equals parts of length word[0].length
                // For example,
                // s = "lingmindraboofooowingdingbarrwingmonkeypoundcake"
                // words = ["fooo","barr","wing","ding","wing"]
                left++;

            }
        }

        return result;
    }

    private boolean isCountMatch(String s, int lower, int higher, int jumpLength, Map<String, Integer> referenceMap) {
        Map<String, Integer> map = new HashMap<>();
        for(int i=lower; i <= higher; i+= jumpLength) {
            String str = s.substring(i, i + jumpLength);
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        return map.equals(referenceMap);
    }

    private boolean isCountMatch2(String s, int lower, int higher, int jumpLength, Map<String, Integer> referenceMap) {
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(int i=lower; i <= higher; i+= jumpLength) {
            int right = i;
            while(right - i + 1 <= jumpLength){
                sb.append(s.charAt(right));
                right++;
            }
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
            sb.delete(0, sb.length());
        }
        return map.equals(referenceMap);
    }

    private Map<String, Integer> generateWordCountMap(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for(String word : words) {
            map.put(word, map.getOrDefault(word,0) + 1);
        }
        return map;
    }
}
