package Back_track;

import java.util.*;

/*
给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
输入：nums = [4,6,7,7]
输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
 */
public class T491 {
    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        System.out.println(Arrays.toString(new Solution_T491_Carl().findSubsequences(nums).toArray()));
    }
}

class Solution_T491_self_timeout {
    /*
    idea：从2-size()的所有上升序列
     */
    List<List<Integer>> res_list = new ArrayList<>();
    MyList temp;

    static class MyList extends ArrayList<Integer> {
        @Override
        public int hashCode() {
            return Integer.parseInt(Arrays.toString(this.toArray()));
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        int endIdx = nums.length;
        for (int lenTgt = 2; lenTgt < endIdx + 1; lenTgt++) {
            temp = new MyList();
            back_track(nums, 0, lenTgt, endIdx);
        }
        return res_list;
    }

    public void back_track(int[] nums, int startIdx, int lenTgt, int endIdx) {
        if (temp.size() == lenTgt && !res_list.contains(temp)) {
            res_list.add(new ArrayList<>(temp));
            return;
        }
        for (int i = startIdx; i < endIdx; i++) {
            if (temp.size() == 0)
                temp.add(nums[i]);
            else {
                if (nums[i] >= temp.get(temp.size() - 1)) {
                    temp.add(nums[i]); // 为什么这里浅拷贝没问题？
                }
            }
            back_track(nums, i + 1, lenTgt, endIdx);
            if (!temp.isEmpty())
                temp.remove(temp.size() - 1);
        }
    }
}

class Solution_T491_self {
    /*
    idea：两个for循环遍历+hashMap去重
     */
    List<List<Integer>> res_list = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    Set<Integer> temp_set = new HashSet<>();
    boolean isBT = false;

    public List<List<Integer>> findSubsequences(int[] nums) {
        int endIdx = nums.length;
        back_track(nums, 0);
        return res_list;
    }

    public void back_track(int[] nums, int startIdx) {
        if (temp.size() >= 2)
            res_list.add(new ArrayList<>(temp)); // 不应该return
        for (int i = startIdx; i < nums.length; i++) {
            if (isBT && temp_set.contains(nums[i]))
                continue;
            isBT = false; // 没有回溯
            if (temp.size() == 0 || nums[i] >= temp.get(temp.size() - 1))
                temp.add(nums[i]);
            back_track(nums, i + 1);
            isBT = true; // 记录回溯
            if (!temp.isEmpty())
                temp.remove(temp.size() - 1);
        }
    }
}

class Solution_T491_Carl {
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums, 0);
        return res;
    }

    private void backtracking(int[] nums, int start) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
        }
        Set<Integer> temp_used = new HashSet<>();
        //int[] used = new int[201]; // 每次循环都维护一个used，这个used就是针对这一层的记录
        for (int i = start; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1) ||
                    (temp_used.contains(nums[i]))) continue;
            temp_used.add(nums[i]);
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
