package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leetcode616 {
    public static void main(String[] args) {
        Leetcode616 q = new Leetcode616();
        String answer = q.addBoldTag("abcxyz123", new String[]{"abc","123"});
        System.out.println("answer = " + answer);
    }

    public String addBoldTag(String s, String[] words) {
        if(words.length == 0) return s;

        StringBuilder sb = new StringBuilder();
        List<int[]> intervals = findIntervals(s,words);
        if(intervals.isEmpty()){
            return s;
        }
         System.out.println("Before merging - ");
         for (int[] interval : intervals) {
             for (int i : interval) {
                 System.out.print(i + " ");
             }
             System.out.println();
         }
         System.out.println("After merging - ");
        List<int[]> mergedIntervals = mergeIntervals(intervals);
         for (int[] interval : mergedIntervals) {
             for (int i : interval) {
                 System.out.print(i + " ");
             }
             System.out.println();
         }
        //append substrings to result
        for(int i=0; i < mergedIntervals.size(); i++) {
            if(i == 0){
                sb.append("<b>").append(s, mergedIntervals.get(i)[0], mergedIntervals.get(i)[1]).append("</b>");
            }else{
                sb.append(s, mergedIntervals.get(i - 1)[1], mergedIntervals.get(i)[0]);
                sb.append("<b>").append(s, mergedIntervals.get(i)[0], mergedIntervals.get(i)[1]).append("</b>");
            }
        }

        return sb.append(s, mergedIntervals.get(mergedIntervals.size() - 1)[1], s.length()).toString();
    }

    private List<int[]> mergeIntervals(List<int[]> intervals) {
        List<int[]> result = new ArrayList<>();

        int[] newInterval = intervals.get(0);
        result.add(newInterval);
        for(int[] interval : intervals) {
            if(newInterval[1] >= interval[0]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }else{
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result;
    }

    private List<int[]> findIntervals(String s, String[] words) {
        List<int[]> intervals = new ArrayList<>();
        for (String word : words) {
            int start = s.indexOf(word);
            while(start >= 0) {
                intervals.add(new int[]{start, start + word.length()});
                start = s.indexOf(word, start + 1);
            }
        }
        intervals.sort(Comparator.comparingInt(a -> a[0]));
        return intervals;
        //return intervals.toArray(int[][]::new);
    }
}
