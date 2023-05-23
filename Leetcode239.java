import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // TC : O(n) -> dq operations are O(1) -> time spent while iterating the index, adding it to the dq and then again removing it.
    // SC : O(n - k + 1) -> output array size (can be ignored as it is expected by the question)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k == 1) return nums;

        Deque<Integer> dq = new ArrayDeque<>();
        int[] output = new int[nums.length - k + 1];

        for(int i=0; i < nums.length; i++) {

            // 1. Clean up the dequeue
            // 1.1 Remove all the indexes from the queue having smaller number than the current index
            while(!dq.isEmpty() && nums[dq.getLast()] < nums[i]) {
                dq.removeLast();
            }
            // 1.2 If the window size is beyond k, remove the first index in the queue
            if(!dq.isEmpty() && dq.getFirst() == i - k){
                dq.removeFirst();
            }

            // 2. Add the current index to the queue
            dq.add(i);

            // 3. For the first window only
            if(i < k - 1) {
                continue;
            }

            // 4. Time to collect the max number's index in the current window
            output[i - k + 1] = nums[dq.getFirst()];
        }

        return output;
    }
}