package Back_track;

import java.util.*;

/*
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
candidates 中的数字可以无限制重复被选取。
 */

public class T39 {
    public static void main(String[] args) {
//        int[] candidates = {18, 34, 2, 16, 25, 6, 35};// t-40：这个是超时案例
        int[] candidates = {2, 3, 6, 7};// t-40：这个是超时案例
        System.out.println(Arrays.toString(new Solution_T39_self_timeout().combinationSum(candidates, 7).toArray()));
    }
}

class Solution_T39_self_timeout {
    List<List<Integer>> res = new ArrayList<>();
    MyList temp = new MyList();
    ArrayList<Integer> cands = new ArrayList<>(); // 处理后的candidates

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 每个数字最多重复target/num次
        for (int candidate : candidates) {
            for (int j = 0; j < target / candidate; j++) {
                cands.add(candidate);
            }
        }
        // 回溯
        back_track(0, target, 0);
        return res;
    }

    public void back_track(int sum, int target, int start) {
        if (sum == target && !res.contains(temp)) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (sum > target) return; // 如果已经超过那就不用加了
        for (int i = start; i < cands.size(); i++) {
            temp.add(cands.get(i));
            back_track(sum + cands.get(i), target, i + 1); // sum+can写在这里就不用“回溯”了
            temp.remove(temp.size() - 1);
        }
    }

    static class MyList extends ArrayList<Integer> {
        @Override
        public int hashCode() {
            StringBuilder sb = new StringBuilder();
            for (int item : this)
                sb.append(item);
            return Integer.parseInt(sb.toString()); // 只要string一致就算数
        }
    }
}

class Solution_T39_self_AC {
    List<List<Integer>> res = new ArrayList<>();
    ArrayList<Integer> temp = new ArrayList<>();
    ArrayList<Integer> cands = new ArrayList<>(); // 处理后的candidates
    private boolean isBack = false;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 每个数字最多重复target/num次
        for (int candidate : candidates) {
            for (int j = 0; j < target / candidate; j++) {
                cands.add(candidate);
            }
        }
        // 回溯
        back_track(0, target, 0); // 2<=candidates[i]<=40
        return res;
    }

    @SuppressWarnings("all")
    public void back_track(int sum, int target, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (sum > target) return; // 如果已经超过那就不用加了
        for (int i = start; i < cands.size(); i++) {
            // 如果回溯后引入的num和pre一样，那就跳过
            if (isBack && cands.get(i) == cands.get(i - 1)) continue;
            /*
            【注意这里的操作：每次回溯都应该查看一下这个值是否和之前一样】
            重点思考为什么是前一个：1. 前一个和之前的重复---√；2. 前一个和之前的不重复-判断是否重复---√
             */
            isBack = false;
            temp.add(cands.get(i));
            back_track(sum + cands.get(i), target, i + 1); // sum+can写在这里就不用“回溯”了
            temp.remove(temp.size() - 1);
            isBack = true;
        }
    }
}

class Solution_T39_another {

    public List<List<Integer>> rs = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combination(candidates, target, 0, new ArrayList<Integer>());
        return rs;
    }


    public void combination(int[] candidates, int target, int start, List<Integer> list) {
        if (target == 0) {
            List<Integer> tmp = new ArrayList<>(list);
            rs.add(tmp);
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                continue;
            }
            list.add(candidates[i]);
            combination(candidates, target - candidates[i], i, list); // 重点是重复，那么只要每次start不变就可以重复了
            list.remove(list.size() - 1);
        }
    }

}