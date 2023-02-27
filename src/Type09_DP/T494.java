package Type09_DP;

/*
给你一个整数数组 nums 和一个整数 target 。
向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
target: [-1000, 1000]
nums.length: [1,?]
nums: [0, 1000]
 */
public class T494 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        System.out.println(new Solution_T494_self().findTargetSumWays(nums, 3));
    }
}

class Solution_T494_self {
    /*
    思路：0-1背包问题：
        把数组分成两堆a、b。|a-b|=target即可：a = (sum-target)/2
        不同：这里要求统计数量而不是能不能
            所以：0-1背包+走楼梯：到某个程度有多少种解法；且dp[?][0]为true
     */
    public int findTargetSumWays(int[] nums, int target) {
        target = Math.abs(target);
        if (nums.length == 1)
            if (nums[0] == target)
                return 1;
            else
                return 0;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum - target < 0 || (sum - target) % 2 != 0)
            return 0;
        int[][] dp = new int[nums.length + 1][(sum - target) / 2 + 1];
        dp[0][0] = 1; // 第一位是true
        for (int i = 1; i <= nums.length; i++) {
            for (int temp_tgt = 0; temp_tgt <= (sum - target) / 2; temp_tgt++) {
                if (temp_tgt < nums[i - 1]) {
                    dp[i][temp_tgt] = dp[i - 1][temp_tgt];
                    continue;
                }
                dp[i][temp_tgt] = dp[i - 1][temp_tgt] + dp[i - 1][temp_tgt - nums[i - 1]];
            }
        }
        return dp[nums.length][(sum - target) / 2];
    }
}