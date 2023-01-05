package Back_track;

/*
找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：

只使用数字1到9
每个数字 最多使用一次
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class T216 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution_T216_self().combinationSum3(2, 5).toArray()));
    }
}

class Solution_T216_self {
    // 先回溯再剪枝
    private final List<List<Integer>> res = new LinkedList<>();
    private final LinkedList<Integer> temp = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        back_track(n, k, 0, 1);
        return res;
    }

    public void back_track(int n, int k, int sum, int start) {
        if (sum == n && temp.size() == k) {
            res.add(new LinkedList<>(temp));
            return;
        }
        for (int i = start; i < 10-sum; i++) {
            temp.add(i);
            back_track(n, k, sum + i, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}