package DP;

/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
e.g.
输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。

输入：[2,1,1,2]
输出：4
 */
public class T198 {
    public static void main(String[] args) {
        int[] golds = new int[]{2, 1, 1, 2};
        System.out.println(new Solution_T198_self_for().rob(golds));
    }
}

class Solution_T198_self_for {
    /*
    【错】打家劫舍Ⅰ可以不用DP，直接遍历一边即可
    打家劫舍的分析过程和“单行最大子矩阵”很像，见pics/T198
    每次长度增加1，就要考虑：是否要用i：① 不用：dp[i-1]；② 用：dp[i-2]+i：想要用，至少（i-1）处不能有值
    注意：由于没有负数，所以状态转移时，不考虑单独采用nums[i]
     */
    public int rob(int[] nums) {
        // 特殊情况
        if (nums.length == 1)
            return nums[0];
        // dp[i]：前i个数的最大值：考虑什么时候数组长度为n+1、n
        int[] dp = new int[nums.length];
        // 初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        /* 填写dp
        dp[i] = ① nums[i], i<=1; √
                ② max(dp[i-1], dp[i-2]+nums[i]), i>1
         */
        for (int i = 2; i < nums.length; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        return dp[nums.length - 1];
    }
}