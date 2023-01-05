package GreedyA;

import java.util.Arrays;

/*
假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 */
public class T455 {
    public static void main(String[] args) {
        int[] g = {8, 7, 6, 5};
        int[] s = {5};
        System.out.println(new Solution_T455_self().findContentChildren(g, s));
    }
}

class Solution_T455_self {
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0)
            return 0;
        int num = 0;
        int[] g_sorted = mergeSort(g); // Arrays.sort为快排：void函数：大多数为O(nlogn)，少数退化为O(n^2)，不增加空间复杂度
        int[] s_sorted = mergeSort(s); // 归并：稳定在O(nlogn)，需要额外空间：https://zhuanlan.zhihu.com/p/95080265
        // g和s递增？
        for (int g_i = 0, s_i = 0; g_i < g_sorted.length && s_i < s_sorted.length; ) {
            if (g_sorted[g_i] <= s_sorted[s_i]) {
                g_i++;
                num++;
            }
            s_i++;
        }
        return num;
    }

    public int[] mergeSort(int[] nums) {
        if (nums.length == 1)
            return nums;
        int mid = nums.length / 2;
        int[] sorted_l = mergeSort(Arrays.copyOfRange(nums, 0, mid)); // [)
        int[] sorted_r = mergeSort(Arrays.copyOfRange(nums, mid, nums.length));
        return merge(sorted_l, sorted_r);
    }

    public int[] merge(int[] nums_l, int[] nums_r) {
        int i = 0, j = 0, sort_i = 0;
        int[] sorted = new int[nums_l.length + nums_r.length];
        while (i < nums_l.length && j < nums_r.length) {
            if (nums_l[i] <= nums_r[j])
                sorted[sort_i++] = nums_l[i++];
            else
                sorted[sort_i++] = nums_r[j++];
        }
        while (i < nums_l.length)
            sorted[sort_i++] = nums_l[i++];
        while (j < nums_r.length)
            sorted[sort_i++] = nums_r[j++];
        return sorted;
    }
}