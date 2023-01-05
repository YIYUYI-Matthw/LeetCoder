package Array.螺旋矩阵;

import java.util.Arrays;

public class T59 {
    public static void main(String[] args) {
        int[][] res_mat = new Solution_T59_Carl().generateMatrix(4);
        for (int[] temp : res_mat) {
            System.out.println(Arrays.toString(temp));
        }
    }
}

class Solution_T59_Carl {
    // 没有算法，只是边界判断
    public int[][] generateMatrix(int n) {
        // 奇数要对中心元素赋值
        int[][] res_mat = new int[n][n];
        int loop_idx = 0;// 从第1圈开始，每条边填n-(2*loop-1)个数；一共有n/2圈
        int num = 1;
        int row, col;
        while (loop_idx < n / 2) {
            row = loop_idx;
            col = loop_idx;
            // 上
            for (; col < n - loop_idx - 1; col++) {
                res_mat[row][col] = num++;
            }
            // 右
            for (; row < n - loop_idx - 1; row++) {
                res_mat[row][col] = num++;
            }
            // 下
            for (; col > loop_idx; col--) {
                // 注意这里是判断大于loop而不是0
                res_mat[row][col] = num++;
            }
            // 左
            for (; row > loop_idx; row--) {
                res_mat[row][col] = num++;
            }
            // 增加一圈
            loop_idx++;
        }
        if (n % 2 == 1)
            res_mat[n / 2][n / 2] = num;
        return res_mat;
    }
}