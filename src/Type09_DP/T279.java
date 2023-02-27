package Type09_DP;

import java.util.Arrays;

/*
给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
e.g.
输入：n = 13
输出：2
解释：13 = 4 + 9
 */
public class T279 {
    public static void main(String[] args) {
        System.out.println(new Solution_T279_simplify().numSquares(12));
    }

}

class Solution_T279_self {
    /*
    就是说，一个数是另外一串数的平方和
    转化：n=amount, 序列=coins=[1,4,9,..., (amount)^{1/2}]
    不同点：这里没有-1的情况，最差就是全部用1加起来
     */
    public int numSquares(int n) {
        int max_coin = (int) Math.floor(Math.sqrt(n));
        // dp[i][j]：找零钱最少硬币组合
        int[][] dp = new int[max_coin + 1][n + 1];
        // 初始化
        for (int i = 1; i <= n; i++)
            dp[1][i] = 1 + dp[1][i - 1]; // 第一个coin就是1，所以不用判断了
        /*
        填充dp：不考虑i=0的情况
        dp[i][j] = ① 0, i>0 & j=0，√
                   ② dp[i-1][j], i>0 & j>0 & j<i^2
                   ③ min(dp[i-1][j], 1+dp[i][j-i^2]), i>0 & j>0 & j>=i^2
         */
        for (int i = 2; i <= max_coin; i++) { // i=1时就是coins[0]
            for (int j = 1; j <= n; j++) {
                if (j < i * i)
                    dp[i][j] = dp[i - 1][j];
                else {
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - i * i]);
                    //dp[i][j] = 1 + dp[i][j - i * i]; // 用一个大的肯定会减少数目
                }
            }
        }
        return dp[max_coin][n];
    }
}

class Solution_T279_simplify {
    // 滚动数组
    /*
    就是说，一个数是另外一串数的平方和
    转化：n=amount, 序列=coins=[1,4,9,..., (amount)^{1/2}]
    不同点：这里没有-1的情况，最差就是全部用1加起来
     */
    public int numSquares(int n) {
        int max_coin = (int) Math.floor(Math.sqrt(n));
        // dp[n]：零钱组合为n的最少硬币数
        int[] dp = new int[n + 1];
        // 初始化
        dp[0] = 0;
        Arrays.fill(dp, 1, dp.length, n); // 开始前刷新下，防止0被选作min
        // 填充dp数组
        for (int i = 1; i <= max_coin; i++) { // i=1时就是coins[0]
            for (int j = i * i; j <= n; j++) { // 从i^2开始算
                dp[j] = Math.min(dp[j], 1 + dp[j - i * i]); // 【错误想法】用一个大的coin肯定比不用大的硬币总量要少：不一定嗷，试下就知道了
            }
        }
        return dp[n];
    }
}