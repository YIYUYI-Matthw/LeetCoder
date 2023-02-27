package Type10_Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
BFS：二叉树层次遍历

input: [3,9,20,null,null,15,7]
output: [[3],[9,20],[15,7]]
 */
public class T102 {

    public static void main(String[] args) {
//        Object[] nodes = {3, 9, 20, null, null, 15, 7};
        TreeNode leafL = new TreeNode(15, null, null);
        TreeNode leafR = new TreeNode(7, null, null);
        TreeNode l2L = new TreeNode(9, null, null);
        TreeNode l2R = new TreeNode(20, leafL, leafR);
        TreeNode head = new TreeNode(3, l2L, l2R);
        System.out.println(new Solution_T102_self().levelOrder(head));
    }
}

class Solution_T102_self {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res_list = new LinkedList<>();
        if (root == null) {
            return res_list;
        }

        LinkedList<TreeNode> node_queue = new LinkedList<>();
        node_queue.offer(root);
        while (!node_queue.isEmpty()) {
            LinkedList<Integer> temp_list = new LinkedList<>();
            int current_len = node_queue.size();
            for (int i = 0; i < current_len; i++) {
                TreeNode temp = node_queue.poll();
                temp_list.add(temp.val);
                if (temp.left != null)
                    node_queue.offer(temp.left);
                if (temp.right != null)
                    node_queue.offer(temp.right);
            }
            res_list.add(temp_list);
        }
        return res_list;
    }
}

class Solution_T102_org {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }
}