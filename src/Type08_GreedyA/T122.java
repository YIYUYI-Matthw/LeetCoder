package Type08_GreedyA;

/*
给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
返回 你能获得的 最大 利润 。
 */
public class T122 {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new Solution_T122_simplify().maxProfit(prices));
    }
}

class Solution_T122_self {
    // 和121相比，在于可以同一天出售
    // 遍历股票价格list，抓住所有上升边
    private int max_prof = 0;

    public int maxProfit(int[] prices) {
        int buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > buy)
                max_prof += prices[i] - buy;
            buy = prices[i]; // 今天卖的价格算作为明天买的
        }
        return max_prof;
    }
}

class Solution_T122_simplify {
    // 和121相比，在于可以同一天出售
    // 遍历股票价格list，抓住所有上升边
    private int max_prof = 0;

    public int maxProfit(int[] prices) {
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                max_prof += prices[i] - prices[i - 1];
        }
        return max_prof;
    }
}