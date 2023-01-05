import java.util.Arrays;

public class Dij {
    // 假定输入为num-节点个数、n*3的二维数组-n为边的个数
    public static void main(String[] args) {
        int[][] edges = {{1, 2, 5}, {1, 5, 3}};
        int[][] adj_mat = new Build_graph().build_mat(6, edges);
        for (int[] line: adj_mat)
            System.out.println(Arrays.toString(line));
    }
}

class Build_graph {
    public int[][] build_mat(int num, int[][] edges) {
        int[][] adj_mat = new int[num][num];
        for (int[] edge : edges)
            adj_mat[edge[0]][edge[1]] = edge[2];// 权重赋值

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                if (adj_mat[i][j] == 0)
                    adj_mat[i][j] = (int) 1e5; // 没有边就赋值max
            }
        }
        return adj_mat;
    }
}
