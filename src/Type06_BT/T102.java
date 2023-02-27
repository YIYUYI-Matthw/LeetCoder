package Type06_BT;

import java.util.*;

// 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
public class T102 {
}

class Solution_T102_again_iterate {
    public List<List<Integer>> levelOrder(TreeNode<Integer> root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> atomicRes = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode<Integer>> queue = new LinkedList<>();
        Deque<TreeNode<Integer>> queueQ = new LinkedList<>();
        Deque<TreeNode<Integer>> queuePointer = queue;
        queuePointer.offer(root);
        while (!queuePointer.isEmpty()) {
            while (!queuePointer.isEmpty()) {
                TreeNode<Integer> tempNode = queuePointer.pop();
                if (queuePointer == queue) {
                    if (tempNode.left != null)
                        queueQ.offer(tempNode.left);
                    if (tempNode.right != null)
                        queueQ.offer(tempNode.right);
                }
                if (queuePointer == queueQ) {
                    if (tempNode.left != null)
                        queue.offer(tempNode.left);
                    if (tempNode.right != null)
                        queue.offer(tempNode.right);
                }
                atomicRes.add(tempNode.val);
            }
            res.add(new ArrayList<>(atomicRes));
            queuePointer = queuePointer == queue ? queueQ : queue;
        }
        return res;
    }
}