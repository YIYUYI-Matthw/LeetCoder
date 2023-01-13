package DP;

/*
给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class T123 {
    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(new Solution_T123_self().maxProfit(prices));
    }
}

class Solution_T123_self {
    public int maxProfit(int[] prices) {
        if (prices.length == 1)
            return 0;

        int pre_min = prices[0], post_max = prices[prices.length - 1];
        int[] pre_maxes = new int[prices.length];
        int[] post_maxes = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < pre_min) {
                pre_maxes[i] = pre_maxes[i - 1];
                pre_min = prices[i];
            } else {
                pre_maxes[i] = Math.max(pre_maxes[i - 1], prices[i] - pre_min);
            }
        }
        for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] > post_max) {
                post_maxes[i] = post_maxes[i + 1];
                post_max = prices[i];
            } else {
                post_maxes[i] = Math.max(post_maxes[i + 1], post_max - prices[i]);
            }
        }
        int temp_profit = 0;
        for (int i = 0; i < prices.length; i++)
            temp_profit = Math.max(pre_maxes[i] + post_maxes[i], temp_profit);
        return temp_profit;
    }
}
