package Back_track;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T131 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution_T131_half().partition("aab").toArray()));
    }
}

class Solution_T131_half {
    List<List<String>> res = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    String subString;

    public List<List<String>> partition(String s) {
        StringBuilder chs = new StringBuilder(s);
        back_track(chs, 0);
        return res;
    }

    public void back_track(StringBuilder chs, int split_start) {
        if (split_start == chs.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int split_end = split_start; split_end < chs.length() + 1; split_end++) {
            subString = chs.substring(split_start, split_end); // 这一步很关键：[startIndex, i]就是区间
            if (!isRev(subString))
                continue;
            temp.add(subString);
            back_track(chs, split_end);
            temp.remove(temp.size() - 1);
        }
    }

    public boolean isRev(String str) {
        if (str.length() == 0)
            return false;
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--))
                return false;
        }
        return true;
    }
}

// 想法：设定分割线的数目
