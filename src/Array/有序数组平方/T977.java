package Array.有序数组平方;

// 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

import java.util.Arrays;

public class T977 {
    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        int[] res = new Solution_T977_doubP().sortedSquares(nums);
        System.out.println(Arrays.toString(res));
    }
}

class Solution_T977_doubP {
    // 双指针：有序数组，有正有负：左右分别遍历，类似mergeSort
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1, cl = nums.length - 1;
        int[] sorted = new int[nums.length];
        while (left < right) {
            if (nums[left] * nums[right] > 0) {
                // 同号直接抄过去
                if (nums[left] > 0) {
                    // 大于零，顺着抄
                    while (right >= left)
                        sorted[cl--] = (int) Math.pow(nums[right--], 2);
                    return sorted;
                } else {
                    while (left <= right)
                        sorted[cl--] = (int) Math.pow(nums[left++], 2);
                    return sorted;
                }
            }
            sorted[cl--] = Math.abs(nums[left]) < nums[right] ? (int) Math.pow(nums[right--], 2) : (int) Math.pow(nums[left++], 2);
        }
        sorted[cl] = (int) Math.pow(nums[left], 2);
        return sorted;
    }
}
