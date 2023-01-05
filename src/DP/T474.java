package DP;

/*
给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 */
public class T474 {
    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        System.out.println(new Solution_T474_self().findMaxForm(strs, 5, 3));
    }
}

class Solution_T474_self {
    /*
    关键：最多有m个0和n个1，而不是刚好
    首先还是统计下每个串有多少个0、1吧
    然后：0-1背包+爬楼梯

    dp数组：
        1. 构造dp[i][j][k]：前(i+1)个数组能组成0_l<=j&1_l<=k的最长序列
        2. dp[i][j][k] =
            ① 0, i=0 & (j<num[i][0] || k<num[i][1]
            ② 1, i=0 & (j>=num[i][0] && k>=num[i][1]
            ③ dp[i-1][j][k], i>0 & (j<num[i][0] || k<num[i][1]
            ③ max(
                    dp[i-1][j][k],
                    dp[i-1][j-num[i][0]][k-num[i][1]]
                    ), otherwise

     Ps：查了一个点没有考虑到：滚动数组
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // 统计下0和1的个数
        int[][] str_seq = new int[strs.length][2];
        for (int i = 0; i < strs.length; i++) {
            for (char ch : strs[i].toCharArray()) {
                if (ch == '0')
                    str_seq[i][0] += 1;
                else
                    str_seq[i][1] += 1;
            }
        }
        // 初始化dp
        int[][][] dp = new int[strs.length][m + 1][n + 1];
        for (int j = 0; j < m + 1; j++) {
            for (int k = 0; k < n + 1; k++) {
                dp[0][j][k] = (j >= str_seq[0][0] & k >= str_seq[0][1]) ? 1 : 0;
            }
        }
        // 填写dp
        for (int i = 1; i < strs.length; i++) {
            for (int j = 0; j < m + 1; j++) {
                for (int k = 0; k < n + 1; k++) {
                    if (j < str_seq[i][0] || k < str_seq[i][1])
                        dp[i][j][k] = dp[i - 1][j][k];
                    else
                        dp[i][j][k] = Math.max(
                                dp[i - 1][j][k],
                                1 + dp[i - 1][j - str_seq[i][0]][k - str_seq[i][1]]);
                }
            }
        }
        return dp[str_seq.length - 1][m][n];
    }
}