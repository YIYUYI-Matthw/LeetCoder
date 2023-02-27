package Type06_BT;

import java.util.ArrayList;
import java.util.List;

/*
给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
叶子节点 是指没有子节点的节点。
 */
public class T257 {
    public static void main(String[] args) {
        TreeNode<Integer> node4 = new TreeNode<>(5);
        TreeNode<Integer> node3 = new TreeNode<>(3);
        TreeNode<Integer> node2 = new TreeNode<>(2, null, node4);
        TreeNode<Integer> node1 = new TreeNode<>(1, node2, node3);
        System.out.println(new Solution_T257_again().binaryTreePaths(node1));
    }
}

interface a{
    default void b(){
        int a = 1;
    }
}

class Solution_T257_again {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null)
            return paths;
        StringBuilder builder = new StringBuilder();
        builder.append(root.val);
        if (root.left != null)
            dfs(root.left, new StringBuilder(builder.toString()), paths);
        if (root.right != null)
            dfs(root.right, new StringBuilder(builder.toString()), paths);
        if (root.left == null && root.right == null)
            paths.add(builder.toString());
        return paths;
    }

    // 先写个dfs再说，打算：先根序
    public void dfs(TreeNode root, StringBuilder builder, List<String> paths) {
        builder.append("->").append(root.val);
        if (root.left != null)
            dfs(root.left, new StringBuilder(builder.toString()), paths);
        if (root.right != null)
            dfs(root.right, new StringBuilder(builder.toString()), paths);
        if (root.left == null && root.right == null)
            paths.add(builder.toString());
    }
}
