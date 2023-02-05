package DP;

/*
给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 */
public class T718 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 2, 1};
        int[] nums2 = new int[]{3, 2, 1, 4, 7};
        System.out.println(new Solution_T718_org().findLength(nums1, nums2));
    }
}

class Solution_T718_self_fail {
    /*
    分析：一个数组A如果在另一个数组B中不存在，那么包含A的其他数组也不存在于B中
    第一想法是双指针+内部for循环（substring判断是否存在）：但是复杂度有点高：
        印象中有一个for(while复杂度还是O(n)的
     */
    public int findLength(int[] nums1, int[] nums2) {
        // 这个方法不像是DP呀：8行，代价太大了
        /*
        暴力解法：org
        暴力解法，即枚举数组 A 中的起始位置 i 与数组 B 中的起始位置 j，然后计算 A[i:] 与 B[j:] 的最长公共前缀 k。最终答案即为所有的最长公共前缀的最大值。
         */
        int max_len = 0;
        int temp_i;
        int temp_len = 0;
        for (int i = 0; i < nums1.length; i++) {
            temp_i = i;
            for (int j = 0; j < nums2.length; j++) {
                if (temp_i < nums1.length) {
                    if (nums1[temp_i++] == nums2[j++])
                        temp_len++;
                    else {
                        max_len = Math.max(max_len, temp_len);
                        temp_len = 0;
                        temp_i = i;
                    }
                }
            }
        }
        return -1;
    }
}

// TODO：没做出来
class Solution_T718_org {
    /*
    思路：在于优化暴力解法中的比较次数
    e.g. 123和124中，双方的2比较了三次，优化的入手点就是这里
        亦即除了第一次之外，另外两次都希望在比较之前能够知道结果，则顺序就是倒序了
     dp[i][j]：数组A[i:]和数组B[j:]的最长前缀（必须分别从A[i]和B[j]算起）
        当A[i]=B[j]时，A[i:]和B[j:]的最长前缀等于dp[i+1][j+1]+1；否则dp[i][j]=0
     */
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];// 外围加一圈0，充当最开始的dp[i+1][j+1]
        int max_len = 0;
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j])
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                else
                    dp[i][j] = 0;
                max_len = Math.max(max_len, dp[i][j]);
            }
        }
        return max_len;
    }
}

class Solution_T718_others {
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            if (nums1[i] == nums2[0]) {
                dp[i][0] = 1;
                res = Math.max(res, dp[i][0]);
            }
        }
        for (int j = 0; j < n; j++) {
            if (nums2[j] == nums1[0]) {
                dp[0][j] = 1;
                res = Math.max(res, dp[0][j]);
            }

        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}