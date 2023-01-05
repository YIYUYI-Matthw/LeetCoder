package Back_track;

/*
给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T90 {

    public static void main(String[] args) {
        int[] arr_raw = {1, 2, 4, 2, 7, 20, 9};
        System.out.println(Arrays.toString(new Solution_T90_self().subsetsWithDup(arr_raw).toArray()));
    }
}

class Solution_T90_self {
    /*
    先排序再说其他的：O(nlogn)+O(n^2)
     */
    private final List<List<Integer>> res = new ArrayList<>();
    private boolean isBT = false;
    private int[] arr_sorted;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        arr_sorted = mergeSort(nums);
        for (int i = 0; i < arr_sorted.length + 1; i++) {
            List<Integer> temp = new ArrayList<>();
            // list长度从0到arr等长
            back_track(temp, i, 0);
        }
        return res;
    }

    public void back_track(List<Integer> temp, int length, int startIdx) {
        if (temp.size() == length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = startIdx; i < arr_sorted.length; i++) {
            if (isBT && arr_sorted[i] == arr_sorted[i - 1])
                continue;
            temp.add(arr_sorted[i]);
            isBT = false;
            back_track(temp, length, i + 1);
            temp.remove(temp.size() - 1);
            isBT = true;
        }
        isBT = false;
    }


    public int[] merge(int[] list1, int[] list2) {
        int s1 = 0, s2 = 0, start = 0;
        int len01 = list1.length, len02 = list2.length;
        int[] sorted_arr = new int[len01 + len02];
        while (s1 < len01 && s2 < len02) {
            if (list1[s1] < list2[s2])
                sorted_arr[start++] = list1[s1++];
            else
                sorted_arr[start++] = list2[s2++];
        }
        while (s1 < len01)
            sorted_arr[start++] = list1[s1++];
        while (s2 < len02)
            sorted_arr[start++] = list2[s2++];
        return sorted_arr;
    }

    public int[] mergeSort(int[] arr_raw) {
        int left = 0;
        int right = arr_raw.length;
        if (arr_raw.length == 1)
            return arr_raw; // [from, to)
        int mid = (left + right) / 2;
        int[] arr_left = mergeSort(Arrays.copyOfRange(arr_raw, left, mid));// [left, mid]
        int[] arr_right = mergeSort(Arrays.copyOfRange(arr_raw, mid, right));
        return merge(arr_left, arr_right);
    }
}
