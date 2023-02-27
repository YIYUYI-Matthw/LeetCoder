package Type09_DP;

/*
给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
    卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

输入: prices = [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class T309 {
    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 0, 2};
        System.out.println(new Solution_T309_self_org().maxProfit(prices));
    }

}

class Solution_T309_dep {
    /*
    如果不含冷冻期：
        dp[i][j]：在前i个价格区间内，这一次选择j=0买或j=1卖的最大利润
        dp[i][0] = max(dp[i-1][1]-price, dp[i-1][0])
        dp[i][1] = max(dp[i-1][1], price + dp[i-1][0])
    */
    /*
    两（三）种状态：
        dp[i][j]：前i个价格区间在j状态时的利润

        dp[i][j] = max(dp[i-1][买], dp[i-1][卖], dp[i-1][啥也不干])

        dp[i][j] = ①


        // 以下弃用
        ① 卖过 & 非冷冻期：可以买
        ② 非卖过：可以卖：比较利润

        ① 买：
            1） 冷冻期：利润不变，买不了
            2） 非冷冻期：
                a. 买：profit = profit - price
                b. 不买：profit不变
        ② 卖：比较今天卖和上一次卖的利润
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
        }
        return dp[prices.length - 1][1];
    }
}

class Solution_T309_self_org {
    /*
    dp[i]表示第i天结束后（实际上是到第二天了）的maxprofit
    每一天有四种状态：持股、（非持股）冷冻期、非持股非冷冻期
    约定：买入为负收入、卖出为正收入，买入价越低、卖出价越高则利润越大
    dp[i][0] = max(dp[i-1][0], dp[i-1][2]-prices[i])
    dp[i][1] = dp[i-1][0]+prices[i-1] // 冷冻期：上一天卖了：只有持股的状态下才能卖
    dp[i][2] = max(dp[i-1][1], dp[i-1][2])
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 1)
            return 0;
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }
}