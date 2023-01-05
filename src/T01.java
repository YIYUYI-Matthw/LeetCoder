import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class T01 {
    public static int[] nums = new int[]{0, 4, 3, 0};
    public static int target = 0;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution_stand_T01().twoSum(nums, target)));
    }
}

class Solution_self_T01 {
    public int[] twoSum(int[] nums, int target) {
        int[] two = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    two[0] = i;
                    two[1] = j;
                    return two;
                }
            }
        }
        return two;
    }
}

class Solution_stand_T01 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> my_map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int left = target - nums[i];
            if (my_map.containsKey(left)) {
                return new int[]{i, my_map.get(left)};
            }
            my_map.put(nums[i], i);
        }
        throw new RuntimeException("no numbers find"); // missing return...
    }
}