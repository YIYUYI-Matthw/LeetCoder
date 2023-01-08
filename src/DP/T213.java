package DP;

import java.util.ArrayList;
import java.util.Arrays;
/*
打家劫舍Ⅱ：房子围成一圈
 */
public class T213 {
    public static void main(String[] args) {
        int[] golds = new int[]{0, 0};
        System.out.println(new Solution_T213_org().rob(golds));
    }
}

class Solution_T213_org {
    /*
    这题我没想到好的解决思路
    讨论区：把数组拆成[0, n-1] [1, n]
     */
    public int max_part(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        return dp[nums.length - 1];
    }

    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        // copyOfRange：newLength = to-from
        return Math.max(max_part(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                max_part(Arrays.copyOfRange(nums, 1, nums.length)));
    }
}