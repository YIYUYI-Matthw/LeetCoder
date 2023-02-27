package Type09_DP;

import java.util.Arrays;

/*
给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class T188 {
    public static void main(String[] args) {
        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(new Solution_T188_self().maxProfit(2, prices));
    }
}

class Solution_T188_self {
    // 不能用滚动数组：每一个price都会遍历一遍买卖状态
    public int maxProfit(int k, int[] prices) {
        int[] buy_status = new int[k]; // buy1, buy2...
        int[] sell_status = new int[k]; // sell1, sell2...
        Arrays.fill(buy_status, prices[0]);
        for (int price : Arrays.copyOfRange(prices, 1, prices.length)) {
            // 提前处理第0次
            buy_status[0] = Math.min(buy_status[0], price);
            sell_status[0] = Math.max(sell_status[0], price - buy_status[0]);
            for (int i = 1; i < k; i++) {
                buy_status[i] = Math.min(buy_status[i], price - sell_status[i - 1]);
                sell_status[i] = Math.max(sell_status[i], price - buy_status[i]);
            }
        }
        return sell_status[k - 1];
    }
}