package Type07_Back_track;

import java.util.*;

/*
给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
个人：感觉这个东西可以用DP来做
 */
public class T46 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(Arrays.toString(new Solution_T46_self().permute(nums).toArray()));
    }
}

class Solution_T46_self {
    /*
    1. 每次回溯后，startIdx不变：如果AI->取不完整
    2. 每层（上层included）维护一个used数组，记录占用元素
    3. 每次回溯后的首个加入元素不能使用回溯去掉的元素
     */
    List<List<Integer>> res_list = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    int bt_num = 11; // num取值-10~10

    public List<List<Integer>> permute(int[] nums) {
        back_track(nums);
        return res_list;
    }

    public void back_track(int[] nums) {
        if (temp.size() == nums.length) {
            res_list.add(new ArrayList<>(temp));
            return;
        }
        HashSet<Integer> used = new HashSet<>(temp); // 把当前temp有的都排除掉
        for (int num : nums) {
            if (used.contains(num) || bt_num == num)
                continue;
            bt_num = 11;
            temp.add(num);
            used.add(num);
            back_track(nums);
            temp.remove(temp.size() - 1);
            bt_num = num;
        }
    }
}
/*
方法二：
startIdx每次+1，但是数组要做swap：将被选中的元素和第一个未被选中的元素做交换：具体看官方题解
 */