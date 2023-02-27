package Type09_DP;

import java.util.Arrays;

/*
给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 */
public class T516 {
    public static void main(String[] args) {
        String str = "cbbd";
        System.out.println(new Solution_T516_self().longestPalindromeSubseq(str));
    }
}

class Solution_T516_self {
    // 思路与T647一致，只是dp[i][j]变为(j-i+1)，最后统计最长的那个
    // 感想：在所有解决问题的方法中，dp算是最简单明了的一种
    public int longestPalindromeSubseq(String s) {
        // TODO：待续：还没做完
        int str_len = s.length();
        int max_cnt = 0;
        int[][] dp = new int[str_len][str_len];
        // 初始化
        for (int i = 0; i < str_len; i++)
            Arrays.fill(dp[i], 0, i + 1, 1);
        // 填写dp
        for (int i = str_len - 2; i >= 0; i--) {// 从str_len-2开始：防止数组越界
            for (int j = i + 1; j < str_len; j++) {
                if (s.charAt(i) == s.charAt(j))
                    if (j - i == 1)
                        dp[i][j] = 2;
                    else
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
            }
        }
        return dp[0][str_len - 1];
    }
}

class Solution_T5116_commonSub {
    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length + 1][length + 1];
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length; j++) {
                if (s.charAt(i - 1) == s.charAt(length - j))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[length][length];
    }
}

// TODO：除了dp，一定有更简单的做法

// ⭐ 在讨论区看到一个很精妙的解释：子串str和str.reverse的公共子序列一定是回文串，而最长公共子序列就是最长回文子串：子序列：不改变顺序，且删除若干字符后剩下的