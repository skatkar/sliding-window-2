import java.util.HashMap;
import java.util.Map;

public class Leetcode1297 {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int max = 0;
        Map<String, Integer> strCountMap = new HashMap<>();
        Map<Character, Integer> charCountMap = new HashMap<>();

        int left = 0, right = 0;
        while(right < s.length()) {
            char ch = s.charAt(right++);
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);

            while((right - left) >= minSize && (right - left) <= maxSize){
                if(charCountMap.size() <= maxLetters){
                    String subStr = s.substring(left, right);
                    strCountMap.put(subStr, strCountMap.getOrDefault(subStr, 0) + 1);
                }
                char charToRemove = s.charAt(left++);
                charCountMap.put(charToRemove, charCountMap.get(charToRemove) - 1);
                charCountMap.remove(charToRemove, 0);
            }
        }

        for(int value : strCountMap.values())
            max = Math.max(max, value);

        return max;
    }

    public static void main(String[] args) {
        Leetcode1297 question = new Leetcode1297();
        int answer = question.maxFreq("aababcaab", 2, 3, 4);
        System.out.println("answer = " + answer);
    }
}
