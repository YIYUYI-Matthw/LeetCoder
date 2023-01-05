package GreedyA;

/*
给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 */
public class T376 {
    /*
    删除元素：当出现连续值时，删除（其实就是不计数）最高峰之前的上升序列/最低谷之前的下降序列
    峰&谷：官方-https://leetcode.cn/problems/wiggle-subsequence/solutions/518296/bai-dong-xu-lie-by-leetcode-solution-yh2m/
     */
    // 随想录：https://leetcode.cn/problems/wiggle-subsequence/discussion/comments/1011792
    // 评论题解：精妙：https://leetcode.cn/problems/wiggle-subsequence/discussion/comments/5616
    public static void main(String[] args) {
        int[] nums = {1, 7, 4, 9, 2, 5};
        System.out.println(new Solution_T376_self().wiggleMaxLength(nums));
    }
}

class Solution_T376_self {
    int max_length = 1;
    boolean isIncPre;

    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        // 确定开始状态：跳过相等的情况
        int idx = 1;
        while (idx < nums.length && nums[idx] == nums[idx - 1])
            idx++;
        if (idx == nums.length)
            return max_length;
        isIncPre = nums[idx] > nums[idx - 1];
        max_length++;
        while (idx < nums.length) {
            if (isIncPre)
                if (nums[idx] < nums[idx - 1]) {
                    max_length++;
                    isIncPre = false;
                }
            if (!isIncPre)
                if (nums[idx] > nums[idx - 1]) {
                    max_length++;
                    isIncPre = true;
                }
            idx++;
        }
        return max_length;
    }
}

class Solution_T376_miaoa {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return Math.max(up, down);
    }
}