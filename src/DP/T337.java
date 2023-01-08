package DP;

import LinkedList.ListNode;
import Search.TreeNode;

import java.util.Queue;

public class T337 {
    public static void main(String[] args) {
        // [3,2,3,null,3,null,1]
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(3, null, null);
        TreeNode node3 = new TreeNode(2, null, node1);
        TreeNode node4 = new TreeNode(3, null, node2);
        TreeNode root = new TreeNode(3, node3, node4);
        System.out.println(new Solution().rob(root));
    }
}

class Solution {
    /*
    思路：按照层进行拆分，dp[i]表示前i层最高收益
    【自否】层次遍历：queue。两个队列交换着来：一个队列不断出队计算当前层的max值；另一个队列不断入队加载下一层
    DFS：解题关键在于遍历时如何结合DFS性质来做状态转移：想不出来呀！
     */
    public int rob(TreeNode root) {
        return -1; // 暂时搁置了
    }
}
