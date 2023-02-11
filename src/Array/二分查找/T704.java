package Array.二分查找;

// 给定有序数组，要求查找target所在的位置，没有返回-1

public class T704 {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        System.out.println(new Solution_T704_again().search_2(arr, -1));
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

// 以下为二刷
class Solution_T704_again {
    // []
    public int search_1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid;
        while (left <= right) {
            // left==right依旧在target取值区间
            mid = (left + right) / 2;
            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    // [)
    public int search_2(int[] nums, int target) {
        int left = 0, right = nums.length; // 右端不计算在内：right向右一位
        int mid;
        while (left < right) {
            // left==right不在target取值区间
            mid = (left + right) / 2;
            if (nums[mid] > target)
                right = mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    // (]：由于a/2=(a+1)/2，所以左侧不可为开区间：否则(a,a+1]时若target=nums[a+1]则永远无法取到
    public int search_3(int[] nums, int target) {
        int left = -1, right = nums.length - 1; // 左端不计算在内：left向左一位
        int mid;
        while (left < right) {
            // left==right不在target取值区间
            mid = (left + right) / 2;
            if (nums[mid] > target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid;
            else
                return mid;
        }
        return -1;
    }
}