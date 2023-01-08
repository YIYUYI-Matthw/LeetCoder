package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
e.g.
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。

输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
 */
public class T139 {
    public static void main(String[] args) {
        List<String> wordDict = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        String s = "catsanddog";
        System.out.println(new Solution_T139_self().wordBreak(s, wordDict));
    }
}

class Solution_T139_self {
    /*
    完全背包
    每次匹配后n位：substring
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int str_len = s.length();
        // dp[i]：单词集是否能组成序列s
        boolean[] dp = new boolean[str_len + 1]; // 想法：String本身是个类，所以是length()这种函数性质的调用方法，而数组等复杂数据结构则是length属性
        // 初始化
        dp[0] = true;
        /*填写dp
        dp[i] = ① true, i=0
                ② dp[i-word.length] & word==s.substring(s.length()-word.length, s.length()), i>0
         */
        for (int i = 1; i <= str_len; i++) {
            for (String word : wordDict) {
                if (i < word.length())
                    continue;
                if (s.startsWith(word, i - word.length()) && dp[i - word.length()]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[str_len];
    }
}
