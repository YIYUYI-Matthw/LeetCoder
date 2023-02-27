package Type09_DP;

/*
有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 */
public class T1049 {
    public static void main(String[] args) {
        int[] stones = {1, 2, 4, 7, 8, 1};
        System.out.println(new Solution_T1049_self().lastStoneWeightII(stones));
    }
}

class Solution_T1049_self {
    public int lastStoneWeightII(int[] stones) {
        /*
        找出几块石头：使得加起来距离sum/2越接近越好
         */
        // 特殊情况
        if (stones.length == 1)
            return stones[0];
        if (stones.length == 2)
            return Math.abs(stones[0] - stones[1]);

        int sum = 0;
        for (int stone : stones)
            sum += stone;

        // 初始化
        boolean[][] dp = new boolean[stones.length][sum / 2 + 1];
        if (stones[0] <= sum / 2)
            dp[0][stones[0]] = true;

        for (int i = 1; i < stones.length; i++) {
            for (int target = 1; target <= sum / 2; target++) {
                if (target < stones[i]) {
                    dp[i][target] = dp[i - 1][target];
                    continue;
                }
                if (target == stones[i]) {
                    dp[i][target] = true;
                    continue;
                }
                dp[i][target] = dp[i - 1][target] || dp[i - 1][target - stones[i]];
            }
        }

        for (int i = sum / 2; i > 0; i--)
            if (dp[stones.length - 1][i])
                return sum - 2 * i;

        return -1;
    }
}