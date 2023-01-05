package DP;

/*
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class T416 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 5};
        System.out.println(new Solution_T416_m2().canPartition(nums));
    }
}

class Solution_T416_self {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0)
            return false;
        // dp数组
        boolean[][] dp = new boolean[nums.length][sum / 2 + 1];
        // 初始化
        if (nums[0] < sum / 2) // 如果一个数超过了target：那么直接跳过
            dp[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int target = 1; target <= sum / 2; target++) {
                if (target < nums[i]) {
                    dp[i][target] = dp[i - 1][target];
                    continue;
                }
                if (target == nums[i]) {
                    dp[i][target] = true;
                    continue;
                }
                dp[i][target] = dp[i - 1][target] || dp[i - 1][target - nums[i]];
            }
        }
        return dp[nums.length - 1][sum / 2];
    }
}

class Solution_T416_m2 {
    // 滚动数组：滚动数组不能用在这，排序也不行
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0)
            return false;
        // dp数组
        boolean[] dp = new boolean[sum / 2 + 1];
        // 初始化
        if (nums[0] < sum / 2) // 如果一个数超过了target：那么直接跳过
            dp[nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int target = 1; target <= sum / 2; target++) {
                if (target < nums[i])
                    continue;
                if (target == nums[i]) {
                    dp[target] = true;
                    continue;
                }
                dp[target] = dp[target] || dp[target - nums[i]];
            }
        }
        return dp[sum / 2];
    }
}