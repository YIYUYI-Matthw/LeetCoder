package Type05_StackQueue;

import java.util.*;

/*
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
返回 滑动窗口中的最大值 。
 */
public class T239 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(new Solution_T239_again().maxSlidingWindow(nums, 3)));
    }
}

class Solution_T239_again_OverTime {
    public int sortSelf(Deque<Integer> deque) {
        int max = (int) -1e4 - 1;
        for (int a : deque)
            max = Math.max(a, max);
        return max;
    }

    // n2：用队列来充当窗口，每次存取后都重新排个序
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++)
            deque.offer(nums[i]);
        res[0] = sortSelf(deque);
        for (int i = k; i < nums.length; i++) {
            deque.poll();
            deque.offer(nums[i]);
            res[i - k + 1] = sortSelf(deque);
        }
        return res;
    }
}

class Solution_T239_again {
    // n2：俩队列：一个窗口、一个排序
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            deque.offer(nums[i]);
            priorityQueue.offer(nums[i]);
        }
        res[0] = priorityQueue.peek();
        for (int i = k; i < nums.length; i++) {
            priorityQueue.remove(deque.poll());
            deque.offer(nums[i]);
            priorityQueue.offer(nums[i]);
            res[i - k + 1] = priorityQueue.peek();
        }
        return res;
    }
}