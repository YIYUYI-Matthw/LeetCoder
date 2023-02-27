package Type09_DP;

/*
给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
返回 你可以获得的最大乘积 。
2<=n<=58
 */
public class T343 {
    public static void main(String[] args) {
        System.out.println(new Solution_T343_self().integerBreak(15));
    }
}

class Solution_T343_self {
    public int integerBreak(int n) {
        int[] maxes = new int[n + 1];
        int temp_max;
        maxes[1] = 1; // n=1作为分量参与计算
        maxes[2] = 2; // n=2时，max=1，n=1时不成立
        for (int i = 3; i <= n; i++) {
            temp_max = i; // 记录为自身大小
            for (int j = 1; j <= i / 2; j++) {
                if (maxes[j] * maxes[i - j] > temp_max)
                    temp_max = maxes[j] * maxes[i - j];
            }
            maxes[i] = temp_max;
        }
        temp_max = 0; // 最后一个数不算自己
        for (int j = 1; j <= n / 2; j++) {
            if (maxes[j] * maxes[n - j] > temp_max)
                temp_max = maxes[j] * maxes[n - j];
        }
        return temp_max;
    }
}

class Solution_T343_others {
    public int integerBreak(int n) {
        // 还是有难度的
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i - j; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}