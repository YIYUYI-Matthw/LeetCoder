package Type06_BT;

// 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
public class T226 {
}

class Solution_T226_again {
    // 遍历，先左后右：先右后左也行，就是选一个
    public TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if (root == null)
            return null;
        TreeNode<Integer> left = invertTree(root.left); // 不能提前修改结构，这里就要先记录
        root.left = invertTree(root.right);
        root.right = left;
        return root;
    }
}