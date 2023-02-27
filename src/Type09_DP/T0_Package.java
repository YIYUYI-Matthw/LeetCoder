package Type09_DP;

/*
这里比较0-1背包、完全背包-排列、完全背包-组合
（不使用滚动数组）
滚送数组：当在二维dp中，外循环取值需单调，如for(int coin : coins)中各coin应该单调，否则无法使用滚动数组
 */
public class T0_Package {
    public static void main(String[] args) {
        int[] nums_S = new int[]{1, 2, 2, 3};
        int[] nums_C = new int[]{1, 2, 3};
        int target = 4;
        System.out.println(new Solution_S_exist().isExist(nums_S, target)); // true
        System.out.println(new Solution_S_num().howMany(nums_S, target)); // 2
        System.out.println(new Solution_C_set().howMany(nums_C, target)); // 4
        System.out.print(new Solution_C_seq().howMany(nums_C, target)); // 7
    }
}

/*
0-1背包-有没有：背包
给定nums[?]、target，要求确定是否存在nums的子集组合的sum等于target
e.g.
有nums=[1,2,2,3]，target=4，结果为true：1-3或者2-2都满足条件
 */
class Solution_S_exist {
    public boolean isExist(int[] nums, int target) {
        // dp[i][j]：在范围nums[0-i]中，是否存在子集sum为j：外循环为coin
        boolean[][] dp = new boolean[nums.length][target + 1];
        // 初始化
        dp[0][0] = true;
        if (nums[0] <= target)
            dp[0][nums[0]] = true;
        /* 填写dp
        dp[i][j] = ① true, i=0 & target < nums[0]，√
                   ② false, i=0 & target >= nums[0]，√
                   ③ dp[i-1][j], i>0 & j < nums[i]
                   ④ dp[i-1][j] || dp[i-1][j-nums[i]], i>0 & j>nums[i]
         */
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
            }
        }
        return dp[nums.length - 1][target];
    }
}

/*
0-1背包-有几种：背包
给定nums[?]、target，要求计算有几种nums的子集组合的sum等于target
e.g.
有nums=[1,2,2,3]，target=4，结果为2：【1-3】或者【2-2】都满足条件
 */
class Solution_S_num {
    public int howMany(int[] nums, int target) {
        // dp[i][j]：在范围nums[0-i]中，有几种子集sum为j的【组合】：外循环为coin
        int[][] dp = new int[nums.length][target + 1]; // boolean -> int
        // 初始化
        dp[0][0] = 1; // true -> 1
        if (nums[0] <= target)
            dp[0][nums[0]] = 1; // true -> 1
        /* 填写dp
        dp[i][j] = ① 0, i=0 & target < nums[0]，√
                   ② 1, i=0 & target >= nums[0]，√
                   ③ dp[i-1][j], i>0 & j < nums[i]
                   ④ dp[i-1][j] + dp[i-1][j-nums[i]], i>0 & j>=nums[i]
         */
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]]; // || -> +
            }
        }
        return dp[nums.length - 1][target];
    }
}

/*
完全背包-组合：找零钱
给定nums[?]、target，要求计算有几种nums的子集组合的sum等于target：每个num可重用
e.g.
有nums=[1,2,3]，target=4，结果为4：【1111】【112】【22】【13】
solution-e.g.
    dp[2][4]：使用1、2达到4的组合数
        = 最后一次使用2的组合数 + 最后一次不使用2的组合数
        = 使用1、2达到4-2=2的组合数-dp[2][4-2] + 使用1、2达到4且最后不使用2的组合数
        = dp[2][4-2] + 使用1达到4的组合数
        = dp[2][2] + dp[1][4]
 */
class Solution_C_set {
    public int howMany(int[] nums, int target) {
        // dp[i][j]：在范围nums[0-i]中，有几种子集sum为j的【组合】：外循环为coin
        int[][] dp = new int[nums.length][target + 1]; // boolean -> int
        // 初始化
        dp[0][0] = 1;
        for (int temp_target = 1; temp_target <= target; temp_target++)
            if (temp_target >= nums[0])
                dp[0][temp_target] = dp[0][temp_target - nums[0]];
        /* 填写dp
        dp[i][j] = ① 0, i=0 & target < nums[0]，√
                   ② 1, i=0 & target >= nums[0]，√
                   ③ dp[i-1][j], i>0 & j < nums[i]
                   ④ dp[i-1][j] + dp[i][j-nums[i]], i>0 & j>=nums[i]
         */
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - nums[i]]; // || -> +
            }
        }
        return dp[nums.length - 1][target];
    }
}

/*
完全背包-排列：走楼梯
给定nums[?]、target，要求计算有几种nums的子集【排列】的sum等于target：每个num可重用
排列不同视作不同解法
e.g.
有nums=[1,2,3]，target=4，结果为7：【1111】【112】【121】【211】【22】【13】【31】
 */
class Solution_C_seq {
    public int howMany(int[] nums, int target) {
        // dp[i][j]：在范围nums[0-j]中，有几种子集sum为i的【组合】：外循环为target：每次都可以考虑所有的集合元素
        int[][] dp = new int[target + 1][nums.length]; // boolean -> int
        // 初始化
        for (int i = 0; i < nums.length; i++)
            dp[0][i] = 1; // target为0时，0-j都有1种组合：啥也不选
        /* 填写dp
        dp[i][j] = ① 1, i=0, √
                   ② dp[i][j-1], i>0 & i<nums[j], j>0
                   ③ 0, i>0 & i<num[j], j=0
                   ④ dp[i-nums[j]][-1] + dp[i][j-1], i>0 & i>=nums[i], j>0
                   ⑤ dp[i-nums[j]][-1], i>0 & i>=nums[i], j=0
         */
        for (int temp_target = 1; temp_target <= target; temp_target++) {
            for (int j = 0; j < nums.length; j++) {
                if (temp_target < nums[j])
                    dp[temp_target][j] = j > 0 ? dp[temp_target][j - 1] : 0; // j<1说明前面没有更多选择：0，下同理
                else
                    dp[temp_target][j] = dp[temp_target - nums[j]][nums.length - 1] +
                            (j > 0 ? dp[temp_target][j - 1] : 0);
            }
        }
        return dp[target][nums.length - 1];
    }
}