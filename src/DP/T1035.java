package DP;

/*
在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
* nums1[i] == nums2[j]
* 绘制的直线不与任何其他连线（非水平线）相交。
 */
public class T1035 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 5, 1, 2, 5};
        int[] nums2 = new int[]{10, 5, 2, 1, 5, 2};
        System.out.println(new Solution_T1035_self().maxUncrossedLines(nums1, nums2));
    }
}

class Solution_T1035_self {
    /*
    个人感觉这个题和T1143：最长公共子序列基本一致
    dp[i][j]：A[:i]和B[:j]有多少重复xxx
    dp[i][j] = dp[i-1][j-1]+1, A[i]==B[j]
             = max(dp[i][j-1], dp[i-1][j]), otherwise
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                if (nums1[i - 1] == nums2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[nums1.length][nums2.length];
    }
}