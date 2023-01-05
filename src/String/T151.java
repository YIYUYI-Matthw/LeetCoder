package String;

/*
给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 */
public class T151 {
    public static void main(String[] args) {
        String str = "  hello world  ";
        System.out.println(new Solution_T51_fast().reverseWords(str));
    }
}

class Solution_T51_self {
    public String reverseWords(String s) {
        if (s.equals(" ") || s.equals(""))
            return "";
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        int left = 0, right = strs.length - 1;
        while (left <= right) {
            String temp = strs[left];
            strs[left++] = strs[right];
            strs[right--] = temp;
        }
        for (int i = 0; i < strs.length - 1; i++) {
            if (strs[i].equals(""))
                continue;
            sb.append(strs[i]).append(" ");
        }
        if (strs[strs.length - 1].equals("")) {
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append(strs[strs.length - 1]);
        return sb.toString();
    }
}

class Solution_T51_fast {
    /*
    这个方法是从其他题解上看到的
     */
    public String reverseWords(String s) {
        if (s.equals(" ") || s.equals(""))
            return "";
        String[] strs = s.trim().split("\\s+"); // split中的标识为regex：正则。\s为空字符-空格、制表符等、\s+为任意正数个\s
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i > 0; i--) {
            sb.append(strs[i]).append(" ");
        }
        return sb.append(strs[0]).toString();
    }
}

// 还可以考虑用栈实现