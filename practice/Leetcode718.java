package practice;

public class Leetcode718 {
    public int findLength(int[] nums1, int[] nums2) {
        int maxLength = 0;

        // Let's assume these two arrays as two trains
        // We'll go with two passes, meaning,
        // First train is moving and the second is stationary AND
        // First train is stationary and the second one is moving

        // First pass - Like the second train is moving
        for(int pointer=0; pointer < nums2.length; pointer++){
            int currMax = 0;
            for(int i=0, j=pointer; i < nums1.length && j < nums2.length; i++, j++){
                if(nums1[i] == nums2[j]){
                    currMax++;
                    maxLength = Math.max(maxLength, currMax);
                }else
                    currMax = 0;
            }
        }

        // Second pass - Like first train is moving
        for(int pointer=0; pointer < nums1.length; pointer++){
            int currMax = 0;
            for(int i=0, j=pointer; j < nums1.length && i < nums2.length; j++, i++){
                if(nums1[j] == nums2[i]){
                    currMax++;
                    maxLength = Math.max(maxLength, currMax);
                }else
                    currMax = 0;
            }
        }

        return maxLength;
    }

    public int findLength2(int[] nums1, int[] nums2) {
        return Math.max(helper(nums1, nums2), helper(nums2, nums1));
    }

    private int helper(int[] A, int[] B){
        int maxLength = 0;

        // First pass
        for(int pointer=0; pointer < B.length; pointer++){
            int currMax = 0;
            for(int i=0, j=pointer; i < A.length && j < B.length; i++, j++){
                if(A[i] == B[j]){
                    currMax++;
                    maxLength = Math.max(maxLength, currMax);
                }else
                    currMax = 0;
            }
        }

        return maxLength;
    }

}
