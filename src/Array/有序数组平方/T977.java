package Array.有序数组平方;

// 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

import java.util.Arrays;
import java.util.stream.Collectors;

public class T977 {
    public static void main(String[] args) {
        int[] nums = {-5, -3, -2, -1};
        int[] res = new Solution_T977_again_ori().sortedSquares(nums);
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

class Solution_T977_again {
    /*
    双指针
    有序数组，有正有负，两边平方最大
    双指针首尾各一
     */
    public int[] sortedSquares(int[] nums) {
        if (nums[0] >= 0) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = (int) Math.pow(nums[i], 2);
            }
            return nums;
        }

        int left = 0, right = nums.length - 1, idx = nums.length - 1;
        int[] sorted = new int[nums.length];
        while (left <= right)
            sorted[idx--] = Math.pow(nums[left], 2) < Math.pow(nums[right], 2) ?
                    (int) Math.pow(nums[right--], 2) : (int) Math.pow(nums[left++], 2);
        return sorted;
    }
}

class Solution_T977_again_ori {
    /*
    双指针：原地修改：太麻烦：绝对值非有序
    有序数组，有正有负，两边平方最大
    双指针首尾各一
     */
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int left2 = (int) Math.pow(nums[left], 2);
            int right2 = (int) Math.pow(nums[right], 2);
            if (left2 > right2) {
                nums[left] = nums[right];
                nums[right--] = left2;
            } else {
                nums[right--] = right2;
            }
        }
        return nums;
    }
}