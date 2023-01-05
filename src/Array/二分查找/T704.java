package Array.二分查找;

// 查找target所在的位置，没有返回-1

public class T704 {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        System.out.println(new Solution_T704_self().search(arr, 13));
    }
}

// 重点在于考虑清楚搜索空间是[]还是[)
class Solution_T704_self {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
