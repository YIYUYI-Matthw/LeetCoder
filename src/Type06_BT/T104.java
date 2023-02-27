package Type06_BT;

// 二叉树最大深度
public class T104 {
    public static void main(String[] args) {
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node3 = new TreeNode<>(3, null, node4);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node1 = new TreeNode<>(1, node2, node3);
        System.out.println(new Solution_T104_again().maxDepth(node1));
    }
}

class Solution_T104_again {
    // DFS-前序遍历-每次记录max(left, right)
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Math.max(left, right);
    }
}