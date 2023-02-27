package Type09_DP;

/*
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 */

public class T392 {
    public static void main(String[] args) {
        String sub = "aaaaaa";
        String str = "aaa";
        System.out.println(new Solution_T392_self().isSubsequence(sub, str));
    }

}

class Solution_T392_self {
    /*
    没啥好说的，感觉不是dp
     */
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty())
            return true;
        int i = 0;
        for (char c : s.toCharArray()) {
            if (i >= t.length()) {
                // i已经走到最后了，子串还有内容
                return false;
            }
            while (i < t.length() && c != t.charAt(i))
                i++; // 不相等，往后移
            i++; // 相等，看下一个
        }
        return i <= t.length();
    }
}