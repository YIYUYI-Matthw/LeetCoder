package DP;

import java.util.Arrays;

public class T115 {
    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";
        System.out.println(new Solution_T115_self().numDistinct(s, t));
    }
}

class Solution_T115_self {
    /*
    一开始枚举找思路：
        rab和rabb：
            ① ra一定，b和b1、b2相比：两种
            ② r一定，
                * a=a1固定：b和b1、b2相比：两种
                * a!=b1
                * a!=b2
                两种
            ③ r=r1固定：
                * a=a1固定：b和b1、b2相比：两种
                ...
        综上，当通过固定前面字符、变化后面字符来枚举解题时，位于后方的字符多次比较，这个重复的操作可以记录下来
        ： dp[i][j]：t中第i个子父在s[j:]出现的次数
        dp[i][j] = dp[i-1][j-1]+dp[i][j-1], t[i]==s[j]
        dp[i][j] = dp[i][j-1], otherwise
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length()][s.length()];
        for (int i = 0; i < s.length(); i++)
            if (t.charAt(0) == s.charAt(i))
                Arrays.fill(dp[0], i, s.length(), 1 + dp[0][i]);// +dp[0][i]：累计
        for (int i = 1; i < t.length(); i++)
            for (int j = i; j < s.length(); j++)
                dp[i][j] = dp[i][j - 1] + (t.charAt(i) == s.charAt(j) ? dp[i - 1][j - 1] : 0);
        return dp[t.length() - 1][s.length() - 1];
    }
}