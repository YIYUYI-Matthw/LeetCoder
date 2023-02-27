package Type09_DP;

import java.util.Arrays;

/*
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
public class T300 {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 2, 3};
        System.out.println(new Solution_T300_org().lengthOfLIS(nums));
    }
}

// TODO：这个看题解写的
class Solution_T300_org {
    // dp[i]：结尾为i的子上升子序列最大长度
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // 至少有一个
        int max_len = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++)
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            max_len = Math.max(max_len, dp[i]);
        }
        return max_len;
    }
}