package DP;

import java.util.Arrays;

/*
给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。

e.g.
输入: "sea", "eat"
输出: 2
解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 */
public class T583 {
    public static void main(String[] args) {
        String word1 = "a";
        String word2 = "b";
        System.out.println(new Solution_T583_self().minDistance(word1, word2));
    }
}

class Solution_T583_self {
    /*
    dp[i][j]：A[:i]和B[:j]相等需要操作的步数
    dp[i][j] = min(dp[i-1][j], dp[i][j-1])+1, A[i]!=B[j]
             = dp[i-1][j-1], A[i]=B[j]
     */
    public int minDistance(String word1, String word2) {
        int wl1 = word1.length();
        int wl2 = word2.length();
        int[][] dp = new int[wl1 + 1][wl2 + 1];
        for (int i = 0; i <= wl1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= wl2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= wl1; i++) {
            for (int j = 1; j <= wl2; j++) {
                if (word1.charAt(i - 1) != word2.charAt(j - 1))
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                else
                    dp[i][j] = dp[i - 1][j - 1];
            }
        }
        return dp[wl1][wl2];
    }
}

// 这个解法很好：转化为其他问题：最长子串长度
class Solution_T583_others {
    // ->> 转化为找相同子串的长度
    public int minDistance(String word1, String word2) {
        char[] cs1 = word1.toCharArray(), cs2 = word2.toCharArray();
        int n = word1.length(), m = word2.length();
        int[][] f = new int[n + 1][m + 1];
        // 通常会习惯性往字符串头部追加一个空格，以减少边界判断（使下标从 1 开始，并很容易构造出可滚动的「有效值」）。但实现上，不用真的往字符串中最佳空格，只需在初始化动规值时假定存在首部空格，以及对最后的 LCS 长度进行减一操作即可。
        for (int i = 0; i <= n; i++) {
            f[i][0] = 1;
        }
        for (int j = 0; j <= m; j++) {
            f[0][j] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (cs1[i - 1] == cs2[j - 1]) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
                }
            }
        }
        int max = f[n][m] - 1;// 减去哨兵空格
        return n - max + m - max;
    }
}

