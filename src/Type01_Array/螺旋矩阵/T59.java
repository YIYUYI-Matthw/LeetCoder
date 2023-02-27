package Type01_Array.螺旋矩阵;

import java.util.Arrays;

// TODO：二刷：官方题解

/*
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 */
public class T59 {
    public static void main(String[] args) {
        int[][] res_mat = new Solution_T59_again().generateMatrix(6);
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

class Solution_T59_again {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        int i = 0, j;
        for (; i < n / 2; i++) {
            num += i == 0 ? 0 : 4 * (n - (2 * (i - 1)) - 1);
            for (j = 0; j < n - (2 * i) - 1; j++) {
                int ctn_row = n - (2 * i) - 1; // 每行这么多数字
                // 上右下左:(4 * i * (ctn_row + 2)
                res[i][i + j] = num + j;
                res[i + j][n - i - 1] = num + j + ctn_row;
                res[n - i - 1][n - 1 - j - i] = num + j + 2 * ctn_row;
                res[n - 1 - j - i][i] = num + j + 3 * ctn_row;
            }
        }
        // 补中间的：n为奇数
        if (n % 2 != 0)
            res[n / 2][n / 2] = n * n;
        return res;
    }
}