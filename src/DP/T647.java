package DP;

import java.util.Arrays;

/*
给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
回文字符串 是正着读和倒过来读一样的字符串。
子字符串 是字符串中的由连续字符组成的一个序列。
具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

e.g.
aaa：6：a, a, a, aa, aa, aaa
 */
public class T647 {
    public static void main(String[] args) {
        String sub_str = "aaaaa";
        System.out.println(new Solution_T647_self().countSubstrings(sub_str));
    }
}

class Solution_T647_self {
    /*
    DP：最优子结构->回文串的子串和回文串的关系？
    回文串去掉收尾依旧是回文串：串.len>=3
    1. 快慢指针：快指针向后移动直到“慢-快”为非回文串，则移动慢指针：这样找不全
    2. DP：dp[i][j]：A[i:j+1]的回文串有多少
    3. DP：dp[i][j]：A[i:j+1]是否为回文串：这个比2好理解、但是多一步计数过程：√
        ① len=1：√
        ② len=2：相等则√ -> dp[i][j] = i > j & A[i] == A[j] -> len=1同样适用
        ③ len>=3：dp[i][j] = A[i]==A[j]? dp[i+1][j-1]: False = A[i]==A[j] & dp[i+1][j-1]
            dp[1][4] = A[1]==a[4]? dp[2][3]: False，则：
                * 从A[-1]开始倒序填写dp
                * 由于i需要<=j，所以只需要上半三角
         综上：
            dp[i][j] = A[i] == A[j] & dp[i+1][j-1], 0 <= j < i <= len
                     = True, 0 <= j=i <=len
     */
    public int countSubstrings(String s) {
        int str_len = s.length();
        int re_cnt = 0;
        boolean[][] dp = new boolean[str_len][str_len];
        // 初始化：下三角为true
        for (int i = 0; i < str_len; i++)
            Arrays.fill(dp[i], 0, i + 1, true);
        // 填写dp：倒序遍历
        for (int i = str_len; i >= 0; i--) {
            for (int j = i + 1; j < str_len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) & dp[i + 1][j - 1];
                if (dp[i][j])
                    re_cnt += 1;
            }
        }
        return re_cnt + str_len; // 这个str_len是对角线元素
    }

    // TODO：官方解法似乎比较新颖：https://leetcode.cn/problems/palindromic-substrings/solutions/379987/hui-wen-zi-chuan-by-leetcode-solution/
}