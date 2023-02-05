package DP;

import java.util.Arrays;

/*
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

输入：text1 = "abcde", text2 = "ace"
输出：3
解释：最长公共子序列是 "ace" ，它的长度为 3 。
 */
public class T1143 {
    public static void main(String[] args) {
        System.out.println(new Solution_T1143_self().longestCommonSubsequence("abcde", "ace"));
    }
}

class Solution_T1143_self {
    /*
    想法：
    * 首先是暴力穷举
        【我又想错了】由于公共子序列的特性，这里不用考虑重复出现的问题
    * dp[i][j]：A[:i]和B[:j]的最长~的长度
            = dp[i-1][j-1] + 1, A[i]==B[j]
            = max(dp[i][j-1], dp[i-1][j]), otherwise
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int i = 0; i < text2.length(); i++) {
            if (text2.charAt(i) == text1.charAt(0))
                Arrays.fill(dp[0], i, text2.length(), 1);
        }
        for (int i = 0; i < text1.length(); i++)
            if (text1.charAt(i) == text2.charAt(0))
                while (i < text1.length())
                    dp[i++][0] = 1;
        for (int i = 1; i < text1.length(); i++) {
            for (int j = 1; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]); // 不相等则照抄对角线
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }
}