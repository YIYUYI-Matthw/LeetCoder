package Type03_Hash;

import java.util.*;

public class T15 {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, -1, 2, -1};
        System.out.println(new Solution_T15_others().threeSum(nums));
    }
}

class Solution_T15_others {
    // 思路：固定一个、移动一个、找一个（hashSet）
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();

        if (nums.length < 3) {
            return resList;
        }

        // a<=b<=c：不会有重复组合出现：掌握细节：遍历时避免其和上一次数相同
        Arrays.sort(nums); // 快排：不占空间，费时间

        // 记录当次不满足要求的第二个数
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            // 全正：直接返回
            if (nums[i] > 0)
                break;
            // 去重
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            // 每次都维护一个新的set：这这样就不用考虑第一个数了
            set.clear();

            int first = nums[i];

            for (int j = i + 1; j < nums.length; j++) {
                int second = nums[j];
                int left = -(first + second);
                if (set.contains(left)) {
                    resList.add(new ArrayList<>(Arrays.asList(first, second, left)));
                    // 去重
                    while (j < nums.length - 1 && nums[j] == nums[j + 1]) j++;
                } else
                    set.add(second);
            }
        }
        return resList;
    }
}