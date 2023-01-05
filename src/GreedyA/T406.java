package GreedyA;

import java.util.*;

/*
假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
请你重新构造并返回输入数组 people 所表示的队列。
    返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 */
public class T406 {
    public static void main(String[] args) {
        int[][] people = {{4, 4}, {5, 0}, {5, 2}, {6, 1}, {7, 0}, {7, 1}};
        int[][] res = new Solution_T406_sortAsc().reconstructQueue(people);
        for (int[] item : res) {
            System.out.println(Arrays.toString(item));
        }
    }
}

class Solution_T406_self {
    /*
    局部最优：为第i-th个位置挑选最适合放的那一个
    决策：第i-th位置的元素t是剩下所有元素中k值<=i且hj值最小的那个
        k值<=i：确保前i个元素至少总数>k
     */

    // 这里是一个O(n^2)的算法
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> people_list = new LinkedList<>(Arrays.asList(people));
        int[][] res = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            int[] temp = {(int) 1e6 + 1, 0};
            for (int[] item : people_list) {
                // 比较规则：当元素的k<=j时有可能放在j-th，然后h又是所有元素的minimum：checked
                if (item[1] <= i && item[0] < temp[0])
                    temp = item;
            }
            if (temp[0] < 1e6 + 1) {
                res[i] = temp;
                people_list.remove(temp);
            }
        }
        return res;
    }
}

class Solution_T406_sortAsc {
    /*
    身高由低到高排列（身高一样，则k值小的在前），第i个人的位置是当前第k_i个”空“位置：O(n^2)
     */

    // 这里是一个O(n^2)的算法
    public int[][] reconstructQueue(int[][] people) {
        int[][] res = new int[people.length][2];

        // O(n)
        for (int[] item : res)
            item[0] = -1; // 用-1标记是否被占用

        // O(nlogn)
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person1[0] - person2[0];
                    // 个人：排序p1和p2，若返回非负，则交换，负值不变。使用p1-p2，若p1<p2则不变：大的在后-正序；p2-p1：p1<p2：为正：交换-降序
                } else {
                    return person2[1] - person1[1];
                }
            }
        });

        // O(n^2)
        for (int[] item : people) {
            int idx = 0, empties = -1;
            for (; empties < item[1]; idx++) {
                if (res[idx][0] == -1)
                    empties++;
            }
            res[idx - 1] = item;
        }
        return res;
    }
}