package DP;

import java.util.Arrays;

public class T338 {
    public static void main(String[] args) {
        int[] res = new Solution_T38().countBits(5);
        System.out.println(Arrays.toString(res));
    }
}

class Solution_T38 {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i] = dp[i >> 1] + (i % 2 == 0 ? 0 : 1);
        }
        return dp;
    }
}