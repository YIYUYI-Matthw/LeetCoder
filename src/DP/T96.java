package DP;

/*
给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
【注意】二叉搜索树：左子树、根节点、右子树：小-中-大
 */
public class T96 {
    public static void main(String[] args) {
        System.out.println(new Solution_T96_self().numTrees(3));
    }
}

class Solution_T96_error {
    public int mul(int k) {
        if (k == 1)
            return 1;
        else return k * mul(k - 1);
    }

    public int numTrees(int n) {
        int[] M = new int[n + 1];
        int temp_cnt;
        M[0] = 1; // 占位
        M[1] = 1;
        M[2] = 2;
        for (int i = 3; i <= n; i++) {
            temp_cnt = i * M[i - 1];
            int j;
            for (j = 1; j < (i - 1) / 2; j++) {
                temp_cnt += i * mul(i - 1 - j) / mul(j) * M[j] * M[i - 1 - j];
            }
            if (n % 2 == 0)
                temp_cnt += i * mul(i - 1 - j) / mul(j) * M[j] * M[i - 1 - j];
            else
                temp_cnt += (i * mul(i - 1 - j) / mul(j) * M[j] * M[i - 1 - j]) / 2;
            M[i] = temp_cnt;
        }
        return M[n];
    }
}

class Solution_T96_self {
    public int numTrees(int n) {
        int[] M = new int[n + 1];
        int temp; // 统计组合数
        M[1] = 1;
        for (int i = 2; i <= n; i++) {
            temp = 0;
            for (int j = 1; j <= i; j++) {
                temp += (j == 1 ? 1 : M[j - 1]) * (i == j ? 1 : M[i - j]); // 左子树+右子树
            }
            M[i] = temp;
        }
        return M[n];
    }
}