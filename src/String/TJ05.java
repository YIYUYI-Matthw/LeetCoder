package String;

/*
请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
public class TJ05 {
    public static void main(String[] args) {
        String str = "     ";
        System.out.println(new Solution_TJ05_self().replaceSpace(str));
    }
}

class Solution_TJ05_self {
    public String replaceSpace(String s) {
        // return s.replace(" ", "%20");
        if (s.equals(""))
            return s;
        int len = s.length();
        char[] chars = s.toCharArray();
        String[] strs = new String[len];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                strs[i] = "%20";
                continue;
            }
            strs[i] = String.valueOf(chars[i]);
        }
        if (len == 1)
            return strs[0];
        for (int i = 1; i < len; i++) {
            strs[i] = strs[i - 1] + strs[i];
        }
        return strs[len - 1];
    }
}

class Solution_TJ05_stringBuilder {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}