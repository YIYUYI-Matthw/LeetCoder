package Type06_BT;

/*
给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
如果存在，返回 true ；否则返回 false 。
 */
public class T112 {
}

class Solution {
    /*
    递归dfs取最末，相等就返回
    （1）啥也不说，先分析下使用先根序写个DFS出来
    （2）观察每步的过程决定下一步
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return (int) root.val == targetSum;
        else {
            boolean isLeftOK = hasPathSum(root.left, targetSum - (int) root.val);
            boolean isRightOK = hasPathSum(root.right, targetSum - (int) root.val);
            return isLeftOK || isRightOK;
        }
    }
}