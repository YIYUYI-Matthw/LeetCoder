package DP;

/*
1. dp数组+下标含义：dp[i]最晚第i天卖出去
2. 递推公式：由简入繁：第i天最大收益 = max(第i-1天收益，price[i天]-min(前i-1天）)
    第i天，卖或不卖：卖-price-min；不卖：dp[i-1]
3. 初始化
4. 遍历顺序
5. 验证：根据递推公式推导（这一步通常在2中完成
 */
public class T121 {
    public static void main(String[] args) {
        // 这里的DP比枚举的方法只少了一次全体的排序
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new Solution_T121_self_dp().maxProfit(prices));
    }
}

class Solution_outofMem_T121 {
    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days <= 1)
            return 0;
        int[][] dp = new int[days + 1][days + 1];// 默认初始化为0
        for (int i = 1; i <= days; i++) {
            for (int j = i; j <= days; j++) {
                int temp_price = Math.max(dp[i - 1][j], dp[i][j - 1]);
                dp[i][j] = Math.max(temp_price, prices[j - 1] - prices[i - 1]);
            }
        }
        return dp[days][days];
    }
}

class Solution_error_timeout_T121 {
    // 两行数组
    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days <= 1)
            return 0;
        int[][] dp = new int[2][days + 1];// 默认初始化为0
        for (int i = 1; i <= days; i++) {
            for (int j = i; j <= days; j++) {
                dp[i % 2][j] = Math.max(dp[Math.abs(i % 2 - 1)][j], prices[j - 1] - prices[i - 1]);// 纵向比较即可
            }
        }
        return dp[days % 2][days];
    }
}

class Solution_error_T121 {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (prices.length <= 1)
            return 0;
        int[] dp = new int[length];
        for (int i = 1; i < length; i++) {
            dp[i] = prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] + dp[i - 1] : dp[i - 1];
        }
        return dp[length - 1];
    }
}

class Solution_ouroftime_T121 {
    // 一行数组
    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days <= 1)
            return 0;
        int[] dp = new int[days];// 默认初始化为0
        for (int i = 0; i < days; i++) {
            // 初始化：把第一天算出来
            dp[i] = prices[i] - prices[0];
        }
        for (int i = 1; i < days; i++) {
            for (int j = i; j < days; j++) {
                int temp_max = Math.max(dp[j], dp[j - 1]);
                dp[j] = Math.max(temp_max, prices[j] - prices[i]);
            }
        }
        return dp[days - 1];
    }
}

class Solution_timeout {
    // 一行数组
    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days <= 1)
            return 0;
        int[] dp = new int[days + 1];// 默认初始化为0
        for (int i = 1; i <= days; i++) {
            for (int j = i; j <= days; j++) {
                int temp_max = Math.max(dp[j], dp[j - 1]);
                dp[j] = Math.max(temp_max, prices[j - 1] - prices[i - 1]);
            }
        }
        return dp[days];
    }
}

class Solution_T121 {
    // 一行数组：任意O(N^2)都是不可取的！
    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days <= 1)
            return 0;
        int[] dp = new int[days];// 默认初始化为0
        int pre_min = prices[0];
        for (int i = 1; i < days; i++) {
            if (pre_min > prices[i])
                pre_min = prices[i];
            dp[i] = Math.max(dp[i - 1], prices[i] - pre_min);
        }
        return dp[days - 1];
    }
}

class Solution_T121_2023 {
    // 这个dp数组应该是不需要的：每次都和前面一个比，所以只需要留一个即可
    public int maxProfit(int[] prices) {
        if (prices.length <= 1)
            return 0;
        int max_profit = 0;
        int pre_min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (pre_min > prices[i])
                pre_min = prices[i];
            max_profit = Math.max(max_profit, prices[i] - pre_min);
        }
        return max_profit;
    }
}

class Solution_T121_self_dp {
    /*
    如果不含冷冻期：解决不了啦
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
        dp[1][0] = Math.max(dp[0][0], dp[0][1] - prices[1]);
        dp[1][1] = Math.max(dp[0][1], prices[1] - dp[0][0]);
        for (int i = 2; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][1] + prices[i] + dp[i - 1][0]);
        }
        return dp[prices.length - 1][1];
    }
}