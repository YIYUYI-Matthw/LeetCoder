package Type04_String;

/*
给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 */
public class T28 {
    public static void main(String[] args) {
        String haystack = "aabaaabaaac";
        String needle = "aafbaaf";
        System.out.println(new Solution_T28_again().strStr(haystack, needle));
    }
}

class Solution_T28_again {
    private int[] getNext(String needle) {
        // left：前缀最后的idx，同时也是最大相同前后缀的长度
        // right：后缀最后的idx，而next[right]则对应了此后缀的最大相同前后缀长度
        int left = 0;
        int[] next = new int[needle.length()];
        for (int right = 1; right < needle.length(); right++) {
            while (left > 0 && needle.charAt(left) != needle.charAt(right))
                left = next[left - 1]; // 后缀和当前前缀不同，则寻找上一个前缀
            /*
            left：前缀的最后一个字符idx，如果仅仅取值为left-1，则是前一个“后缀”，即使这个后缀和当前后缀相同，也无法
             */
            if (needle.charAt(left) == needle.charAt(right))
                left++;
            next[right] = left;
        }
        return next;
    }

    public int strStr(String haystack, String needle) {
        int[] next = getNext(needle);
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                if (j != 0)
                    j = next[j-1];
                else
                    i++;
            }
        }
        if (j == needle.length())
            return i - j;
        return -1;
    }
}

// https://www.zhihu.com/question/21923021/answer/281346746