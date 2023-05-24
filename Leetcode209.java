public class Leetcode209 {
    public static void main(String[] args) {
        Leetcode209 question = new Leetcode209();
        int minWindow = question.minSubArrayLen2(6, new int[]{7,2,4,6,5,8});
        System.out.println("minWindow = " + minWindow);
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left, right;
        left = right = 0;
        int min = 0;
        int sum = 0;
        for(; right < nums.length; right++) {
            sum += nums[right];

            while(sum >= target) {
                if(sum == target) {
                    min = Math.min(min, right - left + 1);
                }
                sum -= nums[left];
                left++;
            }
        }

        return min;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int left, right;
        left = right = 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(; right < nums.length; right++) {
            sum += nums[right];

            while(sum >= target) {
                min = Math.min(min, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
