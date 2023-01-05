package DP;

/*
给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
假设每一种面额的硬币有无限个。
 */
public class T518 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(new Solution_T518_self_simplify().change(5, coins));
    }
}

class Solution_T518_self {
    /*
    1. 完全背包问题可以用滚动数组：只要提前做好排序即可：每次选择的时候，都不会选择比当前值要大的数
    2. 完全背包和爬楼梯问题及其相似，不同点在于爬楼梯是排列问题、完全背包是组合问题
    3. 借鉴爬楼梯算法，应该以一定的策略使得该问题成为不重复的组合问题：设置外循环，重量从小到大一次决定：是否选用该重量、用几个
    4. 爬楼梯
        dp[i] = dp[i-1]+dp[i-2]
        dp[3] = dp[1] + dp[2]
              = 【(1)+2】 + 【dp[1]+1】 + 【dp[0]+2】
              = (1+2) + (1+1) + (0+2)
              = 3，其中：1-2和2-1是不同的：在选择1之后可以选择2、在选择2之后可以选择1
     5. 为了解决ch4中涉及的1-2和2-1在组合中重复的问题，应用ch3中的策略：外循环依次遍历各个重量
        dp[i][j]：前（i+1）中硬币组成数额为j的方法
        dp[i][j] = dp[i-1][j] + dp[i][j-coins[i]]
                即，前i个硬币组成数额j的组合数 = 不使用i组成j的组合数 + 使用i组成(j-coins[i])的组合数，
                    其中，dp[i][j-coins[i]]而不是dp[i-1][j-coins[i]]是因为：选用i的时候，前面可能已经在使用i了，所以要加上的是选用<=i组成j-coins[i]
     6. 完全背包可以使用滚动数组
     */
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        // 初始化
        dp[0][0] = 1;
        for (int i = coins[0]; i < amount + 1; i++) {
            dp[0][i] = dp[0][i - coins[0]];
        }
        // 填写dp
        for (int i = 1; i < coins.length; i++) {
            for (int j = 0; j < amount + 1; j++) {
                if (j < coins[i])
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
            }
        }
        return dp[coins.length - 1][amount];
    }
}

class Solution_T518_self_simplify {
    //使用滚动数组
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // 初始化
        dp[0] = 1;
        // 填写dp
        for (int coin : coins) {
            // 滚动数组的话可以不初始化”第一行“
            for (int j = coin; j < amount + 1; j++) {
                dp[j] = dp[j] + dp[j - coin];
            }
        }
        return dp[amount];
    }
}