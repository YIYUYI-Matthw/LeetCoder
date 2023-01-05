package Back_track;

/*
给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 */

/*
void backtracking(参数) {
    if (终止条件) {
        存放结果;
        return;
    }

    for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
        处理节点;
        backtracking(路径，选择列表); // 递归
        回溯，撤销处理结果
    }
}
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class T77 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution_self_T77().combine(5, 2).toArray()));
    }
}

class Solution_self_T77 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp_list = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        combine_re(n, k, 1);
        return res;
    }

    public void combine_re(int n, int k, int start) {
        if (temp_list.size() == k) {
            res.add(new ArrayList<>(temp_list));
            return;
        }
        for (int i = start; i < n + 1; i++) {
            // 添加这个节点
            temp_list.add(i);
            // 继续向下遍历，直到长度达标后返回
            combine_re(n, k, i + 1);
            // 回溯
            temp_list.remove(temp_list.size() - 1); // 把start，亦即当前最后加入的元素删除
        }
    }
}