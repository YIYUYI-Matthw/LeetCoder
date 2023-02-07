package DP;

/*
给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。

你可以对一个单词进行如下三种操作：
插入一个字符
删除一个字符
替换一个字符
 */

public class T72 {
    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(new Solution_T72_self().minDistance(word1, word2));
    }
}

class Solution_T72_self {
    /*
    dp[i][j]：A[:i]变成B[:j]所需最少操作数
    dp[i][j] = min(dp[i-1][j-1]+1, dp[i][j-1]+1, dp[i-1][j]+1+1), A[i]!=B[j]：三个分别对应于修改、插入、删除操作数
             = mina(dp[i-1][j-1], dp[i][j-1]+1, dp[i][j-1]+1), A[i]==B[j]：三个分量对应于：当次不做改动、插入、删除操作数
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        int temp_val = 0;
        // 初始化：dp(:,0)和dp(0,:)
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        // 计算dp
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                temp_val = Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1);
                dp[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ?
                        Math.min(dp[i - 1][j - 1], temp_val) : Math.min(dp[i - 1][j - 1] + 1, temp_val);
            }
        }
        // return
        return dp[word1.length()][word2.length()];
    }
}
