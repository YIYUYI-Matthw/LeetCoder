package Type09_DP;

/*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 */


public class T63 {
    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 1}, {0, 0}};
        System.out.println(new Solution_T63_self().uniquePathsWithObstacles(obstacleGrid));
    }
}

class Solution_T63_self {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1)
            return 0; // 障碍物在开头：走不了了

        int height = obstacleGrid.length;
        int weight = obstacleGrid[0].length;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {

                if (obstacleGrid[i][j] == 1) {
                    // 障碍物赋值为0
                    obstacleGrid[i][j] = 0;
                    continue;
                }

                if (i == 0 && j == 0) {
                    obstacleGrid[i][j] = 1;
                    continue;
                }

                if (i == 0 || j == 0) {
                    obstacleGrid[i][j] = i == 0 ? obstacleGrid[i][j - 1] : obstacleGrid[i - 1][j];
                    continue;
                }

                obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1]; // 状态转移
            }
        }
        return Math.max(0, obstacleGrid[height - 1][weight - 1]); // 防止障碍物就是终点
    }
}
