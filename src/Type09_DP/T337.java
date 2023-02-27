package Type09_DP;

import Type10_Search.TreeNode;

public class T337 {
    public static void main(String[] args) {
        // [3,2,3,null,3,null,1]
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(3, null, null);
        TreeNode node3 = new TreeNode(2, null, node1);
        TreeNode node4 = new TreeNode(3, null, node2);
        TreeNode root = new TreeNode(3, node3, node4);
        System.out.println(new Solution_T337_org().rob(root));
    }
}

class Solution_T337_org {
    /*
    思路：按照层进行拆分，dp[i]表示前i层最高收益
    【自否】层次遍历：queue。两个队列交换着来：一个队列不断出队计算当前层的max值；另一个队列不断入队加载下一层
    DFS：解题关键在于遍历时如何结合DFS性质来做状态转移：想不出来呀！【DFS时如何遍历这个很不好想】
     */

    /*
    树形DP：分支（归并）和DP的结合
    ① 每个节点的max_profit都有两种情况：要这个节点-A | 不要这个节点-B
    ② 一个节点的max_profit = max(node_val + LB+LA, maxL(A,B) + maxR(A,B))
        这里三个max都是DP的体现：子问题->问题；括号外的max同时含有分治（主要是合）的体现
     */
    public int rob(TreeNode root) {
        int[] profits = dfs(root);
        return Math.max(profits[0], profits[1]);
    }

    public int[] dfs(TreeNode root) {
        // 每个root节点都返回两个值：A和B
        if (root == null)
            return new int[]{0, 0};
        int[] left_profit = dfs(root.left);
        int[] right_profit = dfs(root.right);
        return new int[]{root.val + left_profit[1] + right_profit[1],
                Math.max(left_profit[0], left_profit[1]) + Math.max(right_profit[0], right_profit[1])};
    }
}
