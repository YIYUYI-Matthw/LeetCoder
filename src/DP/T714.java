package DP;

/*
给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
返回获得利润的最大值。
注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 */
public class T714 {

}

class Solution_714_self {
    /*
    【×】一个很朴素的想法：抓住所有超过fee的最大非下降边
    上升边：profit>fee：积累；else：记0
    下降边：profit=dp[i-1]，更新买入价
    关键：如何合并两条上升边：e.g. fee=3：① 1->7赚3；② 7->5赚-2(不计fee)；③ 5->10赚2：1->7+5->10赚5。
                                    合并：1->10=p(1->7)+p(7->5)+p(5-10)-fee=6 ==> 状态转移
     每天划分为两种状态：今天处于上升边、今天处于下降边：在趋势交汇处会同时处于两种状态：分别记录就好
     这里的上升边：指的是非下降边
     buy_val
     dp[0][:] = 0;
     dp[i][0] = delt<0 ? dp[i-1][0]:dp[i-1][0]+delt
     dp[i][1] = delt<0 ? dp[i-1][1]+prices[i]-prices[i-1] : dp[i-1][1]
     */
    public int maxProfit(int[] prices, int fee) {
        int buyFee = prices[0];
        int temp_Sell = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < temp_Sell) {
                if (temp_Sell - buyFee < fee)
                    // 亏了，不卖
                    buyFee = prices[i];
                else
                    profit += temp_Sell - buyFee;
            }
            temp_Sell = prices[i];
        }
        return 0;
    }
}

class Solution_T714_org_dp {
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}

class Solution_T714_org_greedy {
    public int maxProfit(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
            if (prices[i] + fee < buy)
                buy = prices[i] + fee;
        }
        return profit;
    }
}

class Solution_T714_others {
    public int maxProfit(int[] prices, int fee) {
        int res = 0;
        int min = prices[0];//最低价格
        for (int i = 1; i < prices.length; i++) {
            //最低价格买入，又更低就记录
            if (prices[i] < min) {
                min = prices[i];
            } else {
                //有利润卖掉，注意卖完再买回来，因为后面可能有更高的利润
                if (prices[i] - min - fee > 0) {
                    res += prices[i] - min - fee;
                    min = prices[i] - fee;//因为每次计算利润都要算fee，但这次已经算过了，所以这次买就少算一个fee
                }
            }
        }
        return res;
    }
}