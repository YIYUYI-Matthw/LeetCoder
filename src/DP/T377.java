package DP;

/*
给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 */
public class T377 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(new Solution_T377_self().combinationSum4(nums, 4));
    }
}

class Solution_T377_self {
    /*
    完全背包-组合：coin在外
     */
    public int combinationSum4(int[] nums, int target) {
        // dp[i][j]：在范围nums[0-j]中，有几种子集sum为i的【组合】：外循环为target：每次都可以考虑所有的集合元素
        int[][] dp = new int[target + 1][nums.length]; // boolean -> int
        // 初始化
        for (int i = 0; i < nums.length; i++)
            dp[0][i] = 1; // target为0时，0-j都有1种组合：啥也不选
        /* 填写dp
        dp[i][j] = ① 1, i=0, √
                   ② dp[i][j-1], i>0 & i<nums[j], j>0
                   ③ 0, i>0 & i<num[j], j=0
                   ④ dp[i-nums[j]][-1] + dp[i][j-1], i>0 & i>=nums[i], j>0
                   ⑤ dp[i-nums[j]][-1], i>0 & i>=nums[i], j=0
         */
        for (int temp_target = 1; temp_target <= target; temp_target++) {
            for (int j = 0; j < nums.length; j++) {
                if (temp_target < nums[j])
                    dp[temp_target][j] = j > 0 ? dp[temp_target][j - 1] : 0; // j<1说明前面没有更多选择：0，下同理
                else
                    dp[temp_target][j] = dp[temp_target - nums[j]][nums.length - 1] +
                            (j > 0 ? dp[temp_target][j - 1] : 0);
            }
        }
        return dp[target][nums.length - 1];
    }
}