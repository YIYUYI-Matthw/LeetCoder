package Type09_DP;

/*
给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
子数组 是数组中的一个连续部分。

输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 */
public class T53 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1};
        System.out.println(new Solution_T53_self().maxSubArray(nums));
    }
}

class Solution_T53_self {
    /*
    这个很好分析
    dp[i]：序列中从i开始往后的子数组最大值
     */
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int max = (int) -1e4;
        int[] dp = new int[length + 1];// 后面多加一位，把初始化操作去掉
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = Math.max(nums[i] + dp[i + 1], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
