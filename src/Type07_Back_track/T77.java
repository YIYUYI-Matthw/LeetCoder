package Type07_Back_track;

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

class Solution_T77_again {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp_list = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        combine_re(n, k, 1);
        return res;
    }

    /*
    start-n：遍历范围
    k：终止条件判定
     */
    public void combine_re(int n, int k, int start) {
        if (temp_list.size() == k) {
            res.add(new ArrayList<>(temp_list));
            // 达到条件，然后返回并且在上一递归中进行回溯
            return;
        }
        // 不满足条件：开始遍历
        for (int i = start; i < n + 1; i++) {
            // 添加节点：使用i而不是start：后续可能有变化
            temp_list.add(i);
            combine_re(n, k, i + 1);
            // 回溯：接着下一个点遍历
            temp_list.remove(temp_list.get(temp_list.size() - 1));
        }
    }
}

class Solution_T77_again_2 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> temp_list = new ArrayList<>();
        combine_re(n, k, 1, temp_list);
        return res;
    }

    /*
    start-n：遍历范围
    k：终止条件判定
     */
    public void combine_re(int n, int k, int start, List<Integer> temp_list) {
        if (temp_list.size() == k) {
            res.add(temp_list);
            // 达到条件，然后返回并且在上一递归中进行回溯
            return;
        }
        // 不满足条件：开始遍历
        for (int i = start; i < n + 1; i++) {
            // 添加节点：使用i而不是start：后续可能有变化
            temp_list.add(i);
            combine_re(n, k, i + 1, new ArrayList<>(temp_list));
            // 回溯
            temp_list.remove(temp_list.get(temp_list.size()-1));
        }
    }
}