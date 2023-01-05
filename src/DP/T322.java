package DP;

/*
给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
你可以认为每种硬币的数量是无限的。
e.g.
输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1
 */
public class T322 {
    public static void main(String[] args) {
        int[] coins = new int[]{2, 5, 10, 1};
        System.out.println(new Solution_T322_self().coinChange(coins, 27));
    }
}

class Solution_T322_self {
    /*
    完全背包-组合
        最少：dp意义：对应的硬币数目
     */
    public int coinChange(int[] coins, int amount) {
        // dp[i][j]：取值范围在nums[0-i] & 目标为j时：对应的最少硬币数
        int[][] dp = new int[coins.length][amount + 1];
        // 初始化dp
        dp[0][0] = 0; // 由于是硬币数量，所以初始化的dp[?][0]依旧为0
        for (int i = 1; i <= amount; i++)
            if (i >= coins[0])
                dp[0][i] = dp[0][i - coins[0]] == -1 ? -1 : 1 + dp[0][i - coins[0]];
            else
                dp[0][i] = amount + 1;
        /* 补全dp
        dp[i][j] = ① 0, j=0，√
                   ② -1, i=0 & j>0 & i<nums[j]，√
                   ③ -1, i=0 & j>0 & dp[i][i-coins[0]] == -1，√
                   ④ 1+dp[i][i-coins[0]], i=0 & j>0 & i<nums[j]，√
                   ⑤ min(dp[i-1][j], 1+dp[i][i-coins[j]]), i>0 & j>0：四种情况

         改进：使用amount+1标记-1的情况：这样就不用判断-1，而是直接比较后取min
         dp[i][j] = ① 0, j=0，√
                    ② amount+1, i=0 & j>0 & i<nums[j]，√
                    ③ 1+dp[i][i-coins[0]], i=0 & j>0 & i>=nums[j]，√
                    ④ dp[i-1][j], i>0 & j>0（j=0时硬币数就是0） & i<nums[j]
                    ⑤ min(dp[i-1][j], 1+dp[i][i-coins[j]]), i>0 & j>0（j=0时硬币数就是0） & i>=nums[j]
         */
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i])
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i]]);
            }
        }
        return dp[coins.length - 1][amount] > amount ? -1 : dp[coins.length - 1][amount];
    }
}
