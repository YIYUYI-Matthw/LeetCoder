package GreedyA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/*
有一些球形气球贴在一堵用 XY 平面表示的墙面上。
    墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。
    在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。
    可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 */
public class T452 {
    /*
    思考：就是要找出能够涉及所有气球的直径重合区间个数
     */
    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {7, 12}, {1, 6}};
        System.out.println(new Solution_T452_gov().findMinArrowShots(points));
    }
}

/*
考虑：
    贪心一：每次找重合质量最高的区间去掉
    贪心二：每次找重合质量最高的那个球及区间内其他球
    sol：迭代寻找重合区间，直到重合区间list不再改变，length就是答案
 */
class Solution_T452_self {
    public int findMinArrowShots(int[][] points) {
        // O(n^3)
        LinkedList<int[]> point_list = new LinkedList<>(Arrays.asList(points));
        LinkedList<int[]> temp = new LinkedList<>();
        boolean isOverlap = true;
        while (isOverlap) {
            isOverlap = false;
            for (int[] p1 : point_list) {
                for (int[] p2 : point_list) {
                    if (p1[0] <= p2[1] && p1[0] >= p2[0]) {
                        // s2 < s1 < e2 则重叠
                        isOverlap = true;
                        temp.add(new int[]{p1[0], Math.min(p1[1], p2[1])});
                    }
                    if (p2[0] <= p1[1] && p2[0] >= p1[0]) {
                        // s2 < s1 < e2 则重叠
                        isOverlap = true;
                        temp.add(new int[]{p2[0], Math.min(p2[1], p1[1])});
                    }
                }
                point_list.addAll(temp);
                temp.clear();// 清空
            }
        }
        return point_list.size();
    }
}

/*
贪心3：按照右边界排列，每次在最靠左的那个右边界放一只箭
 */
class Solution_T452_gov {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                /*
                这个函数总是把计算后认为小的放前面
                返回值：>0：认为p1>p2；=0：相等；<0：小
                因此，
                    如果想递增排列：p1-p2：p1小就会小于0：p1就放前面
                    递减排列：p2-p1：p1小就会大于零：认为p1>p2，p2就会放前面：递减
                 */
                if (p1[1] < 0 && p2[1] > 0) return -1; // 当p1、p2取值为int范围左右边界，这里就会溢出
                if (p1[1] > 0 && p2[1] < 0) return 1;
                return p1[1] - p2[1];
            }
        });
        // 考虑维护一个“最左边界”：如果一个气球的取值包含了这个边界，则这个气球是一个在过去被刺破的：continue
        int min_left = points[0][1]; // 每次都是最左的最右边界充当最左边界
        int cnt = 1; // 至少有一个：初始状态就把第一个算上
        for (int[] point : points) {
            if (point[0] > min_left) {
                min_left = point[1];
                cnt++;
            }
        }
        return cnt;
    }
}