package Type08_GreedyA;

/*
给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
你的目标是使用最少的跳跃次数到达数组的最后一个位置。
假设你总是可以到达数组的最后一个位置。
 */
public class T45 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1};
        System.out.println(new Solution_T45_forward().jump(nums));
    }
}

class Solution_T45_self {
    /*
    倒着从后往前找，只要经过的元素值>=当前回溯的步数&>上次记录，则更新...最终ok~
     */
    public int jump(int[] nums) {
        int slowP = nums.length - 1;
        int steps = 0;
        int temp = 0;
        while (slowP > 0) {
            int i = slowP - 1, j = 1;
            for (; i >= 0; i--, j++) {
                if (nums[i] >= j && nums[i] >= temp)
                    slowP = i;
            }
            steps++;
        }
        return steps;
    }
}

class Solution_T45_forward {
    /*
    从前往后：每次选当前覆盖范围内元素的覆盖边界（也就是下一次覆盖范围最广的那个
     */
    public int jump(int[] nums) {
        if (nums.length < 3)
            return nums.length - 1;
        int raw_bound = nums[0];
        int bound = raw_bound;
        int steps = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > raw_bound) {
                steps++;
                raw_bound = bound;
            }
            bound = Math.max(bound, i + nums[i]);
            if (bound >= nums.length - 1)
                return steps + 1;
        }
        return -1;// 形式上需要返回内容
    }
}