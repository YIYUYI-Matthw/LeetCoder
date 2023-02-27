package Type01_Array.二分查找;

/*
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
要求Tn = O(log n)
 */

public class T35 {
    public static void main(String[] args) {
        int[] arr = {1, 3};
        System.out.println(new Solution_T35_self().searchInsert(arr, 2));
    }
}

class Solution_T35_self {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
            else
                return mid;
        }
        if (nums[mid] < target)
            return mid + 1;
        else
            return mid;
    }
}
