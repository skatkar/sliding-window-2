import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode187 {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> output = new ArrayList<>();
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for(char ch : s.toCharArray()) {
            sb.append(ch);

            while(sb.length() >= 10) {
                if(sb.length() == 10){
                    String str = sb.toString();
                    if(set.contains(str)) output.add(str);
                    else set.add(str);
                }
                sb.deleteCharAt(0);
            }
        }

        return output;
    }
}
