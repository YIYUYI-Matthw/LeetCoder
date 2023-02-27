package Type03_Hash;

import java.util.HashMap;

/*
给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 */
public class T454 {
    // 感觉上这个题用DP也能做，就是麻烦点：dp[?, 4n]
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{-2, -1};
        int[] nums3 = new int[]{-1, 2};
        int[] nums4 = new int[]{0, 2};
        System.out.println(new Solution_T454_again().fourSumCount_m2(nums1, nums2, nums3, nums4));
    }
}

class Solution_T454_again {
    /*
    n1[0]找剩下的三个维护的hash表中是否含有-n1[0]
    法一：暴力解法
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int cnt = 0;
        for (int n1 : nums1) {
            for (int n2 : nums2) {
                for (int n3 : nums3) {
                    for (int n4 : nums4) {
                        if (n1 + n2 + n3 + n4 == 0)
                            cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    /*
    法二：分组相加
     */
    public int fourSumCount_m2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> mapPre = new HashMap<>();
        int cnt = 0;
        for (int n1 : nums1) {
            for (int n2 : nums2) {
                mapPre.put(n1 + n2, mapPre.getOrDefault(n1 + n2, 0) + 1);
            }
        }
        for (int n1 : nums3) {
            for (int n2 : nums4) {
                cnt += mapPre.getOrDefault(-n1 - n2, 0);
            }
        }
        return cnt;
    }
}
