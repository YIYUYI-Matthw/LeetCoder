package Type09_DP;

/*
1. db table及其下标含义
2. 递推公式（dp[n] = ...）：由简入繁
3. 初始化dp数组
4. 确定遍历顺序（依赖关系，前面依赖后面/后面依赖前面
5. 举例推导dp数组（就是用递推公式写出前几项，运行看对不对）
 */
public class T70 {
    public static void main(String[] args) {
        System.out.println(new Solution_T70_package().climbStairs(5));
    }
}

class Solution_self_T70 {
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

class Solution_simply_T70 {
    public int climbStairs(int n) {
        if (n <= 2)
            return n;
        int pre_pre_s = 1;
        int pre_s = 2;
        int current = -1;
        for (int i = 3; i <= n; i++) {
            current = pre_pre_s + pre_s;
            pre_pre_s = pre_s;
            pre_s = current;
        }
        return current;
    }
}

class Solution_T70_package {
    public int climbStairs(int n) {
        int stairs = 2; // 台阶跨度最大为2
        // dp[i][j]：台阶数为2时选择跨度为[1-j]阶有几种走法
        int[][] dp = new int[n + 1][2 + 1];
        // 初始化
        for (int i = 0; i < stairs; i++)
            dp[0][i] = 1;
        // 填写dp
        for (int temp_tgt = 1; temp_tgt <= n; temp_tgt++) {
            for (int j = 1; j <= stairs; j++) {
                if (temp_tgt < j)
                    // 当前台阶跨度大于temp_tgt：只能选用前面的选择
                    dp[temp_tgt][j] = dp[temp_tgt][j - 1];
                else {
                    // 当前台阶跨度可选
                    dp[temp_tgt][j] = dp[temp_tgt][j - 1] + dp[temp_tgt - j][stairs];
                }
            }
        }
        return dp[n][stairs];
    }
}