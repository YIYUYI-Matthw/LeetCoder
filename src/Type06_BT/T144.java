package Type06_BT;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
public class T144 {
    public static void main(String[] args) {
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node3 = new TreeNode<>(3, null, node4);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node1 = new TreeNode<>(1, node2, node3);
        System.out.println(new Solution_T144_again_iterate().preorderTraversal(node1));
    }
}

class Solution_T144_again_rec {
    public List<Integer> preorderTraversal(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res);
        return res;
    }

    private void traverse(TreeNode<Integer> root, List<Integer> res) {
        if (root == null)
            return;
        res.add(root.val);
        traverse(root.left, res);
        traverse(root.right, res);
    }
}

class Solution_T144_again_iterate {
    public List<Integer> preorderTraversal(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<TreeNode<Integer>> nodeStack = new Stack<>();
        TreeNode<Integer> temp_root;
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            temp_root = nodeStack.pop();
            res.add(temp_root.val);
            // 右子树入栈
            if (temp_root.right != null)
                nodeStack.push(temp_root.right);
            // 左子树入栈
            if (temp_root.left != null)
                nodeStack.push(temp_root.left);
        }
        return res;
    }
}