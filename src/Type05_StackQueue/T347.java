package Type05_StackQueue;

import java.util.*;

/*
给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 */
public class T347 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(new Solution_T347_again().topKFrequent(nums, 2)));
    }
}

class Solution_T347_again {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numFracMap = new HashMap<>();
        for (int num : nums)
            numFracMap.put(num, numFracMap.getOrDefault(num, 0) + 1);
        PriorityQueue<int[]> numSortedQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (Map.Entry<Integer, Integer> entry : numFracMap.entrySet()) {
            int num = entry.getKey();
            int value = entry.getValue();
            if (numSortedQueue.size() == k) {
                assert numSortedQueue.peek() != null;
                if (numSortedQueue.peek()[1] < value) {
                    // 从小到大排序&先进先出，所以是peek
                    numSortedQueue.poll();
                    numSortedQueue.offer(new int[]{num, value});
                }
            } else
                numSortedQueue.offer(new int[]{num, value});
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = Objects.requireNonNull(numSortedQueue.poll())[0];
        }
        return res;
    }
}