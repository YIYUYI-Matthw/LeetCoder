package Type09_DP;

import java.util.Arrays;

public class T674 {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 7, 1, 2, 3};
        System.out.println(new Solution_T674_self().findLengthOfLCIS(nums));
    }
}

class Solution_T674_self {
    /*
    dp[i]：以i结尾的子序列的max_len
    dp[i] = dp[i-1]+1, nums[i]>nums[i-1]
            1, otherwise
     */
    public int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length];
        int max_len = 1;
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                max_len = Math.max(max_len, dp[i]);
            }
        }
        return max_len;
    }
}