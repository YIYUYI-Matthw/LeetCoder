package Type06_BT;

public class T110 {
    TreeNode<Integer> node4 = new TreeNode<>(4);
    TreeNode<Integer> node3 = new TreeNode<>(0, null, node4);
    TreeNode<Integer> node2 = new TreeNode<>(-3);
    TreeNode<Integer> node1 = new TreeNode<>(10, node2, node3);
}

class Solution_T110_again {
    // 后续遍历-递归检查左右子树深度：不平衡就返回-1
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if (left == -1 || right == -1)
            return false;
        return Math.abs(left - right) < 1;
    }

    public int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        if (left == -1 || right == -1)
            return -1;
        if (Math.abs(left - right) > 1)
            return -1;
        // 当前子树深度
        return 1 + Math.max(left, right);
    }
}