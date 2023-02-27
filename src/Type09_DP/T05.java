package Type09_DP;

public class T05 {
    /*
    回文：a, bb, abba, abcba
     */
    public static void main(String[] args) {
        System.out.println(new Solution_T05_M3().longestPalindrome("babaa"));
    }
}

// 方式一：暴力枚举：for-开头 for-结尾 for-is回文 O(n^3)

class Solution_T05_M2 {
    // 方式二：中心扩散：枚举回文中心，有奇偶数之分，分别判断：O(n^2/2+n^2/2)：这也是我一开始用的，虽然没写出来吧
    public String longestPalindrome(String s) {
        int length = s.length();
        char[] chs = s.toCharArray();
        // 小于等于2时
        if (length == 1)
            return s;
        else if (length == 2) {
            if (chs[0] == chs[1])
                return s;
            else
                return "" + chs[0];
        }
        int max_len = 1, idx = 0, temp_len;
        for (int i = 0; i < length; i++) {
            // 奇数
            temp_len = 1;
            for (int j = i + 1, k = i - 1; j < s.length() && k >= 0; j++, k--) {
                if (chs[j] == chs[k]) {
                    temp_len += 2;
                } else {
                    break;
                }
            }
            if (temp_len > max_len) {
                max_len = temp_len;
                idx = i - (max_len / 2);
            }
            // 偶数
            temp_len = 0;
            for (int j = i, k = i + 1; k < s.length() && j >= 0; j--, k++) {
                if (chs[j] == chs[k]) {
                    temp_len += 2;
                } else {
                    break;
                }
            }
            if (temp_len > max_len) {
                max_len = temp_len;
                idx = i - (max_len / 2) + 1;
            }
        }
        return s.substring(idx, idx + max_len);
    }
}

class Solution_T05_M3 {
    public String longestPalindrome(String s) {
        int length = s.length();
        char[] eles = s.toCharArray();
        // l<=2
        if (length == 1)
            return s;
        else if (length == 2)
            if (eles[0] == eles[1])
                return s;
            else
                return "" + eles[0];
        // l>2
        boolean[][] dp = new boolean[length][length];
        int max_L = 1, start = 0;
        int temp_L = 1;
        for (int j = 1; j < length; j++) {
            // j>=i
            for (int i = 0; i <= j; i++) {
                if (eles[i] == eles[j] && (j - i < 3 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    temp_L = j - i + 1;
                }
                if (temp_L > max_L) {
                    max_L = temp_L;
                    start = i;
                }
            }
        }
        return s.substring(start, max_L + start);
    }
}